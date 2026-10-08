import { Component, inject, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TopBar } from '../../components/top-bar/top-bar';
import { MascotaService } from '../../services/MascotaService';
import { NewPet } from '../../components/new-pet/new-pet';
import { PetCard } from '../../components/pet-card/pet-card';

@Component({
  selector: 'app-mascotas',
  standalone: true,
  imports: [
    CommonModule,
    TopBar,
    NewPet,
    PetCard
  ],
  templateUrl: './mascotas.html',
  styleUrl: './mascotas.css',
})
export class Mascotas implements OnInit {

  private mascotaService = inject(MascotaService);
  private cdr = inject(ChangeDetectorRef); // Inyectamos el detector de cambios

  isRegistrerOpen: boolean = false;
  listaMascotas: any[] = [];
  cargando: boolean = true;

  ngOnInit(): void {
    this.obtenerMascotas();
  }

  obtenerMascotas(): void {
    this.cargando = true;
    this.mascotaService.getMascotas().subscribe({
      next: (data) => {
        this.listaMascotas = data;
        this.cargando = false;
        console.log('Mascotas recibidas desde MySQL:', data);
        // Forzamos a Angular a actualizar la vista por si acaso
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error al obtener mascotas de la API:', err);
        this.cargando = false;
        this.cdr.detectChanges();
      }
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
        this.closeRegistrer();  // 1. Cerramos el modal
        this.obtenerMascotas(); // 2. Recargamos la lista desde la base de datos
      },
      error: (err) => console.error('Error al guardar la mascota:', err)
    });
  }

  eliminarMascota(id: number): void {
    const confirmacion = window.confirm('¿Estás seguro de que deseas eliminar esta mascota?');

    if (confirmacion) {
      this.mascotaService.deleteMascota(id).subscribe({
        next: () => {
          this.listaMascotas = this.listaMascotas.filter(m => m.id !== id);
          this.cdr.detectChanges();
        },
        error: (err) => console.error('Error al eliminar la mascota:', err)
      });
    }
  }
}
