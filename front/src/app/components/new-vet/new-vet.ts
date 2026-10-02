import {Component, EventEmitter, Output} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-new-vet',
    imports: [
        FormsModule,
        ReactiveFormsModule
    ],
  templateUrl: './new-vet.html',
  styleUrl: './new-vet.css',
})
export class NewVet {
  @Output() close = new EventEmitter<void>();
  @Output() guardarVete = new EventEmitter<any>();

  numeroColegiado: string = '';
  nombre: string = '';
  apellidos: string = '';
  telefono: string = '';
  email: string = '';
  fechaNacimeniento: string = '';
  fotoUrl: String = '';


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

      numeroColegiado: this.numeroColegiado,
      nombre: this.nombre,
      apellidos: this.apellidos,
      telefono: this.telefono,
      email: this.email,
      fechaNacimeniento: this.fechaNacimeniento,
      fotoUrl: this.fotoUrl,
    }
    this.guardarVete.emit(datosFormulario)

    this.onClose()
  }


  onClose(): void{
    this.close.emit();
  }

}
