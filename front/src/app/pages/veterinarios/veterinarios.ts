import { Component } from '@angular/core';
import {TopBar} from '../../components/top-bar/top-bar';
import {LeftBar} from '../../components/left-bar/left-bar';
import {NewPet} from '../../components/new-pet/new-pet';

@Component({
  selector: 'app-veterinarios',
  imports: [
    TopBar,
    LeftBar,
    NewPet
  ],
  templateUrl: './veterinarios.html',
  styleUrl: './veterinarios.css',
})
export class Veterinarios {

  listaVeterinario: any[] = [

    {
      id: '1',
      nombre: 'Laura',
      apellidos: 'García Gómez',
      telefono: '611223344',
      email: 'laura.garcia@vetpro.com',
      numeroColegiado:'VET-102'
    },
    {
      id: '2',
      nombre: 'Carlos',
      apellidos: 'Sánchez Ruiz',
      telefono: '622334455',
      email: 'carlos.sanchez@vetpro.com',
      numeroColegiado:'VET-105'
    }
  ];

  eliminarVeterinario(id: string) {
    const confirmacion = window.confirm('¿Estas segura que lo quieres eliminar?')

    if (confirmacion) {
      this.listaVeterinario = this.listaVeterinario.filter(d => d.id === id);
    }
  }

}
