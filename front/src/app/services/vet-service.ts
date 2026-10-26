import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Vet } from '../components/vet-card/vet-card'; // Ajusta la ruta a tu modelo/tarjeta

@Injectable({
  providedIn: 'root'
})
export class VetService {

  private http = inject(HttpClient);

  // Asegúrate de que coincida exactamente con la URL de tu @RequestMapping en Java
  private apiUrl = 'http://localhost:8080/veterinarios';

  // 1. Obtener todos los veterinarios desde MySQL
  getVets(): Observable<Vet[]> {
    return this.http.get<Vet[]>(this.apiUrl);
  }

  // 2. Crear un nuevo veterinario
  createVet(vet: Vet): Observable<Vet> {
    return this.http.post<Vet>(this.apiUrl, vet);
  }

  // 3. Eliminar un veterinario por ID
  deleteVet(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
