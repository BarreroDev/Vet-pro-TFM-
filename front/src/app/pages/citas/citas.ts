import { Component } from '@angular/core';
import { TopBar } from '../../components/top-bar/top-bar';
import { NewAppointment } from '../../components/new-appointment/new-appointment';
import { FullCalendarModule } from '@fullcalendar/angular';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';
import {citasModel} from '../../models/citasModel';

@Component({
  selector: 'app-citas',
  standalone: true,
  imports: [
    TopBar,
    NewAppointment,
    FullCalendarModule
  ],
  templateUrl: './citas.html',
  styleUrl: './citas.css'
})
export class Citas {

  isRegistrerOpen: boolean = false;

  citasGuardadas: citasModel[] = [

  ];

  calendarOptions: any = {
    plugins: [dayGridPlugin, timeGridPlugin,interactionPlugin],
    initialView: 'timeGridDay',
    locale: 'es',
    firstDay: 1,

    slotMinTime: '08:00:00',
    slotMaxTime: '21:00:00',
    slotDuration: '00:30:00',

    slotLabelFormat: {
      hour: '2-digit',
      minute: '2-digit',
      hour12: false
    },


    height: 'auto',
    allDaySlot: false,

    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'timeGridDay,timeGridWeek,dayGridMonth'
    },
    events: this.citasGuardadas,

    dateClick: this.handleDateClick.bind(this)
  };

  openRegistrer() {
    this.isRegistrerOpen = true;
  }

  closeRegistrer() {
    this.isRegistrerOpen = false;
  }

  handleDateClick(arg: any): void {
    console.log('Fecha y hora seleccionada: ' + arg.dateStr);
    this.openRegistrer();
  }

  agregarNuevaCita(citaDelFormulario: { mascota: string; motivo: string; fecha: string; hora: string }): void {
    const fechaHoraIso = `${citaDelFormulario.fecha}T${citaDelFormulario.hora}:00`;

    const nuevaCitaEvent: citasModel = {
      fechaHora: fechaHoraIso,
      motivo: citaDelFormulario.motivo,
      title: `${citaDelFormulario.mascota} - ${citaDelFormulario.motivo}`,
      start: fechaHoraIso,
      color: '#0d9488'
    };

    this.citasGuardadas = [...this.citasGuardadas, nuevaCitaEvent];
    this.calendarOptions = {
      ...this.calendarOptions,
      events: this.citasGuardadas
    };
    this.closeRegistrer();
  }
}
