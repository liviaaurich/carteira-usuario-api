package com.liviaaurich.carteiraservice.domain.enumerations;

import org.junit.Assert;
import org.junit.Test;

public class TipoUsuarioEnumTest {
    @Test
    public void getAtributos() {
        Assert.assertNotNull(TipoUsuarioEnum.COMUM.getCodigo());
        Assert.assertNotNull(TipoUsuarioEnum.COMUM.getMensagemDuplicada());
    }
}
