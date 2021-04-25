package com.liviaaurich.carteiraservice.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CarteiraDTO implements Serializable {

    private Long id;

    private Double dinheiro = 0.0;

}
