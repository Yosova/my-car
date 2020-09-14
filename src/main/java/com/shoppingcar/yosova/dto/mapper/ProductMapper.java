package com.shoppingcar.yosova.dto.mapper;

import com.shoppingcar.yosova.domain.Product;
import com.shoppingcar.yosova.dto.ProductDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Product and its DTO ProductDto.
 */
@Mapper( componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {

}
