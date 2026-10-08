import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

export interface Owner {
  id?: number;
  dni: string;
  nombre: string;
  apellidos: string;
  telefono: string;
  email: string;
  direccion?: string;
  fotoUrl?: string;
  mascotas?: Array<{ id: number; nombre: string; especie: string }>;
}

@Component({
  selector: 'app-owner-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './owner-card.html',
  styleUrl: './owner-card.css'
})
export class OwnerCard {
  @Input({ required: true }) owner!: Owner;


  isDetailOpen: boolean = false;

  openDetail(): void {
    this.isDetailOpen = true;
  }

  closeDetail(): void {
    this.isDetailOpen = false;
  }
}
