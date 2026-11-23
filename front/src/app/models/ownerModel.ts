import {Mascota} from './mascotaModel';

export interface OwnerModel {
  id?: number;
  dni: string;
  nombre: string;
  apellidos: string;
  telefono: string;
  email: string;
  direccion?: string;
  fechaNacimiento?: string;
  fotoUrl?: string;
  mascota?: Mascota[];

}
