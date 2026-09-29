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

  dniDueno: string = '';
  nombre: string = '';
  especie: string = '';
  raza: string = '';
  edad: string = '';
  peso: string = '';
  fotoUrl: string = '';


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

      dniDueno: this.dniDueno,
      mascota: {
        nombre: this.nombre,
        especie: this.especie,
        raza: this.raza,
        edad: this.edad,
        peso: this.peso,
        fotoUrl: this.fotoUrl,
      },
      archivoFoto:this.archivoSeleccionado
    }
    this.guardarMascota.emit(datosFormulario)

    this.onClose()
  }


  onClose(): void{
    this.close.emit();
  }
}
