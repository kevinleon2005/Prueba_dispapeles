package com.crud.crud_backend.service.impl;

import com.crud.crud_backend.dto.DocumentTypeDTO;
import com.crud.crud_backend.exception.ResourceNotFoundException;
import com.crud.crud_backend.model.DocumentType;
import com.crud.crud_backend.repository.DocumentTypeRepository;
import com.crud.crud_backend.service.DocumentTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentTypeRepository repository;

    public DocumentTypeServiceImpl(DocumentTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public DocumentTypeDTO crear(DocumentTypeDTO dto) {
        DocumentType documentType = new DocumentType();
        documentType.setNameDocumentType(dto.getNameDocumentType());
        documentType.setPrefix(dto.getPrefix());
        return convertToDTO(repository.save(documentType));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentTypeDTO> listar() {
        return repository.findAll().stream().map(this::convertToDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentTypeDTO obtenerPorId(Long id) {
        return convertToDTO(findEntity(id));
    }

    @Override
    public DocumentTypeDTO actualizar(Long id, DocumentTypeDTO dto) {
        DocumentType documentType = findEntity(id);
        documentType.setNameDocumentType(dto.getNameDocumentType());
        documentType.setPrefix(dto.getPrefix());
        return convertToDTO(repository.save(documentType));
    }

    @Override
    public void eliminar(Long id) {
        repository.delete(findEntity(id));
    }

    private DocumentType findEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de documento no encontrado: " + id));
    }

    private DocumentTypeDTO convertToDTO(DocumentType documentType) {
        return new DocumentTypeDTO(documentType.getId(), documentType.getNameDocumentType(), documentType.getPrefix());
    }
}
