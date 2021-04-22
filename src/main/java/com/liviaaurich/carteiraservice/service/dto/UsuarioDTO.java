package com.liviaaurich.carteiraservice.service.dto;

import com.liviaaurich.carteiraservice.domain.Carteira;
import com.liviaaurich.carteiraservice.domain.enumerations.TipoUsuarioEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class UsuarioDTO  implements Serializable {
    private Long id;

    @NotNull(message = "erro.nome-obrigatorio")
    private String nome;

    @NotNull(message = "erro.email-obrigatorio")
    private String email;

    @NotNull(message = "erro.senha-obrigatoria")
    private String senha;

    private String cpf;

    private String cnpj;

    private TipoUsuarioEnum tipoUsuario;

    private Carteira carteira;
}
