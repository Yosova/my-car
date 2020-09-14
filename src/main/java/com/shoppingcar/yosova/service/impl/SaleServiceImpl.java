package com.shoppingcar.yosova.service.impl;

import com.shoppingcar.yosova.EntityNotFoundException;
import com.shoppingcar.yosova.domain.Sale;
import com.shoppingcar.yosova.domain.SaleDetail;
import com.shoppingcar.yosova.dto.ProductDTO;
import com.shoppingcar.yosova.dto.SaleDTO;
import com.shoppingcar.yosova.dto.mapper.ProductMapper;
import com.shoppingcar.yosova.dto.mapper.SaleMapper;
import com.shoppingcar.yosova.repository.SaleDetailRepository;
import com.shoppingcar.yosova.repository.SaleRepository;
import com.shoppingcar.yosova.service.SaleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import rx.Single;

import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for the Sale entity.
 */
@Service
@Transactional
@Slf4j
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;

    private SaleMapper saleMapper;
    private ProductMapper productMapper;

    public SaleServiceImpl(SaleRepository saleRepository, SaleDetailRepository saleDetailRepository, SaleMapper saleMapper, ProductMapper productMapper) {
        this.saleRepository = saleRepository;
        this.saleDetailRepository = saleDetailRepository;
        this.saleMapper = saleMapper;
        this.productMapper = productMapper;
    }

    /**
     * Save a sale.
     *
     * @param saleDto the entity to save
     * @return the persisted entity
     */
    public SaleDTO save(SaleDTO saleDto) {
        log.debug("Request to save Sale : {}", saleDto);
        Sale sale = saleMapper.toEntity(saleDto);
        sale = saleRepository.save(sale);
        return saleMapper.toDto(sale);
    }


    /**
     * Get all sales.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<SaleDTO> findAll() {
        log.debug("Request to get all sales");
        return saleRepository.findAll().stream()
                .map(saleMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all sales by id.
     *
     * @param id the id of the entity
     * @return Optional of Sale
     */
    @Transactional(readOnly = true)
    public Optional<SaleDTO> findOne(Long id) {
        log.debug("Request to get Sale : {}", id);
        return saleRepository.findById(id)
                .map(saleMapper::toDto);
    }

    /**
     * Create a new SaleDetail.
     *
     * @param products the product list of sale to create
     * @param clientId the id of client
     * @return the persisted entity
     */
    @Override
    public SaleDTO createSaleDetail(Long clientId, List<ProductDTO> products) {
        SaleDTO sale = save(SaleDTO.builder().clientId(clientId).date(ZonedDateTime.now()).build());
        for (ProductDTO product : products) {
            SaleDetail saleDetail =  new SaleDetail();
            saleDetail.setProduct(productMapper.toEntity(product));
            saleDetail.setSale(saleMapper.toEntity(sale));
            saleDetailRepository.save(saleDetail);
        }
        return sale;
    }


    @Transactional(readOnly = true)
    public Single<List<SaleDTO>> findAllSalesById(Long id) {
        log.debug("Request to get all sales from userId : {}", id);
        return findAllSalesInRepository(id)
                .map(this::toBookResponseList);
    }

    private Single<List<Sale>> findAllSalesInRepository(Long id) {
        return Single.create(singleSubscriber -> {
            List<Sale> sales = saleRepository.findAllByClient_Id(id);

            if (!ObjectUtils.isEmpty(sales)) {
                singleSubscriber.onSuccess(sales);
            } else {
                singleSubscriber.onError(new EntityNotFoundException(Sale.class));
            }
        });
    }

    private List<SaleDTO> toBookResponseList(List<Sale> bookList) {
        return bookList
                .stream()
                .map(saleMapper::toDto)
                .collect(Collectors.toList());
    }
}
