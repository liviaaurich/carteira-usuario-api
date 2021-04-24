package com.liviaaurich.carteiraservice.repository;

import com.liviaaurich.carteiraservice.domain.Usuario;
import com.liviaaurich.carteiraservice.service.dto.UsuarioListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT new com.liviaaurich.carteiraservice.service.dto.UsuarioListDTO(id, nome, email, cpf, cnpj," +
            " tipoUsuario) FROM Usuario")
    List<UsuarioListDTO> obterTodos();

    Optional<Usuario> findByEmail(String email);

}
