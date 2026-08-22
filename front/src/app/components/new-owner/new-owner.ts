import {Component, EventEmitter, Output} from '@angular/core';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-new-owner',
  imports: [
    FormsModule
  ],
  templateUrl: './new-owner.html',
  styleUrl: './new-owner.css',
  standalone: true
})
export class NewOwner {

  @Output() close = new EventEmitter<void>();
  @Output() guardarDueño = new EventEmitter<any>();

  name: string = '';
  dni: string = '';
  apellidos: string = '';
  telefono: string = '';
  email: string = '';
  fechaNacimeniento: string = '';


  onGuardar(){
    console.log('¡BOTÓN PULSADO CORRECTAMENTE!')
    const datosFormulario = {

      dni: this.dni,
      name: this.name,
      apellidos: this.apellidos,
      telefono: this.telefono,
      email: this.email,
      fechaNacimeniento: this.fechaNacimeniento
    }
    this.guardarDueño.emit(datosFormulario);
  }


  onClose(): void{
    this.close.emit();
  }

}
