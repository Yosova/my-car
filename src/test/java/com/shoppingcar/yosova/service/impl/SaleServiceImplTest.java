package com.shoppingcar.yosova.service.impl;

import com.shoppingcar.yosova.domain.Sale;
import com.shoppingcar.yosova.dto.SaleDTO;
import com.shoppingcar.yosova.dto.mapper.SaleMapper;
import com.shoppingcar.yosova.repository.SaleRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SaleServiceImplTest {

    @Rule
    public ExpectedException fails = ExpectedException.none();

    @Mock
    SaleRepository saleRepository;
    @Spy
    SaleMapper saleMapper;
    @InjectMocks
    private SaleServiceImpl service;

    Sale sale = Sale.newInstance().build();
    SaleDTO saleDto = SaleDTO.builder().build();

    @Before
    public void setUp() throws Exception {

        when(saleRepository.save(any()))
                .thenReturn(null);
    }

    @Test
    public void saveNullEtityExpectException() {

        service.save(null);

        verify(saleRepository, times(1))
                .save(null);


    }

}
