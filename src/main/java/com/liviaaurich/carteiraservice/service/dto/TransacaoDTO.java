package com.liviaaurich.carteiraservice.service.dto;

import com.liviaaurich.carteiraservice.domain.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacaoDTO {

    private Long id;

    private Double valor;

    private Long idPagador;

    private Long idBeneficiado;
}
