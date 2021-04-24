package com.liviaaurich.carteiraservice.repository;

import com.liviaaurich.carteiraservice.domain.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
