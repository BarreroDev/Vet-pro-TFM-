import { Component } from '@angular/core';
import {TopBar} from '../../components/top-bar/top-bar';
import {LeftBar} from '../../components/left-bar/left-bar';
import {FullCalendarModule} from '@fullcalendar/angular';
import {NewAppointment} from '../../components/new-appointment/new-appointment';
import {NewOwner} from '../../components/new-owner/new-owner';

@Component({
  selector: 'app-duenos',
  imports: [
    TopBar,
    LeftBar,
    FullCalendarModule,
    NewAppointment,
    NewOwner
  ],
  templateUrl: './duenos.html',
  styleUrl: './duenos.css',
})
export class Duenos {

  isRegistrerOpen: boolean = false;

  listaDuenos: any[] = [

    {
      dni: '34546709-E',
      name: 'Pedro',
      apellidos: 'Sánchez Fernández',
      telefono: '655453421',
      email: 'sanchezpedro@gmail.com',
      fechaNacimeniento: '1992-08-24'
    },
    {
      dni: '23221144-C',
      name: 'Álvaro',
      apellidos: 'Román Pérez',
      telefono: '703344567',
      email: 'romanalvaro@gmail.com',
      fechaNacimeniento: '1990-05-30'
    }
  ];

  openRegistrer() {
    this.isRegistrerOpen = true;
  }

  closeRegistrer() {
    this.isRegistrerOpen = false;
  }

  agregarNuevoDueno(nuevoDueno:any):void{
    this.listaDuenos = [...this.listaDuenos, nuevoDueno];
    this.closeRegistrer()
  }

  eliminarDueno(dni: string) {
    const confirmacion = window.confirm('¿Estas segura que lo quieres eliminar?')

    if (confirmacion) {
      this.listaDuenos = this.listaDuenos.filter(d => d.dni === dni);
    }
  }
}
