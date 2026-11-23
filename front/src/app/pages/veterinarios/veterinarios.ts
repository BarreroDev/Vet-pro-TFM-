import { Component, inject, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TopBar } from '../../components/top-bar/top-bar';
import { NewVet } from '../../components/new-vet/new-vet';
import {VeterinarioModel} from '../../models/veterinarioModel';
import {VetCard} from '../../components/vet-card/vet-card';
import { VetService } from '../../services/vet-service';

@Component({
  selector: 'app-veterinarios',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    TopBar,
    NewVet,
    VetCard
  ],
  templateUrl: './veterinarios.html',
  styleUrl: './veterinarios.css',
})
export class Veterinarios implements OnInit {

  private vetService = inject(VetService);
  private cdr = inject(ChangeDetectorRef);

  isRegistrerOpen: boolean = false;
  cargando: boolean = true;


  listaVeterinariosOriginal: VeterinarioModel[] = [];
  listaVeterinariosFiltrados: VeterinarioModel[] = [];
  filtroBusqueda: string = '';

  ngOnInit(): void {
    this.obtenerVeterinarios();
  }

  obtenerVeterinarios(): void {
    this.cargando = true;
    this.vetService.getVets().subscribe({
      next: (data: VeterinarioModel[])=> {
        this.listaVeterinariosOriginal = data;
        this.filtrarVeterinarios();
        this.cargando = false;
        console.log('Veterinarios recibidos desde MySQL:', data);
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error al obtener veterinarios de la API:', err);
        this.cargando = false;
        this.cdr.detectChanges();
      }
    });
  }

  filtrarVeterinarios(): void {
    const texto = this.filtroBusqueda.toLowerCase().trim();

    if (!texto) {
      this.listaVeterinariosFiltrados = [...this.listaVeterinariosOriginal];
      return;
    }

    this.listaVeterinariosFiltrados = this.listaVeterinariosOriginal.filter(v => {
      const colegiado = v.numeroColegiado || '';
      return colegiado.toLowerCase().includes(texto);
    });
  }

  openRegistrer(): void {
    this.isRegistrerOpen = true;
  }

  closeRegistrer(): void {
    this.isRegistrerOpen = false;
  }

  crearVeterinario(evento: { vet: VeterinarioModel, file?: File }) {
    this.vetService.createVet(evento.vet, evento.file).subscribe({
      next: (res) => {
        console.log('¡Veterinario y foto guardados con éxito!', res);
        this.closeRegistrer();
        this.obtenerVeterinarios();
      },
      error: (err) => {
        console.error('Error al guardar el veterinario:', err);
      }
    });
  }

  eliminarVeterinario(id: number): void {
    const confirmacion = window.confirm('¿Estás seguro de que deseas eliminar este veterinario?');

    if (confirmacion) {
      this.vetService.deleteVet(id).subscribe({
        next: () => {
          this.listaVeterinariosOriginal = this.listaVeterinariosOriginal.filter(v => v.id !== id);
          this.filtrarVeterinarios();
          this.cdr.detectChanges();
        },
        error: (err) => console.error('Error al eliminar el veterinario:', err)
      });
    }
  }
}
