package com.liviaaurich.carteiraservice.service.mapper;

import com.liviaaurich.carteiraservice.domain.Transacao;
import com.liviaaurich.carteiraservice.service.dto.TransacaoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransacaoMapper {

    TransacaoDTO toDto(Transacao entity);

    @Mapping(source = "idPagador", target = "usuarioPagador.id")
    @Mapping(source = "idBeneficiado", target = "usuarioBeneficiado.id")
    Transacao toEntity(TransacaoDTO dto);
}
