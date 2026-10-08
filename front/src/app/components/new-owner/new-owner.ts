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

  nombre: string = '';
  dni: string = '';
  apellidos: string = '';
  telefono: string = '';
  email: string = '';
  fechaNacimeniento: string = '';


  archivoSeleccionado: File | null = null;

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.archivoSeleccionado = input.files[0];
    }
  }

  onGuardar(){
    console.log('¡BOTÓN PULSADO CORRECTAMENTE!')
    const datosFormulario = {

      dni: this.dni,
      nombre: this.nombre,
      apellidos: this.apellidos,
      telefono: this.telefono,
      email: this.email,
      fechaNacimeniento: this.fechaNacimeniento
    }
    this.guardarDueño.emit(datosFormulario)
    this.onClose()
  }


  onClose(): void{
    this.close.emit();
  }

}
