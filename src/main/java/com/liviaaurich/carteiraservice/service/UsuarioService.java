package com.liviaaurich.carteiraservice.service;

import com.liviaaurich.carteiraservice.domain.Usuario;
import com.liviaaurich.carteiraservice.repository.UsuarioRepository;
import com.liviaaurich.carteiraservice.service.dto.UsuarioDTO;
import com.liviaaurich.carteiraservice.service.dto.UsuarioListDTO;
import com.liviaaurich.carteiraservice.service.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service da entidade Usuario, responsável pelas regras de negócio
 * e manipulação das requisições ao banco de dados para a tabela
 * representada pela entidade Usuario
 *
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;

    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    public List<UsuarioListDTO> obterTodos() {
        return usuarioRepository.obterTodos();
    }

}
