package com.br.daniel.provaTecnicaAise.enumerator;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TipoPessoa {
    FISICA("FISICA", "Física", CpfGroup.class),
    JURIDICA("JURIDICA", "Jurídica", CnpjGroup.class);

    private final String value;
    private final String descricao;
    private final Class<?> group;

    TipoPessoa(String value, String descricao, Class<?> group){
        this.value = value;
        this.descricao = descricao;
        this.group = group;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public static TipoPessoa fromString(String value) {
        if (TipoPessoa.FISICA.getValue().equals(value)) {
            return FISICA;
        } else if (TipoPessoa.JURIDICA.getValue().equals(value)) {
            return JURIDICA;
        }

        return null;
    }
}