package com.br.daniel.provaTecnicaAise.domain;

import com.br.daniel.provaTecnicaAise.converter.TipoPessoaConverter;
import com.br.daniel.provaTecnicaAise.enumerator.CnpjGroup;
import com.br.daniel.provaTecnicaAise.enumerator.CpfGroup;
import com.br.daniel.provaTecnicaAise.enumerator.TipoPessoa;
import com.br.daniel.provaTecnicaAise.validator.PessoalValidatorPfj;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

@Entity
@Table( name = "pessoa", schema = "aise" )
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@GroupSequenceProvider(PessoalValidatorPfj.class)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idpessoa")
    private Long idPessoa;

    @Column(name="nomepessoa")
    private String nomePessoa;

    @Column(name="pfjpessoa")
    @CPF(groups = CpfGroup.class)
    @CNPJ(groups = CnpjGroup.class)
    private String pfjPessoa;

    @Column(name="tipo")
    @Convert(converter = TipoPessoaConverter.class)
    private TipoPessoa tipo;
}
