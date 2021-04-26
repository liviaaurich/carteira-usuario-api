package com.liviaaurich.carteiraservice.domain.enumerations;

import org.junit.Assert;
import org.junit.Test;

public class AutorizacaoEnumTest {
    @Test
    public void getAtributos() {
        Assert.assertNotNull(AutorizacaoEnum.AUTORIZADO.getDescricao());
    }
}
