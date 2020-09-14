package com.shoppingcar.yosova.service.impl;

import com.shoppingcar.yosova.domain.Product;
import com.shoppingcar.yosova.dto.ProductDTO;
import com.shoppingcar.yosova.dto.mapper.ProductMapper;
import com.shoppingcar.yosova.exceptions.ProductException;
import com.shoppingcar.yosova.repository.ProductRepository;
import com.shoppingcar.yosova.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service implementation for the Product entity.
 */
@Service
@Transactional
@Log4j2
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    /**
     * Save a product.
     *
     * @param productDto the entity to save
     * @return the persisted entity
     */
    public ProductDTO save(ProductDTO productDto) throws ProductException {
        log.debug("Request to save Product : {}", productDto);

        try{
            Product product = productMapper.toEntity(productDto);
            product = productRepository.save(product);
            return productMapper.toDto(product);
        } catch (Exception ex) {
            throw new ProductException(String.format("An error occurred while persisting a product: %s", ex.getMessage()));
        }
    }

    /**
     * Get one product by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public ProductDTO findOne(Long id) throws ProductException {
        log.debug("[ProductService] - findOne {} " + "productId: " + id);

        try{
            return productMapper.toDto(this.findById(id));
        } catch (ProductException e) {
            throw e;
        } catch (Exception ex) {
            throw new ProductException(String.format("An error occurred getting a product by id: %s", ex.getMessage()));
        }
    }

    /**
     * Get one product by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Product findById(Long id) throws ProductException {
        log.debug("[ProductService] - findById {} " + "productId: " + id);

        try{
            return productRepository.findById(id)
                    .orElseThrow(() -> new ProductException(String.format("There is no product with id %s", id)));
        } catch (Exception ex) {
            throw ex;
        }
    }


    /**
     * Get all Products.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() throws ProductException {
        log.debug("[ProductService] - getAll {}");

        try{
            return productMapper.toDto(productRepository.findAll());
        } catch (Exception ex) {
            throw new ProductException(String.format("An error occurred getting the product list: %s", ex.getMessage()));
        }
    }

    /**
     * Delete the product by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) throws ProductException {
        log.info("[ProductService] - deletePeripheralDeviceFromGateway {} " + "productId: " + id);

        try{
            productRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ProductException(String.format("An error occurred getting the product list: %s", ex.getMessage()));
        }
    }
}
