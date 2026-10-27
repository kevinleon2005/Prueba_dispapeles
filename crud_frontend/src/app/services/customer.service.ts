import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from '../models/customer';

@Injectable({ providedIn: 'root' })
export class CustomerService {
    private readonly http = inject(HttpClient);
    private readonly apiUrl = 'http://localhost:8080/api/customers';

    listar(): Observable<Customer[]> {
        return this.http.get<Customer[]>(this.apiUrl);
    }

    crear(customer: Customer): Observable<Customer> {
        return this.http.post<Customer>(this.apiUrl, customer);
    }

    actualizar(id: number, customer: Customer): Observable<Customer> {
        return this.http.put<Customer>(`${this.apiUrl}/${id}`, customer);
    }

    eliminar(id: number): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }
}
