package com.liviaaurich.carteiraservice.repository;

import com.liviaaurich.carteiraservice.domain.Usuario;
import com.liviaaurich.carteiraservice.service.dto.UsuarioDTO;
import com.liviaaurich.carteiraservice.service.dto.UsuarioListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT new com.liviaaurich.carteiraservice.service.dto.UsuarioListDTO(id, nome, email, cpf, cnpj," +
            " tipoUsuario) FROM Usuario")
    List<UsuarioListDTO> obterTodos();

    Optional<Usuario> findByEmail(String email);

    @Query(value = "SELECT COUNT(u) FROM Usuario u WHERE u.email = :#{#dto.email}" +
            " AND (:#{#dto.id} IS NULL OR u.id != :#{#dto.id})")
    Integer existeEmailCadastrado(@Param("dto") UsuarioDTO dto);

    @Query(value = "SELECT COUNT(u) FROM Usuario u WHERE ((:#{#dto.cpf} IS NOT NULL AND u.cpf = :#{#dto.cpf})" +
            " OR (:#{#dto.cnpj} IS NOT NULL AND u.cnpj = :#{#dto.cnpj}))" +
            " AND (:#{#dto.id} IS NULL OR u.id != :#{#dto.id})")
    Integer existeCpfCnpjCadastrado(@Param("dto") UsuarioDTO dto);

}
