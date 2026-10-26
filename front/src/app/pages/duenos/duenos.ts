import { Component, inject, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TopBar } from '../../components/top-bar/top-bar';
import { NewOwner } from '../../components/new-owner/new-owner';
import { OwnerCard, Owner } from '../../components/owner-card/owner-card';
import { OwnerService } from '../../services/owner-service'; // <-- Tu servicio

@Component({
  selector: 'app-duenos',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    TopBar,
    NewOwner,
    OwnerCard
  ],
  templateUrl: './duenos.html',
  styleUrl: './duenos.css',
})
export class Duenos implements OnInit {

  private ownerService = inject(OwnerService);
  private cdr = inject(ChangeDetectorRef);

  isRegistrerOpen: boolean = false;
  cargando: boolean = true;

  // Listas para el filtrado reactivo
  listaDuenosOriginal: Owner[] = [];
  listaDuenosFiltrados: Owner[] = [];
  filtroDni: string = '';

  ngOnInit(): void {
    this.obtenerDuenos();
  }

  obtenerDuenos(): void {
    this.cargando = true;
    this.ownerService.getOwners().subscribe({
      next: (data) => {
        this.listaDuenosOriginal = data;
        this.filtrarDuenos(); // Aplica el filtro actual sobre los nuevos datos
        this.cargando = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error al obtener los dueños de la base de datos:', err);
        this.cargando = false;
        this.cdr.detectChanges();
      }
    });
  }

  filtrarDuenos(): void {
    const texto = this.filtroDni.toLowerCase().trim();

    if (!texto) {
      this.listaDuenosFiltrados = [...this.listaDuenosOriginal];
      return;
    }

    this.listaDuenosFiltrados = this.listaDuenosOriginal.filter(d =>
      d.dni.toLowerCase().includes(texto)
    );
  }

  openRegistrer() {
    this.isRegistrerOpen = true;
  }

  closeRegistrer() {
    this.isRegistrerOpen = false;
  }

  agregarNuevoDueno(nuevoDueno: Owner): void {
    this.ownerService.createOwner(nuevoDueno).subscribe({
      next: (res) => {
        console.log('Dueño guardado en MySQL:', res);
        this.closeRegistrer();
        this.obtenerDuenos(); // Recarga la lista desde el backend
      },
      error: (err) => console.error('Error al guardar el dueño:', err)
    });
  }

  eliminarDueno(id: number) {
    const confirmacion = window.confirm('¿Estás seguro de que quieres eliminar este dueño?');

    if (confirmacion) {
      this.ownerService.deleteOwner(id).subscribe({
        next: () => {
          this.listaDuenosOriginal = this.listaDuenosOriginal.filter(d => d.id !== id);
          this.filtrarDuenos();
          this.cdr.detectChanges();
        },
        error: (err) => console.error('Error al eliminar el dueño:', err)
      });
    }
  }
}
