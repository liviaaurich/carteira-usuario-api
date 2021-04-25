package com.liviaaurich.carteiraservice.web.rest;

import com.liviaaurich.carteiraservice.service.dto.RootEntryPointDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RootEntryPointResource {

    @GetMapping
    public RootEntryPointDTO root() {
        var rootEntryPoint = new RootEntryPointDTO();

        rootEntryPoint.add(linkTo(methodOn(UsuarioResource.class).obterTodos()).withRel("usuarios"));

        return rootEntryPoint;
    }

}
