import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { OwnerModel } from '../models/ownerModel';

@Injectable({
  providedIn: 'root'
})
export class OwnerService {

  private http = inject(HttpClient);


  private apiUrl = 'http://localhost:8080/duenos';

  // 1. Obtener todos los dueños desde MySQL
  getOwners(): Observable<OwnerModel[]> {
    return this.http.get<OwnerModel[]>(this.apiUrl);
  }

  // 2. Crear un nuevo dueño
  createOwner(owner: OwnerModel): Observable<OwnerModel> {
    return this.http.post<OwnerModel>(this.apiUrl, owner);
  }

  // 3. Eliminar un dueño por su ID (o por DNI según lo tengas en tu backend)
  deleteOwner(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
