package com.liviaaurich.carteiraservice.service.dto;

import com.liviaaurich.carteiraservice.service.util.ConstantsUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO implements Serializable {

    private Long id;

    @NotBlank(message = ConstantsUtil.ERRO_OBRIGATORIEDADE_NOME)
    private String nome;

    @Email(message = ConstantsUtil.ERRO_EMAIL_INVALIDO)
    @NotNull(message = ConstantsUtil.ERRO_OBRIGATORIEDADE_EMAIL)
    private String email;

    @NotBlank(message = ConstantsUtil.ERRO_OBRIGATORIEDADE_SENHA)
    private String senha;

    @CPF(message = ConstantsUtil.ERRO_CPF_INVALIDO)
    private String cpf;

    @CNPJ(message = ConstantsUtil.ERRO_CNPJ_INVALIDO)
    private String cnpj;

    private CarteiraDTO carteira = new CarteiraDTO();

}
