package com.br.daniel.provaTecnicaAise.domain;

import com.br.daniel.provaTecnicaAise.converter.SituacaoParcelaConverter;
import com.br.daniel.provaTecnicaAise.enumerator.SituacaoParcela;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="debitoparcela", schema="aise")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DebitoParcela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="iddebitoparcela")
    private Long idDebitoParcela;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="iddebito", referencedColumnName = "iddebito")
    private Debito debito;

    @Column(name="parcela")
    @NotNull(message = "Número da Parcela é obrigatório")
    private Long parcela;

    @Column(name="datavencimento")
    @NotNull(message = "Data Vencimento é obrigatória")
    private LocalDate dataVencimento;

    @Column(name="valor")
    @NotNull(message = "Valor da Parcela deve ser maior que zero")
    private BigDecimal valor;

    @Enumerated(value = EnumType.STRING)
    @Column(name="situacao")
    @NotNull(message = "Situação da Parcela é obrigatória")
    @Convert(converter = SituacaoParcelaConverter.class)
    private SituacaoParcela situacao;
}
