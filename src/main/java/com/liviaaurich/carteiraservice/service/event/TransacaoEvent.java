package com.liviaaurich.carteiraservice.service.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TransacaoEvent extends DefaultEvent {

    private Long idBeneficiado;
    private Double valor;

    public TransacaoEvent(Long id, Double valor, Long idBeneficiado) {
        super(id);
        this.valor = valor;
        this.idBeneficiado = idBeneficiado;
    }
}
