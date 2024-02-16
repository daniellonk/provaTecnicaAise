package com.br.daniel.provaTecnicaAise.converter;

import com.br.daniel.provaTecnicaAise.enumerator.TipoPessoa;
import jakarta.persistence.AttributeConverter;

import java.util.Objects;

public class TipoPessoaConverter implements AttributeConverter<TipoPessoa, String> {

    @Override
    public String convertToDatabaseColumn(TipoPessoa attribute) {
        if (Objects.isNull(attribute)) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public TipoPessoa convertToEntityAttribute(String dbData) {
        return TipoPessoa.fromString(dbData);
    }
}
