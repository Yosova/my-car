package com.shoppingcar.yosova.service;

import com.shoppingcar.yosova.dto.ProductDTO;
import com.shoppingcar.yosova.exceptions.ProductException;

import java.util.List;

/**
 * Service interface for the Client entity.
 */
public interface ProductService {

    ProductDTO save(ProductDTO productDto) throws ProductException;

    List<ProductDTO> findAll() throws ProductException;

    ProductDTO findOne(Long id) throws ProductException;

    void delete(Long id) throws ProductException;
}
