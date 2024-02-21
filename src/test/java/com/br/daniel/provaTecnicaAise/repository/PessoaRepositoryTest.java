package com.br.daniel.provaTecnicaAise.repository;

import com.br.daniel.provaTecnicaAise.domain.Pessoa;
import com.br.daniel.provaTecnicaAise.enumerator.TipoPessoa;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PessoaRepositoryTest {

    @Autowired
    PessoaRepository repository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void deveInserirUmaPessoaNoBanco(){
        Pessoa pessoa = criarPessoa();

        Pessoa pessoaSave = repository.save(pessoa);

        Assertions.assertTrue(pessoaSave.getIdPessoa().intValue()>0);
    }

    @Test
    public void deveBuscarUmaPessoaPorPFJ(){
        Pessoa pessoa = criarPessoa();
        entityManager.persist(pessoa);

        Optional<Pessoa> result = repository.findByPfjPessoa("05884843933");

        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void deveRetornarVazioQuandoBuscarUmaPessoaPorPfjQueNaoExiste(){
        Optional<Pessoa> result = repository.findByPfjPessoa("05884843933");

        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void deveVerificarAExistenciaDeUmPfj(){
        Pessoa pessoa = Pessoa.builder().nomePessoa("Daniel").pfjPessoa("05884843933").tipo(TipoPessoa.FISICA).build();
        entityManager.persist(pessoa);

        boolean result = repository.existsByPfjPessoa("05884843933");

        Assertions.assertTrue(result);
    }

    @Test
    public void deveRetornarFalseQuandoNaoEncontraroPfj(){
        boolean result = repository.existsByPfjPessoa("05884843933");

        Assertions.assertFalse(result);
    }

    @Test
    public void deveRetornarErroQuandoCpfForInvalido(){
        Pessoa pessoa = criarPessoa();

        Throwable e = org.assertj.core.api.Assertions.catchThrowable(() -> repository.save(pessoa));

        assertThat(e).isInstanceOf(ConstraintViolationException.class).hasMessageContaining("No value present");
    }
    @Test
    public void deveRetornarErroQuandoCnpjForInvalido(){
        Pessoa pessoa = Pessoa.builder()
                              .nomePessoa("Daniel")
                              .pfjPessoa("50290260000117")
                              .tipo(TipoPessoa.JURIDICA)
                              .build();

        Throwable e = org.assertj.core.api.Assertions.catchThrowable(() -> repository.save(pessoa));

        assertThat(e).isInstanceOf(ConstraintViolationException.class).hasMessageContaining("No value present");
    }

    public static Pessoa criarPessoa() {
        return Pessoa.builder()
                .nomePessoa("Daniel")
                .pfjPessoa("05884843933")
                .tipo(TipoPessoa.FISICA)
                .build();
    }

}
