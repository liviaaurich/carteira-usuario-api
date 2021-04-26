package com.liviaaurich.carteiraservice.service;

import com.liviaaurich.carteiraservice.domain.Usuario;
import com.liviaaurich.carteiraservice.domain.enumerations.TipoUsuarioEnum;
import com.liviaaurich.carteiraservice.repository.UsuarioRepository;
import com.liviaaurich.carteiraservice.service.dto.UsuarioDTO;
import com.liviaaurich.carteiraservice.service.dto.UsuarioListDTO;
import com.liviaaurich.carteiraservice.service.mapper.UsuarioMapper;
import com.liviaaurich.carteiraservice.service.util.ConstantsUtil;
import com.liviaaurich.carteiraservice.service.util.FuncoesUtil;
import com.liviaaurich.carteiraservice.web.rest.errors.ParametrizedMessageException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Service da entidade Usuario, responsável pelas regras de negócio
 * e manipulação das requisições ao banco de dados para a tabela
 * representada pela entidade Usuario
 *
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    private final UsuarioMapper mapper;

    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
        formatarCampos(usuarioDTO);

        Usuario usuario = mapper.toEntity(usuarioDTO);
        validarCampos(usuarioDTO, usuario.getTipoUsuario());

        return mapper.toDto(repository.save(usuario));
    }

    @Transactional(readOnly = true)
    public List<UsuarioListDTO> obterTodos() {
        return repository.obterTodos();
    }

    private void formatarCampos(UsuarioDTO usuarioDTO) {
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));

        usuarioDTO.setCpf(FuncoesUtil.formatarCampoNumerico(usuarioDTO.getCpf()));
        usuarioDTO.setCnpj(FuncoesUtil.formatarCampoNumerico(usuarioDTO.getCnpj()));
    }

    private void validarCampos(UsuarioDTO usuarioDTO, TipoUsuarioEnum tipoUsuario) {
        if(Objects.isNull(usuarioDTO.getCpf()) && Objects.isNull(usuarioDTO.getCnpj())) {
            throw new ParametrizedMessageException(ConstantsUtil.ERRO_OBRIGATORIEDADE_CPF_CNPJ, ConstantsUtil.ERROR_TITLE);
        }
        verificarDuplicidade(usuarioDTO, tipoUsuario);
    }

    private void verificarDuplicidade(UsuarioDTO usuarioDTO, TipoUsuarioEnum tipoUsuario) {
        FuncoesUtil.verificarDuplicidade(repository.existeEmailCadastrado(usuarioDTO), ConstantsUtil.ERRO_DUPLICIDADE_EMAIL);
        FuncoesUtil.verificarDuplicidade(repository.existeCpfCnpjCadastrado(usuarioDTO),
                tipoUsuario.getMensagemDuplicada());
    }

}
