package com.liviaaurich.carteiraservice.service.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class ConstantsUtil {

    public static final String ERROR_TITLE = "error.title";

    public static final String ERRO_DUPLICIDADE_CPF="O CPF informado já consta no sistema.";
    public static final String ERRO_DUPLICIDADE_CNPJ="O CNPJ informado já consta no sistema.";
    public static final String ERRO_DUPLICIDADE_EMAIL="O e-mail informado já consta no sistema.";

    public static final String ERRO_OBRIGATORIEDADE_EMAIL="Campo e-mail é obrigatório.";
    public static final String ERRO_OBRIGATORIEDADE_NOME="Campo nome é obrigatório.";
    public static final String ERRO_OBRIGATORIEDADE_SENHA="Campo senha é obrigatório.";


    public static final String ERRO_AUTORIZACAO="Transferencia não autorizada.";
    public static final String ERRO_CARTEIRA_USUARIO_INEXISTENTE="Carteira de usuário não existe.";
    public static final String ERRO_CPF_INVALIDO="CPF inválido.";
    public static final String ERRO_CNPJ_INVALIDO="CNPJ inválido.";
    public static final String ERRO_EMAIL_INVALIDO="E-mail inválido.";
    public static final String ERRO_SALDO_INSUFICIENTE="Saldo insuficiente para a transferência.";
    public static final String ERRO_USUARIO_NAO_ENCONTRADO="Usuário não encontrado com o e-mail informado.";

    public static final String DEFAULT_TYPE = "https://www.jhipster.tech/problem/problem-with-message";
    public static final String PARAMETERIZED_TYPE = "https://www.jhipster.tech/problem/parameterized";

    public static final String ARGUMENTO_INVALIDO = "Argumento inválido";
    public static final String CORPO_INVALIDO = "O corpo da requisição está inválido. Verifique o erro de sintaxe.";
    public static final String MENSAGEM_INCOMPREENSIVEL = "Mensagem incompreensível";

}
