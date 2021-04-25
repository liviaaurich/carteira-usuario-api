package com.liviaaurich.carteiraservice.web.rest;

import com.liviaaurich.carteiraservice.service.TransacaoService;
import com.liviaaurich.carteiraservice.service.dto.TransacaoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@RestController
@RequestMapping("/api/transacoes")
@RequiredArgsConstructor
public class TransacaoResource {

    private final TransacaoService transacaoService;

    private static final String API_TRANSACOES = "/api/transacoes/";

    @PostMapping
    public ResponseEntity<TransacaoDTO> salvar(@Valid @RequestBody TransacaoDTO transacaoDTO) throws URISyntaxException {
        log.debug("REST request to save Transacao : {}", transacaoDTO);
        TransacaoDTO result = transacaoService.salvar(transacaoDTO);
        return ResponseEntity.created(new URI(API_TRANSACOES + result.getId()))
                .body(result);
    }

}
