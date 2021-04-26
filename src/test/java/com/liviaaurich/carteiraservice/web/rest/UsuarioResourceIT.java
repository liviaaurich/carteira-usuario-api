package com.liviaaurich.carteiraservice.web.rest;

import com.liviaaurich.carteiraservice.builder.UsuarioBuilder;
import com.liviaaurich.carteiraservice.service.dto.UsuarioDTO;
import com.liviaaurich.carteiraservice.service.util.ConstantsUtil;
import com.liviaaurich.carteiraservice.util.BaseIntTeste;
import com.liviaaurich.carteiraservice.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UsuarioResourceIT extends BaseIntTeste {

    private static final String BASE_URL = "/api/usuarios";

    @Autowired
    private UsuarioBuilder entityGenerator;

    @Autowired
    @Qualifier("mappingJackson2HttpMessageConverter")
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Test
    @Transactional
    public void incluirUsuarioTest() throws Exception {
        this.getMockMvc().perform(post(BASE_URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityGenerator.createDto())))
            .andExpect(status().isCreated());

        this.getMockMvc().perform(post(BASE_URL)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(entityGenerator.createDto())))
                .andExpect(status().isBadRequest())
                .andExpect(header().stringValues(ConstantsUtil.ERRO_DUPLICIDADE_EMAIL));

        UsuarioDTO usuarioDTO = entityGenerator.createDto();
        usuarioDTO.setEmail("user@email.com");
        this.getMockMvc().perform(post(BASE_URL)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(header().stringValues(ConstantsUtil.ERRO_DUPLICIDADE_CPF));
    }

    @Test
    @Transactional
    public void atualizarUsuarioTest() throws Exception {
        UsuarioDTO usuarioDTO = jacksonMessageConverter.getObjectMapper().readValue(
            this.getMockMvc().perform(post(BASE_URL)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(entityGenerator.createDto())))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(), UsuarioDTO.class);

        usuarioDTO.setNome("Teste");
        this.getMockMvc().perform(put(BASE_URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usuarioDTO)))
            .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void obterUsuariosTest() throws Exception {
        this.getMockMvc().perform(post(BASE_URL)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(entityGenerator.createDto())))
                .andExpect(status().isCreated());

        this.getMockMvc().perform(get(BASE_URL)
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(status().isOk());
    }
}
