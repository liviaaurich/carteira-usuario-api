package com.liviaaurich.carteiraservice.service;

import com.liviaaurich.carteiraservice.domain.Usuario;
import com.liviaaurich.carteiraservice.repository.UsuarioRepository;
import com.liviaaurich.carteiraservice.service.dto.UsuarioDTO;
import com.liviaaurich.carteiraservice.service.dto.UsuarioListDTO;
import com.liviaaurich.carteiraservice.service.mapper.UsuarioMapper;
import com.liviaaurich.carteiraservice.service.util.ConstantsUtil;
import com.liviaaurich.carteiraservice.service.util.FuncoesUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
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

    private final UsuarioRepository repository;

    private final UsuarioMapper mapper;

    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
        Usuario usuario = mapper.toEntity(usuarioDTO);

        verificarDuplicidade(usuarioDTO);
        formatarCampos(usuario);

        return mapper.toDto(repository.save(usuario));
    }

    public List<UsuarioListDTO> obterTodos() {
        return repository.obterTodos();
    }

    private void formatarCampos(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        usuario.setCpf(FuncoesUtil.formatarCampoNumerico(usuario.getCpf()));
        usuario.setCnpj(FuncoesUtil.formatarCampoNumerico(usuario.getCnpj()));
    }

    private void verificarDuplicidade(UsuarioDTO usuarioDTO) {
        FuncoesUtil.verificarDuplicidade(repository.existeEmailCadastrado(usuarioDTO), ConstantsUtil.ERRO_DUPLICIDADE_EMAIL);
        FuncoesUtil.verificarDuplicidade(repository.existeCpfCnpjCadastrado(usuarioDTO),
                usuarioDTO.getTipoUsuario().getMensagemDuplicada());
    }

}
