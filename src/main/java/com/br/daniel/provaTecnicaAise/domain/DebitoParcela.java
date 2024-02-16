package com.br.daniel.provaTecnicaAise.domain;

import com.br.daniel.provaTecnicaAise.enumerator.SituacaoParcela;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="debitoParcela", schema="aise")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DebitoParcela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idDebitoparcela")
    private Long idDebitoParcela;

    @ManyToOne
    @JoinColumn
    @Column(name="iddebito")
    private Debito idDebito;

    @Column(name="parcela")
    private int parcela;

    @Column(name="datavencimento")
    private LocalDate dataVencimento;

    @Column(name="valor")
    private BigDecimal valor;

    @Enumerated(value = EnumType.STRING)
    @Column(name="situacao")
    private SituacaoParcela situacao;
}
