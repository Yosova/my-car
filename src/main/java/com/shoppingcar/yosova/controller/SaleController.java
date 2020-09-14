package com.shoppingcar.yosova.controller;

import com.shoppingcar.yosova.dto.ProductDTO;
import com.shoppingcar.yosova.dto.SaleDTO;
import com.shoppingcar.yosova.service.SaleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import rx.Single;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/car/sale")
@Slf4j
public class SaleController {

	private final SaleService saleService;

	public SaleController(SaleService saleService) {
		this.saleService = saleService;
	}

    public static void writeLog(String text) {
        log.error(text);
    }


    @PostMapping("")
    public ResponseEntity<SaleDTO> createSale(@RequestBody SaleDTO saleDTO) throws URISyntaxException {
        log.debug("REST request to save Sale : {}", saleDTO);

        SaleDTO result = saleService.save(saleDTO);

        if (ObjectUtils.isEmpty(result)) {
            return ResponseEntity.badRequest().body(result);
        } else {
            return ResponseEntity.created(new URI("/api/sales/" + result.getId()))
                    .body(result);
        }
    }


    @GetMapping("")
    public List<SaleDTO> getAllSales() {
        log.debug("REST request to get all sales");
        return saleService.findAll();
    }


    @GetMapping("/{id}")
    public Single<List<SaleDTO>> getAllSalesByUserId(@PathVariable Long id) {
        log.debug("Request to get all sales from userId : {}", id);
        return saleService.findAllSalesById(id);
    }


    @PostMapping("/detail/{clientId}")
    public ResponseEntity<SaleDTO> createSaleDetail(@RequestBody List<ProductDTO> products, @PathVariable Long clientId) throws URISyntaxException {
        log.debug("REST request to save SaleDetail : {}", clientId);
        SaleDTO result = saleService.createSaleDetail(clientId, products);
        return ResponseEntity.created(new URI("/api/sales/detail/" + result.getId()))
                .body(result);
    }
}
