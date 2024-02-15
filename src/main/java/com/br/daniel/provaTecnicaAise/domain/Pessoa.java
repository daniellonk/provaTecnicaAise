package com.br.daniel.provaTecnicaAise.domain;

import com.br.daniel.provaTecnicaAise.enumerator.TipoPessoa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "pessoa", schema = "aise" )
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idpessoa")
    private Long idPessoa;

    @Column(name="nomepessoa")
    private String nomePessoa;

    @Column(name="pfjpessoa")
    private String pfjPessoa;

    @Column(name="tipo")
    @Enumerated(value = EnumType.STRING)
    private TipoPessoa tipo;
}
