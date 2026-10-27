package com.crud.crud_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomerDTO {
    
    private Long id_customer;
    
    @jakarta.validation.constraints.NotNull(message = "El número de documento es obligatorio")
    private Long documentNumber;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size (max = 100, message = "El nombre del cliente no puede tener más de 100 caracteres")
    private String nameCustomer;

    @NotBlank(message = "El apellido del cliente es obligatorio")
    @Size (max = 100, message = "El apellido del cliente no puede tener más de 100 caracteres")
    private String lastnameCustomer;

    @jakarta.validation.constraints.NotNull(message = "La edad del cliente es obligatoria")
    @jakarta.validation.constraints.Min(value = 0, message = "La edad no puede ser negativa")
    private Integer ageCustomer;

    @jakarta.validation.constraints.NotNull(message = "El teléfono del cliente es obligatorio")
    private Long phone;

    @NotBlank(message = "La dirección del cliente es obligatoria")
    @Size (max = 100, message = "La dirección del cliente no puede tener más de 100 caracteres")
    private String address;

    private Long documentTypeId;
    private DocumentTypeDTO documentType;

    public CustomerDTO() {}

    public Long getId_customer() {
        return id_customer;
    }
    
    public void setId_customer(Long id_customer) {
        this.id_customer = id_customer;
    }

    public Long getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(Long documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getLastnameCustomer() {
        return lastnameCustomer;
    }

    public void setLastnameCustomer(String lastnameCustomer) {
        this.lastnameCustomer = lastnameCustomer;
    }

    public Integer getAgeCustomer() {
        return ageCustomer;
    }

    public void setAgeCustomer(Integer ageCustomer) {
        this.ageCustomer = ageCustomer;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(Long documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public DocumentTypeDTO getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentTypeDTO documentType) {
        this.documentType = documentType;
    }
}
