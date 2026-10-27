package com.crud.crud_backend.service.impl;

import com.crud.crud_backend.dto.CustomerDTO;
import com.crud.crud_backend.exception.ResourceNotFoundException;
import com.crud.crud_backend.model.Customer;
import com.crud.crud_backend.model.DocumentType;
import com.crud.crud_backend.repository.CustomerRepository;
import com.crud.crud_backend.repository.DocumentTypeRepository;
import com.crud.crud_backend.service.CustomerService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@Transactional
public class ClientSerciveImpl implements CustomerService {
    private final CustomerRepository repository;
    private final DocumentTypeRepository documentTypeRepository;

    public ClientSerciveImpl(CustomerRepository repository, DocumentTypeRepository documentTypeRepository) {
        this.repository = repository;
        this.documentTypeRepository = documentTypeRepository;
    }

    @Override
    public CustomerDTO crear(CustomerDTO dto) {
        Customer customer = convertToEntity(dto);
        customer.setId(null);
        customer.setDocumentType(resolveDocumentType(dto.getDocumentTypeId()));
        return convertToDTO(repository.save(customer));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> listar() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .toList();}

    @Override
    @Transactional(readOnly = true)
    public CustomerDTO obtenerPorId(Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        return convertToDTO(customer);
    }

    @Override
    public CustomerDTO actualizar(Long id, CustomerDTO dto) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado: " + id));

        customer.setDocumentNumber(dto.getDocumentNumber());
        customer.setNameCustomer(dto.getNameCustomer());
        customer.setLastnameCustomer(dto.getLastnameCustomer());
        customer.setAgeCustomer(dto.getAgeCustomer());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());
        if (dto.getDocumentTypeId() != null) {
            customer.setDocumentType(resolveDocumentType(dto.getDocumentTypeId()));
        }

        return convertToDTO(repository.save(customer));
    }

    @Override
    public void eliminar(Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado: " + id));
        repository.delete(customer);
    }

    private Customer convertToEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setDocumentNumber(dto.getDocumentNumber());
        customer.setNameCustomer(dto.getNameCustomer());
        customer.setLastnameCustomer(dto.getLastnameCustomer());
        customer.setAgeCustomer(dto.getAgeCustomer());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());
        return customer;
    }

    private CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId_customer(customer.getId());
        dto.setDocumentNumber(customer.getDocumentNumber());
        dto.setNameCustomer(customer.getNameCustomer());
        dto.setLastnameCustomer(customer.getLastnameCustomer());
        dto.setAgeCustomer(customer.getAgeCustomer());
        dto.setPhone(customer.getPhone());
        dto.setAddress(customer.getAddress());
        dto.setDocumentTypeId(customer.getDocumentType().getId());
        dto.setDocumentType(new com.crud.crud_backend.dto.DocumentTypeDTO(
            customer.getDocumentType().getId(), customer.getDocumentType().getNameDocumentType(),
            customer.getDocumentType().getPrefix()));
        return dto;
    }

    private DocumentType resolveDocumentType(Long documentTypeId) {
        if (documentTypeId != null) {
            return documentTypeRepository.findById(documentTypeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Tipo de documento no encontrado: " + documentTypeId));
        }

        return documentTypeRepository.findByNameDocumentTypeIgnoreCase("DNI")
                .orElseGet(() -> documentTypeRepository.save(createDefaultDocumentType()));
    }

    private DocumentType createDefaultDocumentType() {
        DocumentType documentType = new DocumentType();
        documentType.setNameDocumentType("DNI");
        documentType.setPrefix("DNI");
        return documentType;
    }
}
