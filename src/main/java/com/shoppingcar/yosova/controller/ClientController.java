package com.shoppingcar.yosova.controller;

import com.shoppingcar.yosova.dto.ClientDTO;
import com.shoppingcar.yosova.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import rx.Single;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/car/client")
@Slf4j
public class ClientController{

	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

    @PostMapping("")
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) throws URISyntaxException {
        log.debug("REST request to save Client : {}", clientDTO);

        ClientDTO result = clientService.save(clientDTO);
        if (ObjectUtils.isEmpty(result)) {
            return ResponseEntity.badRequest().body(result);
        } else {
            return ResponseEntity.created(new URI("/car/client/" + result.getId()))
                    .body(result);
        }
    }

    @GetMapping("")
    public List<ClientDTO> getAllClients() {
        log.debug("REST request to get all clients");
        return clientService.findAll();
    }


    @GetMapping(
            value = "{clientId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Single<Object> getClientById(@PathVariable("clientId") String id) {
        log.debug("REST request to get all clients");
        return clientService.findAllById(Long.parseLong(id));
    }

}
