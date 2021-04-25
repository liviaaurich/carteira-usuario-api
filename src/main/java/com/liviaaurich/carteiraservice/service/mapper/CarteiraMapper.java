package com.liviaaurich.carteiraservice.service.mapper;

import com.liviaaurich.carteiraservice.domain.Carteira;
import com.liviaaurich.carteiraservice.service.dto.CarteiraDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarteiraMapper {

    CarteiraDTO toDto(Carteira entity);

    Carteira toEntity(CarteiraDTO dto);
}
