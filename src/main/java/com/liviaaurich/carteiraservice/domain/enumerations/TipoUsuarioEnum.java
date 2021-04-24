package com.liviaaurich.carteiraservice.domain.enumerations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoUsuarioEnum {

    COMUM(0, "Comum"),
    LOGISTA(1, "Logista");

    private Integer codigo;
    private String descricao;
}
