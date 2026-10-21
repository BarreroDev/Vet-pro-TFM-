import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TopBar } from '../../components/top-bar/top-bar';
import { NewVet } from '../../components/new-vet/new-vet';
import { VetCard, Vet } from '../../components/vet-card/vet-card';

@Component({
  selector: 'app-veterinarios',
  standalone: true,
  imports: [
    CommonModule,
    TopBar,
    NewVet,
    VetCard
  ],
  templateUrl: './veterinarios.html',
  styleUrl: './veterinarios.css',
})
export class Veterinarios implements OnInit {

  isRegistrerOpen: boolean = false;
  cargando: boolean = false;

  // Array de datos falsos para probar la vista y las VetCards
  listaVeterinarios: Vet[] = [];

  ngOnInit(): void {
    this.cargarDatosPrueba();
  }

  cargarDatosPrueba(): void {
    this.listaVeterinarios = [
      {
        id: 1,
        nombre: 'Carlos',
        apellidos: 'Mendoza Ríos',
        numeroColegiado: 'COL-4102',
        telefono: '654987321',
        email: 'cmendoza@vetpro.com',
        especialidad: 'Cirugía y Traumatología',
        activo: true,
        fotoUrl: ''
      },
      {
        id: 2,
        nombre: 'Lucía',
        apellidos: 'Gómez Peralta',
        numeroColegiado: 'COL-4109',
        telefono: '612345678',
        email: 'lgomez@vetpro.com',
        especialidad: 'Dermatología',
        activo: true,
        fotoUrl: ''
      },
      {
        id: 3,
        nombre: 'Elena',
        apellidos: 'Sánchez Varo',
        numeroColegiado: 'COL-3891',
        telefono: '699887766',
        email: 'esanchez@vetpro.com',
        especialidad: 'Medicina General',
        activo: false,
        fotoUrl: ''
      }
    ];
  }

  openRegistrer(): void {
    this.isRegistrerOpen = true;
  }

  closeRegistrer(): void {
    this.isRegistrerOpen = false;
  }

  // Simulación para añadir a la lista local cuando rellenes el formulario modal
  onGuardarVeterinario(nuevoVet: any): void {
    this.listaVeterinarios.push({
      id: Date.now(),
      ...nuevoVet
    });
    this.closeRegistrer();
  }
}
