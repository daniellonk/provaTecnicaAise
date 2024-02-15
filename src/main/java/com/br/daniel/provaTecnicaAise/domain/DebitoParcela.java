package com.br.daniel.provaTecnicaAise.domain;

import com.br.daniel.provaTecnicaAise.enumerator.SituacaoParcela;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name="debitoParcela", schema="aise")
@Builder
@Data
public class DebitoParcela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDebitoParcela;

    @ManyToOne
    @JoinColumn
    private Debito idDebito;

    private int parcela;

    private LocalDate dataVencimento;

    private BigDecimal valor;

    @Enumerated(value = EnumType.STRING)
    private SituacaoParcela situacao;

}
