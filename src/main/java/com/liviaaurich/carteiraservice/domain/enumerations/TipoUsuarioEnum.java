package com.liviaaurich.carteiraservice.domain.enumerations;

import com.liviaaurich.carteiraservice.service.util.ConstantsUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoUsuarioEnum {

    COMUM(0, ConstantsUtil.ERRO_DUPLICIDADE_CPF),
    LOGISTA(1, ConstantsUtil.ERRO_DUPLICIDADE_CNPJ);

    private Integer codigo;
    private String mensagemDuplicada;
}
