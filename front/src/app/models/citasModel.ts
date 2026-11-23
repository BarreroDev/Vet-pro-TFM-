import {Mascota} from './mascotaModel';
import {VeterinarioModel} from './veterinarioModel';

export interface citasModel {

  id?: number;
  fechaHora: string;
  motivo: string;
  diagnostico?: string;
  tratamiento?: string;
  mascota?: Mascota;
  veterinario?: VeterinarioModel;
  title?: string;
  start?: string;
  color?: string;
}
