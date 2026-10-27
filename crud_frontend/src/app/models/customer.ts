export interface Customer {
  id_customer?: number;
  documentNumber: number;
  nameCustomer: string;
  lastnameCustomer: string;
  ageCustomer: number;
  phone: number;
  address: string;
  documentTypeId: number;
  documentType?: DocumentType;
}

export interface DocumentType {
  id: number;
  nameDocumentType: string;
  prefix: string;
}
