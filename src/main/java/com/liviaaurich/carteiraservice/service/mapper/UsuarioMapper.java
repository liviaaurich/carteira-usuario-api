package com.liviaaurich.carteiraservice.service.mapper;

import com.liviaaurich.carteiraservice.domain.Usuario;
import com.liviaaurich.carteiraservice.service.dto.UsuarioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO toDto(Usuario entity);

    Usuario toEntity(UsuarioDTO dto);
}
