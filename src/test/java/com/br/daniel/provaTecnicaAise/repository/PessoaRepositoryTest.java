package com.br.daniel.provaTecnicaAise.repository;

import com.br.daniel.provaTecnicaAise.domain.Pessoa;
import com.br.daniel.provaTecnicaAise.enumerator.TipoPessoa;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class PessoaRepositoryTest {

    @Autowired
    PessoaRepository repository;

    @Test
    public void deveVerificarAExistenciaDeUmPfj(){
        Pessoa pessoa = Pessoa.builder().nomePessoa("Daniel").pfjPessoa("05884843933").tipo(TipoPessoa.FISICA).build();
        repository.save(pessoa);

        boolean result = repository.existsByPfjPessoa("05884843933");

        Assertions.assertTrue(result);
    }

    @Test
    public void deveRetornarFalseQuandoNaoEncontraroPfj(){
        repository.deleteAll();

        boolean result = repository.existsByPfjPessoa("05884843933");

        Assertions.assertFalse(result);
    }

    @Test
    public void deveRetornarErroQuandoCpfForInvalido(){
        Pessoa pessoa = Pessoa.builder().nomePessoa("Daniel").pfjPessoa("05884843933").tipo(TipoPessoa.FISICA).build();

        Throwable e = org.assertj.core.api.Assertions.catchThrowable(() -> repository.save(pessoa));

        assertThat(e).isInstanceOf(ConstraintViolationException.class).hasMessageContaining("No value present");
    }

    @Test
    public void deveRetornarErroQuandoCnpjForInvalido(){
        Pessoa pessoa = Pessoa.builder().nomePessoa("Daniel").pfjPessoa("50290260000117").tipo(TipoPessoa.JURIDICA).build();

        Throwable e = org.assertj.core.api.Assertions.catchThrowable(() -> repository.save(pessoa));

        assertThat(e).isInstanceOf(ConstraintViolationException.class).hasMessageContaining("No value present");
    }
}
