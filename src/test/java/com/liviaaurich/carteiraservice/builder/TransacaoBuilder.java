package com.liviaaurich.carteiraservice.builder;

import com.liviaaurich.carteiraservice.domain.Transacao;
import com.liviaaurich.carteiraservice.service.dto.TransacaoDTO;
import com.liviaaurich.carteiraservice.service.mapper.TransacaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransacaoBuilder extends EntityGenerator<Transacao, TransacaoDTO> {

    @Autowired
    private TransacaoMapper transacaoMapper;

    @Override
    public TransacaoDTO createDto() {
        return TransacaoDTO.builder().valor(0.0).build();
    }

    @Override
    public Transacao createEntity() {
        return transacaoMapper.toEntity(createDto());
    }
}
