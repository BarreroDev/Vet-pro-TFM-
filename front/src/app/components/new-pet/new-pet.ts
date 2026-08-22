import {Component, EventEmitter, Output} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-new-pet',
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './new-pet.html',
  styleUrl: './new-pet.css',
  standalone: true
})
export class NewPet {

  @Output() close = new EventEmitter<void>();
  @Output() guardarMascota = new EventEmitter<any>();

  nombre: string = '';
  especie: string = '';
  raza: string = '';
  edad: string = '';
  peso: string = '';


  onGuardar(){
    console.log('¡BOTÓN PULSADO CORRECTAMENTE!')
    const datosFormulario = {

      nombre: this.nombre,
      especie: this.especie,
      raza: this.raza,
      edad: this.edad,
      peso: this.peso
    }
    this.guardarMascota.emit(datosFormulario);
  }


  onClose(): void{
    this.close.emit();
  }
}
