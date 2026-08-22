import {Component, EventEmitter, model, Output} from '@angular/core';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-new-appointment',
  imports: [FormsModule],
  templateUrl: './new-appointment.html',
  styleUrl: './new-appointment.css',
  standalone: true
})
export class NewAppointment {
  @Output() close = new EventEmitter<void>();
  @Output() guardarCita = new EventEmitter<any>();

  name: string = '';
  dni: string = '';
  mascota: string = '';
  motivo: string = '';
  fecha: string = '';
  hora: string = '';

  onGuardar(){
    console.log('¡BOTÓN PULSADO CORRECTAMENTE!')
    const datosFormulario = {
      name: this.name,
      dni: this.dni,
      mascota: this.mascota,
      motivo: this.motivo,
      fecha: this.fecha,
      hora: this.hora
    }
    this.guardarCita.emit(datosFormulario);
  }


  onClose(): void{
    this.close.emit();
  }


}
