package com.liviaaurich.carteiraservice.service;

import com.liviaaurich.carteiraservice.service.dto.AutorizadorDTO;
import com.liviaaurich.carteiraservice.service.event.TransacaoEvent;
import com.liviaaurich.carteiraservice.service.feign.MockFeignClient;
import com.liviaaurich.carteiraservice.service.util.ConstantsUtil;
import com.liviaaurich.carteiraservice.web.rest.errors.ParametrizedMessageException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FeignService {

    private final MockFeignClient mockFeignClient;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public AutorizadorDTO emitirNotificacao(TransacaoEvent event) {
        log.info("[INFO] Iniciando a emissão da notificação");
        try {
            return mockFeignClient.emitirNotificacao(event.getIdBeneficiado());
        } catch (FeignException e) {
            throw new ParametrizedMessageException(e.getMessage(), ConstantsUtil.ERROR_TITLE);
        }
    }

    public AutorizadorDTO autorizarTransferencia() {
        try {
            return mockFeignClient.autorizarTransferencia();
        } catch (FeignException e) {
            throw new ParametrizedMessageException(e.getMessage(), ConstantsUtil.ERROR_TITLE);
        }
    }
}
