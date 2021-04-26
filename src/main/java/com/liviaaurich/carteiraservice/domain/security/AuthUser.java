package com.liviaaurich.carteiraservice.domain.security;

import com.liviaaurich.carteiraservice.domain.Usuario;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Getter
public class AuthUser extends User {

    private Long id;

    public AuthUser(Usuario usuario) {
        super(usuario.getEmail(), usuario.getSenha(), Collections.emptyList());
        this.id = usuario.getId();
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof AuthUser) && new EqualsBuilder()
            .append(getId(), ((AuthUser) o).getId())
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }
}
