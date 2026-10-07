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

  dni: string = '';
  name: string = '';
  mascota: string = '';
  veterinario: string = '';
  consulta: string = '';
  motivo: string = '';
  fecha: string = '';
  hora: string = '';

  // Mascotas filtradas para el desplegable
  mascotasDisponibles: Array<{ id: number; nombre: string; especie: string }> = [];

  // Datos de prueba para simular el comportamiento mientras terminas el Front
  private baseDatosMockDueños = [
    {
      dni: '12345678A',
      nombre: 'Paco González',
      mascotas: [
        { id: 1, nombre: 'Alma', especie: 'Perro' },
        { id: 2, nombre: 'Thor', especie: 'Gato' }
      ]
    },
    {
      dni: '87654321B',
      nombre: 'María López',
      mascotas: [
        { id: 3, nombre: 'Luna', especie: 'Conejo' }
      ]
    }
  ];

  // Lista de veterinarios activos (en el futuro vendrá del VeterinarioService)
  listaVeterinariosActivos = [
    { id: 1, nombre: 'Dra. Laura', apellidos: 'García', activo: true },
    { id: 2, nombre: 'Dr. Carlos', apellidos: 'Pérez', activo: true }
  ];

  /**
   * Se ejecuta cada vez que el usuario escribe en el campo DNI
   */
  onDniChange(): void {
    const dniLimpio = this.dni.trim().toUpperCase();
    const dueñoEncontrado = this.baseDatosMockDueños.find(d => d.dni === dniLimpio);

    if (dueñoEncontrado) {
      this.name = dueñoEncontrado.nombre;
      this.mascotasDisponibles = dueñoEncontrado.mascotas;
    } else {
      this.name = '';
      this.mascotasDisponibles = [];
      this.mascota = '';
    }
  }

  onGuardar(): void {
    const datosFormulario = {
      dni: this.dni,
      name: this.name,
      mascota: this.mascota,
      veterinario: this.veterinario,
      consulta: this.consulta,
      motivo: this.motivo,
      fecha: this.fecha,
      hora: this.hora
    };
    this.guardarCita.emit(datosFormulario);
  }

  onClose(): void {
    this.close.emit();
  }
}
