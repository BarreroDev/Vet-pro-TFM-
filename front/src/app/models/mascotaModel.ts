import{OwnerModel} from './ownerModel';

export interface Mascota {
  id?: number;
  nombre: string;
  especie: string;
  raza?: string;
  edad?: string;
  peso?: string;
  fotoUrl?: string;
  dueno?: OwnerModel;
  duenoDni?: string;
  dniDueno?: string;
  duenoNombre?: string;
}
