package com.liviaaurich.carteiraservice.domain.enumerations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AutorizacaoEnum {
    AUTORIZADO("Autorizado"),
    NAO_AUTORIZADO("Não Autorizado");

    private final String descricao;
}
