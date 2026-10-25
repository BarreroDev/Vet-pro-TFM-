import { Component, inject, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; // <-- 1. Importante para ngModel
import { TopBar } from '../../components/top-bar/top-bar';
import { MascotaService } from '../../services/MascotaService';
import { NewPet } from '../../components/new-pet/new-pet';
import { PetCard } from '../../components/pet-card/pet-card';

@Component({
  selector: 'app-mascotas',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule, // <-- 2. Añadido aquí
    TopBar,
    NewPet,
    PetCard
  ],
  templateUrl: './mascotas.html',
  styleUrl: './mascotas.css',
})
export class Mascotas implements OnInit {

  private mascotaService = inject(MascotaService);
  private cdr = inject(ChangeDetectorRef);

  isRegistrerOpen: boolean = false;

  // Listas de datos
  listaMascotasOriginal: any[] = [];
  listaMascotasFiltradas: any[] = [];
  filtroDniDueno: string = ''; // Texto que escribes en la barra

  cargando: boolean = true;

  ngOnInit(): void {
    this.obtenerMascotas();
  }

  obtenerMascotas(): void {
    this.cargando = true;
    this.mascotaService.getMascotas().subscribe({
      next: (data) => {
        this.listaMascotasOriginal = data;
        this.filtrarMascotas(); // Inicializa la lista filtrada con los datos de BD
        this.cargando = false;
        console.log('Mascotas recibidas desde MySQL:', data);
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error al obtener mascotas de la API:', err);
        this.cargando = false;
        this.cdr.detectChanges();
      }
    });
  }

  // Lógica de filtrado en tiempo real por DNI del dueño
  filtrarMascotas(): void {
    const texto = this.filtroDniDueno.toLowerCase().trim();

    if (!texto) {
      this.listaMascotasFiltradas = [...this.listaMascotasOriginal];
      return;
    }

    // Nota: Ajusta 'dniDueno' o 'dueño?.dni' según cómo te devuelva el objeto tu backend de Spring Boot
    this.listaMascotasFiltradas = this.listaMascotasOriginal.filter(m => {
      const dni = m.dniDueno || m.dueno?.dni || '';
      return dni.toLowerCase().includes(texto);
    });
  }

  openRegistrer(): void {
    this.isRegistrerOpen = true;
  }

  closeRegistrer(): void {
    this.isRegistrerOpen = false;
  }

  onGuardarMascota(evento: any): void {
    const { mascota, dniDueno } = evento;

    this.mascotaService.createMascota(mascota, dniDueno).subscribe({
      next: (res) => {
        console.log('Mascota guardada en BD:', res);
        this.closeRegistrer();
        this.obtenerMascotas(); // Recarga de la BD y actualiza el filtro
      },
      error: (err) => console.error('Error al guardar la mascota:', err)
    });
  }

  eliminarMascota(id: number): void {
    const confirmacion = window.confirm('¿Estás seguro de que deseas eliminar esta mascota?');

    if (confirmacion) {
      this.mascotaService.deleteMascota(id).subscribe({
        next: () => {
          this.listaMascotasOriginal = this.listaMascotasOriginal.filter(m => m.id !== id);
          this.filtrarMascotas(); // Refresca la vista filtrada
          this.cdr.detectChanges();
        },
        error: (err) => console.error('Error al eliminar la mascota:', err)
      });
    }
  }
}
