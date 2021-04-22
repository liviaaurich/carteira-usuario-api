package com.liviaaurich.carteiraservice.repository;

import com.liviaaurich.carteiraservice.domain.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
}
