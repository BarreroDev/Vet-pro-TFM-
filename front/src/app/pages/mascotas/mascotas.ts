import {Component, inject, OnInit } from '@angular/core';
import {TopBar} from '../../components/top-bar/top-bar';
import {LeftBar} from '../../components/left-bar/left-bar';
import {MascotaService} from '../../services/MascotaService';
import {Mascota} from '../../models/mascotaModel';
import {NewPet} from '../../components/new-pet/new-pet';

@Component({
  selector: 'app-mascotas',
  imports: [
    TopBar,
    LeftBar,
    NewPet
  ],
  templateUrl: './mascotas.html',
  styleUrl: './mascotas.css',
})




export class Mascotas implements OnInit {

  private mascotaService = inject(MascotaService);

  isRegistrerOpen: boolean = false;

  listaMascotas: any[] = [];
  cargando: boolean = true;

  ngOnInit() {
    this.obtenerMascota();
  }

  openRegistrer() {
    this.isRegistrerOpen = true;
  }

  closeRegistrer() {
    this.isRegistrerOpen = false;
  }

  agregarNuevaMascota(nuevaMascota:any){
    this.listaMascotas = [...this.listaMascotas, nuevaMascota];
    this.closeRegistrer()
  }

  eliminarMascota(id: string) {
    const confirmacion = window.confirm('¿Estas segura que lo quieres eliminar?')

    if (confirmacion) {
      this.listaMascotas = this.listaMascotas.filter(d => d.id === id);
    }
  }
}
