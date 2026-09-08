import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Mascota } from '../models/mascotaModel'; // Ajusta la ruta si la carpeta models está en otro nivel

@Injectable({
  providedIn: 'root'
})
export class MascotaService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/mascotas';

  /**
   * Obtiene la lista completa de mascotas desde Spring Boot
   */
  getMascotas(): Observable<Mascota[]> {
    return this.http.get<Mascota[]>(this.apiUrl);
  }

  /**
   * Obtiene una mascota específica por su ID
   */
  getMascotaById(id: number): Observable<Mascota> {
    return this.http.get<Mascota>(`${this.apiUrl}/${id}`);
  }

  /**
   * Registra una nueva mascota en la base de datos
   */
  createMascota(mascota: Mascota): Observable<Mascota> {
    return this.http.post<Mascota>(this.apiUrl, mascota);
  }

  /**
   * Actualiza los datos de una mascota existente
   */
  updateMascota(id: number, mascota: Mascota): Observable<Mascota> {
    return this.http.put<Mascota>(`${this.apiUrl}/${id}`, mascota);
  }

  /**
   * Elimina una mascota de la base de datos por su ID
   */
  deleteMascota(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
