package com.liviaaurich.carteiraservice.repository;

import com.liviaaurich.carteiraservice.domain.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

    @Query(value = "SELECT dinheiro FROM Carteira WHERE id = :idUsuario")
    Optional<Double> obterSaldoUsuario(@Param("idUsuario") Long idUsuario);

    @Modifying
    @Query(value = "UPDATE Carteira SET dinheiro = dinheiro + :valor WHERE id = :idUsuario")
    void atualizarSaldoUsuario(@Param("idUsuario") Long idUsuario, @Param("valor") Double valor);

    boolean existsById(Long id);

}
