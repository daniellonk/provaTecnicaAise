package com.br.daniel.provaTecnicaAise.service;

import com.br.daniel.provaTecnicaAise.repository.PessoaRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PessoaServiceTest {

    @InjectMocks
    PessoaService service;
    @Mock
    PessoaRepository repository;
    @Before
    public void setUp(){
        service = new PessoaService(repository);
    }

    @Test
    public void deveValidarPfj(){
        Mockito.when(repository.existsByPfjPessoa(Mockito.anyString())).thenReturn(false);

        service.validarPfjExiste("05884843933");
    }

    @Test
    public void deveLancarErroQuandoEncontrarPfjCadastrado(){
        Mockito.when(repository.existsByPfjPessoa(Mockito.anyString())).thenReturn(true);

        service.validarPfjExiste("05884843933");
    }
}
