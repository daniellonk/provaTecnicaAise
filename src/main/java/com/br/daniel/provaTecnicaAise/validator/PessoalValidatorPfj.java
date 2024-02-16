package com.br.daniel.provaTecnicaAise.validator;

import com.br.daniel.provaTecnicaAise.domain.Pessoa;
import com.br.daniel.provaTecnicaAise.enumerator.CnpjGroup;
import com.br.daniel.provaTecnicaAise.enumerator.CpfGroup;
import com.br.daniel.provaTecnicaAise.enumerator.TipoPessoa;
import jakarta.validation.GroupSequence;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@GroupSequence({CpfGroup.class, CnpjGroup.class, Pessoa.class })
public class PessoalValidatorPfj implements DefaultGroupSequenceProvider<Pessoa> {
    @Override
    public List<Class<?>> getValidationGroups(Pessoa pessoa) {
        if (pessoa != null && pessoa.getTipo() != null) {
            if (TipoPessoa.FISICA.equals(pessoa.getTipo())) {
                return Arrays.asList(CpfGroup.class, Pessoa.class);
            } else if (TipoPessoa.JURIDICA.equals(pessoa.getTipo())) {
                return Arrays.asList(CnpjGroup.class, Pessoa.class);
            }
        }
        return Collections.singletonList(Pessoa.class);
    }
}
