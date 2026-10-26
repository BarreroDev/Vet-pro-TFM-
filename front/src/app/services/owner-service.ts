import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Owner } from '../components/owner-card/owner-card'; // O la ruta donde tengas tu interface

@Injectable({
  providedIn: 'root'
})
export class OwnerService {

  private http = inject(HttpClient);

  // URL de tu controlador REST en Spring Boot (ajusta el puerto si es necesario)
  private apiUrl = 'http://localhost:8080/duenos';

  // 1. Obtener todos los dueños desde MySQL
  getOwners(): Observable<Owner[]> {
    return this.http.get<Owner[]>(this.apiUrl);
  }

  // 2. Crear un nuevo dueño
  createOwner(owner: Owner): Observable<Owner> {
    return this.http.post<Owner>(this.apiUrl, owner);
  }

  // 3. Eliminar un dueño por su ID (o por DNI según lo tengas en tu backend)
  deleteOwner(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
