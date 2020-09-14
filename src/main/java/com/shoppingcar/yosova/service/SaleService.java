package com.shoppingcar.yosova.service;

import com.shoppingcar.yosova.dto.ProductDTO;
import com.shoppingcar.yosova.dto.SaleDTO;
import rx.Single;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for the Sale entity.
 */
public interface SaleService {

    SaleDTO save(SaleDTO clientDto);

    List<SaleDTO> findAll();

    Optional<SaleDTO> findOne(Long id);

    Single<List<SaleDTO>> findAllSalesById(Long id);

    SaleDTO createSaleDetail(Long clientId, List<ProductDTO> products);
}
