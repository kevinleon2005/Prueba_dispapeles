import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DocumentType } from '../models/customer';

@Injectable({ providedIn: 'root' })
export class DocumentTypeService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080/api/document-types';

  listar(): Observable<DocumentType[]> {
    return this.http.get<DocumentType[]>(this.apiUrl);
  }
}
