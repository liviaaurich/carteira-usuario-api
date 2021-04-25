package com.liviaaurich.carteiraservice.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacaoDTO {

    private Long id;

    private Double valor;

    @JsonIgnore
    private Long idPagador;

    private Long idBeneficiado;
}
