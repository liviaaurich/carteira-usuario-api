package com.liviaaurich.carteiraservice.service.feign;

import com.liviaaurich.carteiraservice.service.dto.AutorizadorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;

@Profile("!test")
@FeignClient(name = "${autorizador.name}", url = "${autorizador.url}")
public interface MockFeignClient {

    @GetMapping(value = "/8fafdd68-a090-496f-8c9a-3442cf30dae6")
    AutorizadorDTO autorizarTransferencia();

    @GetMapping(value = "/b19f7b9f-9cbf-4fc6-ad22-dc30601aec04")
    AutorizadorDTO emitirNotificacao(Long id);

}
