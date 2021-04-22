package com.liviaaurich.carteiraservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "tb_transacao")
public class Transacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_transacao")
    @SequenceGenerator(name = "seq_transacao", allocationSize = 1, sequenceName = "seq_transacao")
    @Column(name = "id")
    private Long id;

    @Column(name = "valor")
    private Double valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pagador")
    private Usuario usuarioPagador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_beneficiario")
    private Usuario usuarioBeneficiado;

}
