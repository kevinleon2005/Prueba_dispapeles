package com.crud.crud_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table (name = "document_types")
public class DocumentType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_document_type;

    @Column(name = "name_document_type", nullable = false, unique = true, length = 50)
    private String nameDocumentType;

    @Column(name = "prefix", nullable = false, length = 50)
    private String prefix;

    public DocumentType(){}

    // Getters and Setters id_document_type
    public Long getId() {
        return id_document_type;
    }

    public void setId(Long id) {
        this.id_document_type = id;
    }

    // Getters and Setters name_document_type
    public String getNameDocumentType() {
        return nameDocumentType;
    }

    public void setNameDocumentType(String nameDocumentType) {
        this.nameDocumentType = nameDocumentType;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
