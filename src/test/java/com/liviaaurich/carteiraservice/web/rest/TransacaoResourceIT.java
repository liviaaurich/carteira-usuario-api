package com.liviaaurich.carteiraservice.web.rest;

import com.liviaaurich.carteiraservice.builder.TransacaoBuilder;
import com.liviaaurich.carteiraservice.builder.UsuarioBuilder;
import com.liviaaurich.carteiraservice.service.dto.TransacaoDTO;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class TransacaoResourceIT extends BaseIntTeste {

    private static final String BASE_URL = "/api/transacoes";

    private static final String BASE_USUARIOS_URL = "/api/usuarios";

    @Autowired
    private UsuarioBuilder usuarioGenerator;

    @Autowired
    private TransacaoBuilder entityGenerator;

    @Autowired
    @Qualifier("mappingJackson2HttpMessageConverter")
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Test
    @Transactional
    public void incluirTransacaoTest() throws Exception {
        UsuarioDTO usuarioDTO = salvarUsuario();

        TransacaoDTO transacaoDTO = entityGenerator.createDto();
        transacaoDTO.setIdBeneficiado(usuarioDTO.getId());
        this.getMockMvc().perform(post(BASE_URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transacaoDTO)))
            .andExpect(status().isCreated());

        transacaoDTO.setValor(10.0);
        this.getMockMvc().perform(post(BASE_URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transacaoDTO)))
            .andExpect(status().isBadRequest())
            .andExpect(header().stringValues(ConstantsUtil.ERRO_SALDO_INSUFICIENTE));
    }

    private UsuarioDTO salvarUsuario() throws Exception {
        this.getMockMvc().perform(post(BASE_USUARIOS_URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usuarioGenerator.createDto())))
            .andExpect(status().isCreated());

        UsuarioDTO usuarioDTO = usuarioGenerator.createDto();
        usuarioDTO.setEmail("teste@gmail.com");
        usuarioDTO.setCpf("031.079.007-70");
        return jacksonMessageConverter.getObjectMapper().readValue(
            this.getMockMvc().perform(post(BASE_USUARIOS_URL)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioDTO)))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(), UsuarioDTO.class);
    }

}
