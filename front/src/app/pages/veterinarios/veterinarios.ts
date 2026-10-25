import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; // <-- 1. Importante para ngModel
import { TopBar } from '../../components/top-bar/top-bar';
import { NewVet } from '../../components/new-vet/new-vet';
import { VetCard, Vet } from '../../components/vet-card/vet-card';

@Component({
  selector: 'app-veterinarios',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule, // <-- 2. Añadido aquí
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

  // Listas de datos
  listaVeterinariosOriginal: Vet[] = [];
  listaVeterinariosFiltrados: Vet[] = [];
  filtroBusqueda: string = ''; // Texto que escribes en la barra

  ngOnInit(): void {
    this.cargarDatosPrueba();
  }

  cargarDatosPrueba(): void {
    this.listaVeterinariosOriginal = [
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

    // Al iniciar, mostramos todos los registros
    this.listaVeterinariosFiltrados = [...this.listaVeterinariosOriginal];
  }

  // Lógica de filtrado en tiempo real por DNI o Nº Colegiado
  filtrarVeterinarios(): void {
    const texto = this.filtroBusqueda.toLowerCase().trim();

    if (!texto) {
      this.listaVeterinariosFiltrados = [...this.listaVeterinariosOriginal];
      return;
    }

    this.listaVeterinariosFiltrados = this.listaVeterinariosOriginal.filter(v => {
      const dni = (v as any).dni || '';
      const colegiado = v.numeroColegiado || '';
      return dni.toLowerCase().includes(texto) || colegiado.toLowerCase().includes(texto);
    });
  }

  openRegistrer(): void {
    this.isRegistrerOpen = true;
  }

  closeRegistrer(): void {
    this.isRegistrerOpen = false;
  }

  onGuardarVeterinario(nuevoVet: any): void {
    const vetCreado = {
      id: Date.now(),
      ...nuevoVet
    };
    this.listaVeterinariosOriginal.push(vetCreado);
    this.filtrarVeterinarios(); // Actualiza la vista actual
    this.closeRegistrer();
  }
}
