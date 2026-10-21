import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

export interface Vet {
  id?: number;
  nombre: string;
  apellidos: string;
  numeroColegiado: string;
  telefono: string;
  email: string;
  especialidad?: string;
  activo?: boolean;
  fotoUrl?: string;
}

@Component({
  selector: 'app-vet-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './vet-card.html',
  styleUrl: './vet-card.css'
})
export class VetCard {
  @Input({ required: true }) vet!: Vet;

  isDetailOpen: boolean = false;

  openDetail(): void {
    this.isDetailOpen = true;
  }

  closeDetail(): void {
    this.isDetailOpen = false;
  }
}
