package com.crud.crud_backend.repository;

import com.crud.crud_backend.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {
	Optional<DocumentType> findByNameDocumentTypeIgnoreCase(String nameDocumentType);
	Optional<DocumentType> findByPrefixIgnoreCase(String prefix);
    
}
