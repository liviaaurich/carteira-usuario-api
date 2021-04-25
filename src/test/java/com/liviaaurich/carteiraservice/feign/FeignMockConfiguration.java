package com.liviaaurich.carteiraservice.feign;

import com.liviaaurich.carteiraservice.service.dto.AutorizadorDTO;
import com.liviaaurich.carteiraservice.service.feign.MockFeignClient;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class FeignMockConfiguration {

    @MockBean
    private MockFeignClient mockFeignClient;

    @PostConstruct
    private void configurarMocks() {
        Mockito.when(mockFeignClient.autorizarTransferencia())
                .thenReturn(AutorizadorDTO.builder().message("Autorizado").build());

        Mockito.when(mockFeignClient.emitirNotificacao(Mockito.anyLong()))
                .thenReturn(AutorizadorDTO.builder().message("Enviado").build());
    }

}
