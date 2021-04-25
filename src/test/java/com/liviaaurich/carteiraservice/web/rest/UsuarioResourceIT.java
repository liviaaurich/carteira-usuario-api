package com.liviaaurich.carteiraservice.web.rest;

import com.liviaaurich.carteiraservice.builder.UsuarioBuilder;
import com.liviaaurich.carteiraservice.util.BaseIntTeste;
import com.liviaaurich.carteiraservice.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UsuarioResourceIT extends BaseIntTeste {

    private static final String BASE_URL = "/api/usuarios";

    @Autowired
    private UsuarioBuilder entityGenerator;

    @Test
    @Transactional
    public void createContratoTest() throws Exception {
        this.getMockMvc().perform(post(BASE_URL)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(entityGenerator.createDto())))
                .andExpect(status().isOk());
    }
}
