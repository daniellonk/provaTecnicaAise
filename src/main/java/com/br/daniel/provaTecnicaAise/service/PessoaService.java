package com.br.daniel.provaTecnicaAise.service;

import com.br.daniel.provaTecnicaAise.domain.Pessoa;
import com.br.daniel.provaTecnicaAise.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public Pessoa salvarPessoa(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    void validarPFJ(String pfjPessoa){
    }

    public Pessoa findById(Long id){
        return pessoaRepository.findById(id).orElseThrow((() -> new RuntimeException("Pessoa não encontrada")));
    }

}
