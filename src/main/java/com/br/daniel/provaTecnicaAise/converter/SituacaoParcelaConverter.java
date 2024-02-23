package com.br.daniel.provaTecnicaAise.converter;

import com.br.daniel.provaTecnicaAise.enumerator.SituacaoParcela;
import com.br.daniel.provaTecnicaAise.enumerator.TipoPessoa;
import jakarta.persistence.AttributeConverter;

import java.util.Objects;

public class SituacaoParcelaConverter implements AttributeConverter<SituacaoParcela, String> {

    @Override
    public String convertToDatabaseColumn(SituacaoParcela attribute) {
        if (Objects.isNull(attribute)) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public SituacaoParcela convertToEntityAttribute(String dbData) {
        return SituacaoParcela.fromString(dbData);
    }
}

