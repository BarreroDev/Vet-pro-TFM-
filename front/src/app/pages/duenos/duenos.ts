import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; // <-- 1. Importante para el buscador
import { TopBar } from '../../components/top-bar/top-bar';
import { NewOwner } from '../../components/new-owner/new-owner';
import { OwnerCard } from '../../components/owner-card/owner-card';

@Component({
  selector: 'app-duenos',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule, // <-- 2. Añadido aquí
    TopBar,
    NewOwner,
    OwnerCard
  ],
  templateUrl: './duenos.html',
  styleUrl: './duenos.css',
})
export class Duenos implements OnInit {

  isRegistrerOpen: boolean = false;

  // Listas de datos
  listaDuenosOriginal: any[] = [
    {
      dni: '34546709-E',
      nombre: 'Pedro',
      apellidos: 'Perez Fernández',
      telefono: '655453421',
      email: 'sanchezpedro@gmail.com',
      fechaNacimeniento: '1992-08-24'
    },
    {
      dni: '23221144-C',
      nombre: 'Álvaro',
      apellidos: 'Román Pérez',
      telefono: '703344567',
      email: 'romanalvaro@gmail.com',
      fechaNacimeniento: '1990-05-30'
    }
  ];

  listaDuenosFiltrados: any[] = [];
  filtroDni: string = ''; // Texto que escribes en la barra

  ngOnInit(): void {
    // Al arrancar, inicializamos la lista filtrada con todos los dueños
    this.listaDuenosFiltrados = [...this.listaDuenosOriginal];
  }

  openRegistrer() {
    this.isRegistrerOpen = true;
  }

  closeRegistrer() {
    this.isRegistrerOpen = false;
  }

  filtrarDuenos(): void {
    const texto = this.filtroDni.toLowerCase().trim();

    if (!texto) {
      this.listaDuenosFiltrados = [...this.listaDuenosOriginal];
      return;
    }

    // Filtra en tiempo real comparando el DNI
    this.listaDuenosFiltrados = this.listaDuenosOriginal.filter(d =>
      d.dni.toLowerCase().includes(texto)
    );
  }

  agregarNuevoDueno(nuevoDueno: any): void {
    this.listaDuenosOriginal.push(nuevoDueno);
    this.filtrarDuenos(); // Actualiza la vista actual
    this.closeRegistrer();
  }

  eliminarDueno(dni: string) {
    const confirmacion = window.confirm('¿Estas segura que lo quieres eliminar?');

    if (confirmacion) {
      // Corregido !== para que elimine correctamente el elemento
      this.listaDuenosOriginal = this.listaDuenosOriginal.filter(d => d.dni !== dni);
      this.filtrarDuenos(); // Actualiza el filtrado tras borrar
    }
  }
}
