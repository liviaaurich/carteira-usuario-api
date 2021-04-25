package com.liviaaurich.carteiraservice.builder;

import com.liviaaurich.carteiraservice.domain.Usuario;
import com.liviaaurich.carteiraservice.domain.enumerations.TipoUsuarioEnum;
import com.liviaaurich.carteiraservice.service.dto.UsuarioDTO;
import com.liviaaurich.carteiraservice.service.mapper.UsuarioMapper;
import com.liviaaurich.carteiraservice.util.TestUtilConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioBuilder extends EntityGenerator<Usuario, UsuarioDTO> {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public UsuarioDTO createDto() {
        return UsuarioDTO.builder()
                .email(TestUtilConstants.DEFAULT_STRING)
                .nome(TestUtilConstants.DEFAULT_STRING)
                .senha(TestUtilConstants.DEFAULT_STRING)
                .cpf(TestUtilConstants.DEFAULT_CPF)
                .tipoUsuario(TipoUsuarioEnum.COMUM)
                .build();
    }

    @Override
    public Usuario createEntity() {
        return usuarioMapper.toEntity(createDto());
    }
}
