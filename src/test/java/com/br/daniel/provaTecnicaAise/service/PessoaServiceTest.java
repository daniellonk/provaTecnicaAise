package com.br.daniel.provaTecnicaAise.service;

import com.br.daniel.provaTecnicaAise.domain.Pessoa;
import com.br.daniel.provaTecnicaAise.enumerator.TipoPessoa;
import com.br.daniel.provaTecnicaAise.repository.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class PessoaServiceTest {

    @Autowired
    PessoaService service;

    @Autowired
    PessoaRepository repository;

    @Test
    public void deveValidarPfj(){
        repository.deleteAll();

        service.validarPFJ("05884843933");
    }

    @Test
    public void deveLancarErroQuandoEncontrarPfjCadastrado(){
        Pessoa pessoa = Pessoa.builder().nomePessoa("Daniel").pfjPessoa("05884843933").tipo(TipoPessoa.FISICA).build();
        repository.save(pessoa);

        service.validarPFJ("05884843933");
    }
}
