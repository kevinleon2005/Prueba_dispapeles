package com.crud.crud_backend.service;


import com.crud.crud_backend.dto.CustomerDTO;
import java.util.List;
public interface CustomerService {
    CustomerDTO crear(CustomerDTO dto);
    List<CustomerDTO> listar();
    CustomerDTO obtenerPorId(Long id);
    CustomerDTO actualizar(Long id, CustomerDTO dto);
    void eliminar(Long id);
}
