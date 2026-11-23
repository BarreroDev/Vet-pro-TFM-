import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {VeterinarioModel} from '../models/veterinarioModel';

@Injectable({
  providedIn: 'root'
})
export class VetService {

  private http = inject(HttpClient);


  private apiUrl = 'http://localhost:8080/veterinarios';

  // Obtener todos los veterinarios desde MySQL
  getVets(): Observable<VeterinarioModel[]> {
    return this.http.get<VeterinarioModel[]>(this.apiUrl);
  }

  //Crear un nuevo veterinario
  createVet(vet: VeterinarioModel, file?: File): Observable<VeterinarioModel> {
    const formData = new FormData();
    formData.append(
      'veterinario',
      new Blob([JSON.stringify(vet)], { type: 'application/json' }),
    );

    if (file){
      formData.append('file', file);
    }

    return this.http.post<VeterinarioModel>(this.apiUrl, formData);
  }

  //Eliminar un veterinario por ID
  deleteVet(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
