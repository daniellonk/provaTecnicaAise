package com.br.daniel.provaTecnicaAise.service;

import com.br.daniel.provaTecnicaAise.domain.Pessoa;
import com.br.daniel.provaTecnicaAise.repository.PessoaRepository;
import com.br.daniel.provaTecnicaAise.service.exception.RegraNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public Pessoa salvarPessoa(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    void validarPfjExiste(String pfjPessoa){
        boolean exists = pessoaRepository.existsByPfjPessoa(pfjPessoa);
        if(exists){
            throw new RegraNegocioException("Já existe uma pessoa com esse CPF/CNPJ");
        }
    }

    public Pessoa findById(Long id){
        return pessoaRepository.findById(id).orElseThrow((() -> new RuntimeException("Pessoa não encontrada")));
    }


}
