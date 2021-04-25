package com.liviaaurich.carteiraservice.service;

import com.liviaaurich.carteiraservice.domain.Usuario;
import com.liviaaurich.carteiraservice.domain.security.AuthUser;
import com.liviaaurich.carteiraservice.repository.UsuarioRepository;
import com.liviaaurich.carteiraservice.service.util.ConstantsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String s) {
        Usuario usuario = usuarioRepository.findByEmail(s)
                .orElseThrow(() -> new UsernameNotFoundException(ConstantsUtil.ERRO_USUARIO_NAO_ENCONTRADO));
        return new AuthUser(usuario);
    }
}
