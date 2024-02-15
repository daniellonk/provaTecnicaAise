package com.br.daniel.provaTecnicaAise.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="debitoParcelaPagamento", schema="aise")
@Builder
@Data
public class DebitoParcelaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDebitoParcelaPag;

    @ManyToOne
    @JoinColumn
    private DebitoParcela idDebitoParcela;

    private LocalDate dataPagamento;

    private BigDecimal valor;

    private BigDecimal juros;

    private BigDecimal multa;

    private BigDecimal correcao;
}
