package com.crud.crud_backend.service;

import com.crud.crud_backend.dto.DocumentTypeDTO;

import java.util.List;

public interface DocumentTypeService {
    DocumentTypeDTO crear(DocumentTypeDTO dto);
    List<DocumentTypeDTO> listar();
    DocumentTypeDTO obtenerPorId(Long id);
    DocumentTypeDTO actualizar(Long id, DocumentTypeDTO dto);
    void eliminar(Long id);
}
