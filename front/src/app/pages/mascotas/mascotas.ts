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
    this.obtenerMascotas();
  }

  obtenerMascotas(): void {
    this.cargando = true;
    this.mascotaService.getMascotas().subscribe({
      next: (data) => {
        this.listaMascotas = data;
        this.cargando = false;
        console.log('Mascotas recibidas desde MySQL:', data);
      },
      error: (err) => {
        console.error('Error al obtener mascotas de la API:', err);
        this.cargando = false;
      }
    })
  }

    openRegistrer()
    {
      this.isRegistrerOpen = true;
    }

    closeRegistrer()
    {
      this.isRegistrerOpen = false;
    }

  agregarNuevaMascota(nuevaMascota: Mascota) {
    this.mascotaService.createMascota(nuevaMascota).subscribe({
      next: (mascotaCreada) => {
        
        this.listaMascotas = [...this.listaMascotas, mascotaCreada];
        this.closeRegistrer();
      },
      error: (err) => console.error('Error al guardar la nueva mascota:', err)
    });
  }

  eliminarMascota(id: number) {
    const confirmacion = window.confirm('¿Estás seguro de que deseas eliminar esta mascota?');

    if (confirmacion) {
      this.mascotaService.deleteMascota(id).subscribe({
        next: () => {

          this.listaMascotas = this.listaMascotas.filter(m => m.id !== id);
        },
        error: (err) => console.error('Error al eliminar la mascota:', err)
      });
    }

  }
}
