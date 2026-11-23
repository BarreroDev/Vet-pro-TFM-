export interface VeterinarioModel {
  id?: number;
  nombre: string;
  apellidos?: string;
  numeroColegiado: string;
  telefono: string;
  email: string;
  password?: string;
  especialidad?: string;
  activo?: boolean;
  fotoUrl?: string;
  rol?: string;
}
