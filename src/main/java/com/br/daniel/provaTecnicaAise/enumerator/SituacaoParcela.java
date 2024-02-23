package com.br.daniel.provaTecnicaAise.enumerator;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SituacaoParcela {
    ABERTO("ABERTO", "Aberto"),
    PAGO("PAGO", "Pago");

    private final String value;
    private final String descricao;

    SituacaoParcela(String value, String descricao){
        this.value = value;
        this.descricao = descricao;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public static SituacaoParcela fromString(String value) {
        if (SituacaoParcela.ABERTO.getValue().equals(value)) {
            return ABERTO;
        } else if (SituacaoParcela.PAGO.getValue().equals(value)) {
            return PAGO;
        }

        return null;
    }
}
