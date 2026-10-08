import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

export interface Pet {
  id?: number;
  nombre: string;
  especie: string;
  raza?: string;
  edad?: number;
  peso?: number;
  duenoNombre?: string;
  duenoDni?: string;
  fotoUrl?: string;
}

@Component({
  selector: 'app-pet-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pet-card.html',
  styleUrl: './pet-card.css'
})
export class PetCard {
  @Input({ required: true }) pet!: Pet;

  isDetailOpen: boolean = false;

  openDetail(): void {
    this.isDetailOpen = true;
  }

  closeDetail(): void {
    this.isDetailOpen = false;
  }
}
