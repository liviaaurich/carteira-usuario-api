package com.liviaaurich.carteiraservice.domain;

import com.liviaaurich.carteiraservice.domain.security.AuthUser;
import com.liviaaurich.carteiraservice.util.TestUtilConstants;
import org.junit.Assert;
import org.junit.Test;

public class AuthUserTest {

    @Test
    public void equalsTest() {
        Usuario usuario = getUsuario();

        AuthUser entidade1 = new AuthUser(usuario);
        AuthUser entidade2 = new AuthUser(usuario);

        Assert.assertEquals(entidade1, entidade2);

        usuario.setId(3L);
        entidade2 = new AuthUser(usuario);
        Assert.assertNotEquals(entidade1, entidade2);
    }

    /**
     * Testa o Método hashCode.
     */
    @Test
    public void hashCodeTeste() {
        Usuario usuario = getUsuario();

        AuthUser entidade1 = new AuthUser(usuario);
        AuthUser entidade2 = new AuthUser(usuario);

        Assert.assertEquals(entidade1.hashCode(), entidade2.hashCode());

        usuario.setId(3L);
        entidade2 = new AuthUser(usuario);
        Assert.assertNotEquals(entidade1.hashCode(), entidade2.hashCode());
    }

    private Usuario getUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setEmail(TestUtilConstants.DEFAULT_EMAIL);
        usuario.setSenha(TestUtilConstants.DEFAULT_STRING);
        return usuario;
    }
}
