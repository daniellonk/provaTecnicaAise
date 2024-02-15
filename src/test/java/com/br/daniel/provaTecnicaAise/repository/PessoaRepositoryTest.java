package com.br.daniel.provaTecnicaAise.repository;

import com.br.daniel.provaTecnicaAise.domain.Pessoa;
import com.br.daniel.provaTecnicaAise.enumerator.TipoPessoa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class PessoaRepositoryTest {

    @Autowired
    PessoaRepository repository;

    @Test
    public void deveVerificarAExistenciaDeUmPfj(){
        Pessoa pessoa = Pessoa.builder().nomePessoa("Daniel").pfjPessoa("123456789").tipo(TipoPessoa.FISICA).build();
        repository.save(pessoa);

        boolean result = repository.existsByPfjPessoa("123456789");

        Assertions.assertTrue(result);
    }

    @Test
    public void deveRetornarFalseQuandoNaoEncontraroPfj(){
        repository.deleteAll();

        boolean result = repository.existsByPfjPessoa("123456789");

        Assertions.assertFalse(result);
    }
}
