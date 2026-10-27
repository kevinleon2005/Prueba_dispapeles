package com.crud.crud_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DocumentTypeDTO {

    private Long id;

    @NotBlank(message = "El nombre del tipo de documento es obligatorio")
    @Size(max = 50, message = "El tipo de documento no puede tener más de 50 caracteres")
    private String nameDocumentType;

    @NotBlank(message = "El prefijo del tipo de documento es obligatorio")
    @Size(max = 50, message = "El prefijo no puede tener más de 50 caracteres")
    private String prefix;

    public DocumentTypeDTO() {
    }

    public DocumentTypeDTO(Long id, String nameDocumentType, String prefix) {
        this.id = id;
        this.nameDocumentType = nameDocumentType;
        this.prefix = prefix;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
