package com.shoppingcar.yosova.dto.mapper;

import com.shoppingcar.yosova.domain.Sale;
import com.shoppingcar.yosova.dto.SaleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Sale and its DTO SaleDto.
 */
@Mapper( componentModel = "spring")
public interface SaleMapper extends EntityMapper<SaleDTO, Sale> {

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "client.name", target = "clientName")
    SaleDTO toDto(Sale sale);

    @Mapping(source = "clientId", target = "client.id")
    Sale toEntity(SaleDTO saleDTO);

    default Sale fromId(Long id) {
        if (id == null) {
            return null;
        }
        Sale sale = new Sale();
        sale.setId(id);
        return sale;
    }

}
