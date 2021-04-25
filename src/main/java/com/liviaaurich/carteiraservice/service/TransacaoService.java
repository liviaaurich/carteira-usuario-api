package com.liviaaurich.carteiraservice.service;

import com.liviaaurich.carteiraservice.domain.Transacao;
import com.liviaaurich.carteiraservice.domain.enumerations.AutorizacaoEnum;
import com.liviaaurich.carteiraservice.repository.TransacaoRepository;
import com.liviaaurich.carteiraservice.security.UserSecurity;
import com.liviaaurich.carteiraservice.service.dto.TransacaoDTO;
import com.liviaaurich.carteiraservice.service.event.TransacaoEvent;
import com.liviaaurich.carteiraservice.service.mapper.TransacaoMapper;
import com.liviaaurich.carteiraservice.service.util.ConstantsUtil;
import com.liviaaurich.carteiraservice.web.rest.errors.ParametrizedMessageException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TransacaoService {

    private final TransacaoRepository repository;

    private final TransacaoMapper mapper;

    private final CarteiraService carteiraService;

    private final FeignService feignService;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final UserSecurity userSecurity;

    public TransacaoDTO salvar(TransacaoDTO transacaoDTO) {
        transacaoDTO.setIdPagador(userSecurity.getUsuarioId());
        Transacao transacao = mapper.toEntity(transacaoDTO);

        autorizarTransferencia();
        validarTransacao(transacaoDTO);
        repository.save(transacao);

        applicationEventPublisher.publishEvent(new TransacaoEvent(transacaoDTO.getIdPagador(),
                transacaoDTO.getValor(), transacaoDTO.getIdBeneficiado()));

        return mapper.toDto(transacao);
    }

    private void autorizarTransferencia() {
        if(AutorizacaoEnum.AUTORIZADO.getDescricao().equals(feignService.autorizarTransferencia().getMessage())) {
            return;
        }
        throw new ParametrizedMessageException(ConstantsUtil.ERRO_AUTORIZACAO, ConstantsUtil.ERROR_TITLE);
    }

    private void validarTransacao(TransacaoDTO transacaoDTO) {
        carteiraService.verificarCarteiraExistente(transacaoDTO.getIdBeneficiado());
        if(carteiraService.obterSaldoUsuario(transacaoDTO.getIdPagador()) < transacaoDTO.getValor()) {
            throw new ParametrizedMessageException(ConstantsUtil.ERRO_SALDO_INSUFICIENTE, ConstantsUtil.ERROR_TITLE);
        }
    }
}
