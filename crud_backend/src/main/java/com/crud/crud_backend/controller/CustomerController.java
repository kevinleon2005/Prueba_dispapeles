package com.crud.crud_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.crud_backend.dto.CustomerDTO;
import com.crud.crud_backend.service.CustomerService;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> crear(
        @Valid @RequestBody CustomerDTO dto){
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(customerService.crear(dto));
        }
    
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> listar(){
        return ResponseEntity.ok(customerService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(customerService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> actualizar(@PathVariable Long id, @Valid @RequestBody CustomerDTO dto){
        return ResponseEntity.ok(customerService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        customerService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}