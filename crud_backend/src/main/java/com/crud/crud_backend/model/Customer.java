package com.crud.crud_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "customers")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_customer;

    @Column(name = "document_number", nullable = false)
    private Long documentNumber;

    @Column(name = "name_customer", nullable = false, length = 100)
    private String nameCustomer;

    @Column(name = "lastname_customer", nullable = false, length = 100)
    private String lastnameCustomer;

    @Column(name = "age_customer", nullable = false)
    private Integer ageCustomer;

    @Column(name = "phone", nullable = false)
    private Long phone;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_document_type_id", nullable = false)
    private DocumentType documentType;

    public Customer(){}

    // Getters and Setters id_customer
    public Long getId() {
        return id_customer;
    }

    public void setId(Long id) {
        this.id_customer = id;
    }

    // Getters and Setters document_number
    public Long getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(Long documentNumber) {
        this.documentNumber = documentNumber;
    }

    // Getters and Setters name_customer
    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    // Getters and Setters lastname_customer
    public String getLastnameCustomer() {
        return lastnameCustomer;
    }

    public void setLastnameCustomer(String lastnameCustomer) {
        this.lastnameCustomer = lastnameCustomer;
    }

    // Getters and Setters age_customer
    public Integer getAgeCustomer() {
        return ageCustomer;
    }

    public void setAgeCustomer(Integer ageCustomer) {
        this.ageCustomer = ageCustomer;
    }

    // Getters and Setters phone
    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    // Getters and Setters address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

}
