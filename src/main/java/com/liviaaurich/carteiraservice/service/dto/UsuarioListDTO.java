package com.liviaaurich.carteiraservice.service.dto;

import com.liviaaurich.carteiraservice.domain.enumerations.TipoUsuarioEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioListDTO {

    private Long id;

    private String nome;

    private String email;

    private String cpf;

    private String cnpj;

    private TipoUsuarioEnum tipoUsuario;
}
