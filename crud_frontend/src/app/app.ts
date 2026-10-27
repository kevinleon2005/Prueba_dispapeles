import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { PLATFORM_ID } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Customer, DocumentType } from './models/customer';
import { CustomerService } from './services/customer.service';
import { DocumentTypeService } from './services/document-type.service';

@Component({
  selector: 'app-root',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
  private readonly formBuilder = inject(FormBuilder);
  private readonly customerService = inject(CustomerService);
  private readonly documentTypeService = inject(DocumentTypeService);
  private readonly platformId = inject(PLATFORM_ID);

  customers: Customer[] = [];
  documentTypes: DocumentType[] = [];
  editingId: number | null = null;
  loading = false;
  saving = false;
  message = '';
  error = '';
  pendingDelete: Customer | null = null;
  deletingId: number | null = null;
  loadingDocumentTypes = false;

  form = this.formBuilder.nonNullable.group({
    documentTypeId: [0, [Validators.required, Validators.min(1)]],
    documentNumber: [0, [Validators.required, Validators.min(1)]],
    nameCustomer: ['', [Validators.required, Validators.maxLength(100)]],
    lastnameCustomer: ['', [Validators.required, Validators.maxLength(100)]],
    ageCustomer: [0, [Validators.required, Validators.min(0), Validators.max(150)]],
    phone: [0, [Validators.required, Validators.min(1)]],
    address: ['', [Validators.required, Validators.maxLength(100)]]
  });

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.loadDocumentTypes();
      this.loadCustomers();
    }
  }

  loadDocumentTypes(): void {
    this.loadingDocumentTypes = true;
    this.documentTypeService.listar().subscribe({
      next: (types) => {
        this.documentTypes = types;
        this.loadingDocumentTypes = false;
      },
      error: () => {
        this.error = 'No se pudieron cargar los tipos de documento.';
        this.loadingDocumentTypes = false;
      }
    });
  }

  loadCustomers(): void {
    this.loading = true;
    this.customerService.listar().subscribe({
      next: (customers) => { this.customers = customers; this.loading = false; },
      error: () => { this.error = 'No se pudieron cargar los clientes.'; this.loading = false; }
    });
  }

  save(): void {
    if (this.form.invalid || this.saving) {
      this.form.markAllAsTouched();
      return;
    }

    this.saving = true;
    this.error = '';
    const customer = this.form.getRawValue() as Customer;
    const request = this.editingId === null
      ? this.customerService.crear(customer)
      : this.customerService.actualizar(this.editingId, customer);

    request.subscribe({
      next: (savedCustomer) => {
        this.message = this.editingId === null ? 'Cliente creado correctamente.' : 'Cliente actualizado correctamente.';
        this.saving = false;
        if (this.editingId === null) {
          this.customers = [savedCustomer, ...this.customers];
        } else {
          this.customers = this.customers.map((item) =>
            item.id_customer === savedCustomer.id_customer ? savedCustomer : item
          );
        }
        this.resetForm();
      },
      error: () => { this.error = 'No se pudo guardar el cliente.'; this.saving = false; }
    });
  }

  edit(customer: Customer): void {
    this.editingId = customer.id_customer ?? null;
    this.form.patchValue(customer);
    this.message = '';
    this.error = '';
  }

  remove(customer: Customer): void {
    if (!customer.id_customer) return;
    this.pendingDelete = customer;
    this.message = '';
    this.error = '';
  }

  confirmDelete(): void {
    if (!this.pendingDelete?.id_customer) return;

    const customerToDelete = this.pendingDelete;
    const customerId = customerToDelete.id_customer;
    if (customerId === undefined) return;
    this.deletingId = customerId;
    this.error = '';
    this.message = '';
    this.customerService.eliminar(customerId).subscribe({
      next: () => {
        this.customers = this.customers.filter((item) => item.id_customer !== customerToDelete.id_customer);
        this.message = 'Cliente eliminado correctamente.';
        this.pendingDelete = null;
        this.deletingId = null;
      },
      error: () => {
        this.error = 'No se pudo eliminar el cliente. Inténtalo nuevamente.';
        this.deletingId = null;
      }
    });
  }

  cancelDelete(): void {
    if (this.deletingId === null) {
      this.pendingDelete = null;
    }
  }

  resetForm(): void {
    this.editingId = null;
    this.form.reset({ documentTypeId: 0, documentNumber: 0, nameCustomer: '', lastnameCustomer: '', ageCustomer: 0, phone: 0, address: '' });
  }
}
