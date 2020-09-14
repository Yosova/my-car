package com.shoppingcar.yosova.controller;

import com.shoppingcar.yosova.dto.ProductDTO;
import com.shoppingcar.yosova.exceptions.ProductException;
import com.shoppingcar.yosova.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/car/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) throws URISyntaxException, ProductException {
        log.debug("REST request to save Product : {}", productDTO);

        ProductDTO result = productService.save(productDTO);

        if (ObjectUtils.isEmpty(result)) {
            return ResponseEntity.badRequest().body(result);
        } else {
            return ResponseEntity.created(new URI("/api/products/" + result.getId()))
                    .body(result);
        }
    }


    @PutMapping("")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO) throws ProductException {
        log.debug("REST request to update Product : {}", productDTO);
        ProductDTO result = productService.save(productDTO);
        return ResponseEntity.ok()
                .body(result);
    }


    @GetMapping("")
    public List<ProductDTO> getAllProducts() throws ProductException {
        log.debug("REST request to get all Products");
        return productService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) throws ProductException {
        log.debug("REST request to get Product : {}", id);

        ProductDTO productDTO = productService.findOne(id);
        return new ResponseEntity<>(productDTO,HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws ProductException {
        log.debug("REST request to delete Product : {}", id);
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

}
