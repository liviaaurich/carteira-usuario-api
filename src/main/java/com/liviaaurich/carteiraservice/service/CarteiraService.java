package com.liviaaurich.carteiraservice.service;

import com.liviaaurich.carteiraservice.repository.CarteiraRepository;
import com.liviaaurich.carteiraservice.service.event.TransacaoEvent;
import com.liviaaurich.carteiraservice.service.util.ConstantsUtil;
import com.liviaaurich.carteiraservice.web.rest.errors.ParametrizedMessageException;
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
public class CarteiraService {

    private final CarteiraRepository repository;

    public Double obterSaldoUsuario(Long idUsuario) {
        return repository.obterSaldoUsuario(idUsuario).orElse(0.0);
    }

    public void verificarCarteiraExistente(Long idUsuario) {
        if(!repository.existsById(idUsuario)) {
            throw new ParametrizedMessageException(ConstantsUtil.ERRO_CARTEIRA_USUARIO_INEXISTENTE, ConstantsUtil.ERROR_TITLE);
        }
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void processarEventoTransacao(TransacaoEvent event) {
        log.info("[INFO] Iniciando a transferencia");
        repository.atualizarSaldoUsuario(event.getId(), -event.getValor());
        repository.atualizarSaldoUsuario(event.getIdBeneficiado(), event.getValor());
    }
}
