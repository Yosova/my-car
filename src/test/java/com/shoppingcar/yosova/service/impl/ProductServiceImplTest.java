package com.shoppingcar.yosova.service.impl;

import com.shoppingcar.yosova.domain.Product;
import com.shoppingcar.yosova.dto.ProductDTO;
import com.shoppingcar.yosova.dto.mapper.ProductMapper;
import com.shoppingcar.yosova.exceptions.ProductException;
import com.shoppingcar.yosova.repository.ProductRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest implements TestConstants {

    @Rule
    public ExpectedException fails = ExpectedException.none();

    @Mock
    ProductRepository productRepository;
    @Mock
    ProductMapper productMapper;
    @InjectMocks
    private ProductServiceImpl service;

    Product product = Product.newInstance()
            .id(PRODUCT_ID)
            .name("bici")
            .price(Float.valueOf("250.00"))
            .build();
    ProductDTO productDto = ProductDTO.builder()
            .id(PRODUCT_ID)
            .name("bici")
            .price(Float.valueOf("250.00"))
            .build();

    @Before
    public void setUp() throws Exception {

        when(productRepository.findById(PRODUCT_ID))
                .thenReturn(Optional.of(product));
    }

    @Test(expected = NullPointerException.class)
    public void findOneByIdNotExist() throws ProductException {
      Product prod =  service.findById(PRODUCT_ID);
        assertEquals(PRODUCT_ID, prod.getId());
    }
}
