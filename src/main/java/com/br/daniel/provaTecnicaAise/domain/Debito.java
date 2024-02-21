package com.br.daniel.provaTecnicaAise.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

import java.time.LocalDate;

@Entity
@Table(name = "debito", schema = "aise")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Debito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="iddebito")
    private Long idDebito;

    @ManyToOne
    @JoinColumn(name="idpessoa")
    private Pessoa pessoa;

    @Column(name="datalancamento")
    private LocalDate dataLancamento;

    @Column(name="origemdebito")
    private String origemDebito;
}
