package com.br.daniel.provaTecnicaAise.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "debito", schema = "aise")
@Data
@NoArgsConstructor
public class Debito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDebito;

    @ManyToOne
    @JoinColumn
    private Pessoa idPessoa;

    private LocalDate dataLancamento;

    private String origemDebito;
}
