package com.crud.crud_backend.config;

import com.crud.crud_backend.model.DocumentType;
import com.crud.crud_backend.repository.DocumentTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class DocumentTypeSeeder {

    private static final Map<String, String> DEFAULT_TYPES = Map.of(
            "CC", "Cédula de Ciudadanía",
            "CE", "Cédula de Extranjería",
            "TI", "Tarjeta de Identidad",
            "PAS", "Pasaporte"
    );

    @Bean
    CommandLineRunner seedDocumentTypes(DocumentTypeRepository repository) {
        return args -> DEFAULT_TYPES.forEach((prefix, name) ->
                repository.findByPrefixIgnoreCase(prefix).ifPresentOrElse(existing -> {
                    existing.setNameDocumentType(name);
                    existing.setPrefix(prefix);
                    repository.save(existing);
                }, () -> {
                    DocumentType documentType = new DocumentType();
                    documentType.setNameDocumentType(name);
                    documentType.setPrefix(prefix);
                    repository.save(documentType);
                }));
    }
}