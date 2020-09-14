package com.shoppingcar.yosova.dto.mapper;

import com.shoppingcar.yosova.domain.Client;
import com.shoppingcar.yosova.dto.ClientDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Client and its DTO ClientDTO.
 */
@Mapper( componentModel = "spring")
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {

}
