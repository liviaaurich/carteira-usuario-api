package com.liviaaurich.carteiraservice.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoDTO {

    private Long id;

    private Double valor;

    @JsonIgnore
    private Long idPagador;

    private Long idBeneficiado;
}
