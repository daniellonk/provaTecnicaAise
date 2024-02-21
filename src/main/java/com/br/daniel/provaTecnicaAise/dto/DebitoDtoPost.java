package com.br.daniel.provaTecnicaAise.dto;

import com.br.daniel.provaTecnicaAise.domain.Debito;
import com.br.daniel.provaTecnicaAise.domain.Pessoa;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DebitoDtoPost {

    private Long idDebito = 0L;
    private Long IdPessoa;
    private LocalDate dataLancamento;
    private String origemDebito;

    public static Debito newEntity(DebitoDtoPost debitoDtoPost) {
        Debito debito = new Debito();
        debito.setDataLancamento(debitoDtoPost.getDataLancamento());
        debito.setOrigemDebito(debitoDtoPost.getOrigemDebito());

        return debito;
    }
}
