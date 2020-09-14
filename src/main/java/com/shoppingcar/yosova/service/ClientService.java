package com.shoppingcar.yosova.service;

import com.shoppingcar.yosova.dto.ClientDTO;
import rx.Single;

import java.util.List;

/**
 * Service interface for the Client entity.
 */
public interface ClientService {

    ClientDTO save(ClientDTO clientDto);

    List<ClientDTO> findAll();

    Single<Object> findAllById(Long id);
}
