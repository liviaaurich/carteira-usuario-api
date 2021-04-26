package com.liviaaurich.carteiraservice.service.mapper;

import com.liviaaurich.carteiraservice.domain.Usuario;
import com.liviaaurich.carteiraservice.domain.enumerations.TipoUsuarioEnum;
import com.liviaaurich.carteiraservice.service.dto.UsuarioDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO toDto(Usuario entity);

    @Mapping(source = "id", target = "carteira.id")
    Usuario toEntity(UsuarioDTO dto);

    @AfterMapping
    default void atualizarObjeto(@MappingTarget Usuario entity) {
        entity.getCarteira().setUsuario(entity);
        entity.setTipoUsuario(Objects.isNull(entity.getCpf()) ? TipoUsuarioEnum.LOGISTA : TipoUsuarioEnum.COMUM);
    }
}
