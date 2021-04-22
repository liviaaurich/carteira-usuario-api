package com.liviaaurich.carteiraservice.domain.security;

import com.liviaaurich.carteiraservice.domain.Usuario;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Getter
public class AuthUser extends User {

    private Long id;

    public AuthUser(Usuario usuario) {
        super(usuario.getEmail(), usuario.getSenha(), Collections.emptyList());
        this.id = usuario.getId();
    }
}
