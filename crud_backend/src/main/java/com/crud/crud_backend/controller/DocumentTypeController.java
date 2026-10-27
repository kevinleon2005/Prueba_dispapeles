package com.crud.crud_backend.controller;

import com.crud.crud_backend.dto.DocumentTypeDTO;
import com.crud.crud_backend.service.DocumentTypeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/document-types")
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    public DocumentTypeController(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @PostMapping
    public ResponseEntity<DocumentTypeDTO> crear(@Valid @RequestBody DocumentTypeDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentTypeService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<DocumentTypeDTO>> listar() {
        return ResponseEntity.ok(documentTypeService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentTypeDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(documentTypeService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentTypeDTO> actualizar(
            @PathVariable Long id, @Valid @RequestBody DocumentTypeDTO dto) {
        return ResponseEntity.ok(documentTypeService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        documentTypeService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}