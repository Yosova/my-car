package com.shoppingcar.yosova.service.impl;


import com.shoppingcar.yosova.domain.Client;
import com.shoppingcar.yosova.dto.ClientDTO;
import com.shoppingcar.yosova.dto.mapper.ClientMapper;
import com.shoppingcar.yosova.repository.ClientRepository;
import com.shoppingcar.yosova.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rx.Single;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for the Client entity.
 */
@Service
@Transactional
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    /**
     * Save a client.
     *
     * @param clientDto the entity to save
     * @return the persisted entity
     */
    public ClientDTO save(ClientDTO clientDto) {
        log.debug("Request to save Client : {}", clientDto);
        Client client = clientMapper.toEntity(clientDto);
        client = clientRepository.save(client);
        return clientMapper.toDto(client);
    }


    /**
     * Get all clients.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ClientDTO> findAll() {
        log.debug("Request to get all clients");
        return clientRepository.findAll().stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public Single<Object> findAllById(Long id) {
        return null;
    }


}
