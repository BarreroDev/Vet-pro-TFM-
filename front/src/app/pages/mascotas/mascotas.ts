import { Component } from '@angular/core';
import {TopBar} from '../../components/top-bar/top-bar';
import {LeftBar} from '../../components/left-bar/left-bar';

import {NewPet} from '../../components/new-pet/new-pet';

@Component({
  selector: 'app-mascotas',
  imports: [
    TopBar,
    LeftBar,
    NewPet
  ],
  templateUrl: './mascotas.html',
  styleUrl: './mascotas.css',
})
export class Mascotas {

  isRegistrerOpen: boolean = false;

  listaMascotas: any[] = [

    {
      id:1,
      nombre: 'Alma',
      especie: 'perro',
      raza: 'Labrador retivere',
      edad: '12 años',
      peso: '36 kg'
    },
    {
      id:2,
      nombre: 'Cuco',
      especie: 'pájaro',
      raza: 'Guacamayo',
      edad: '9 años',
      peso: '4,3 kg'
    }
  ];

  openRegistrer() {
    this.isRegistrerOpen = true;
  }

  closeRegistrer() {
    this.isRegistrerOpen = false;
  }

  agregarNuevaMascota(nuevaMascota:any){
    this.listaMascotas = [...this.listaMascotas, nuevaMascota];
    this.closeRegistrer()
  }

  eliminarMascota(id: string) {
    const confirmacion = window.confirm('¿Estas segura que lo quieres eliminar?')

    if (confirmacion) {
      this.listaMascotas = this.listaMascotas.filter(d => d.id === id);
    }
  }
}
