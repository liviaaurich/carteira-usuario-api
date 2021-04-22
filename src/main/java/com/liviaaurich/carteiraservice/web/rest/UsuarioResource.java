package com.liviaaurich.carteiraservice.web.rest;

import com.liviaaurich.carteiraservice.service.UsuarioService;
import com.liviaaurich.carteiraservice.service.dto.UsuarioDTO;
import com.liviaaurich.carteiraservice.service.dto.UsuarioListDTO;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioResource {

    private final UsuarioService usuarioService;

    private static final String API_USUARIOS = "/usuarios";

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody @Valid UsuarioDTO usuarioDTO) throws URISyntaxException {
        log.debug("REST request to save Usuario : {}", usuarioDTO);
        UsuarioDTO result = usuarioService.salvar(usuarioDTO);
        return ResponseEntity.created(new URI(API_USUARIOS + result.getId()))
                .body(result);
    }

    @Timed
    @GetMapping
    public ResponseEntity<List<UsuarioListDTO>> obterTodos() {
        log.debug("REST request to get Usuario");
        return ResponseEntity.ok(usuarioService.obterTodos());
    }
}
