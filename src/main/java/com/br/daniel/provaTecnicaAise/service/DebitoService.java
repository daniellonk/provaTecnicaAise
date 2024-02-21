package com.br.daniel.provaTecnicaAise.service;

import com.br.daniel.provaTecnicaAise.domain.Debito;
import com.br.daniel.provaTecnicaAise.domain.Pessoa;
import com.br.daniel.provaTecnicaAise.dto.DebitoDto;
import com.br.daniel.provaTecnicaAise.dto.DebitoDtoPost;
import com.br.daniel.provaTecnicaAise.repository.DebitoRepository;
import com.br.daniel.provaTecnicaAise.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DebitoService {
    private DebitoRepository debitoRepository;
    private PessoaRepository pessoaRepository;

    public DebitoDto findById(Long id){
        Debito found = this.debitoRepository.findById(id)
                .orElseThrow();
        return DebitoDto.convertToDTO(found);
    }

    @Transactional
    public DebitoDto save(DebitoDtoPost dto) throws Exception {
        Pessoa pessoa = pessoaRepository.findById(dto.getIdPessoa()).orElseThrow();

        Debito debitoToSave = DebitoDtoPost.newEntity(dto);

        debitoToSave.setPessoa(pessoa);

        Debito saved = debitoRepository.save(debitoToSave);

        return DebitoDto.convertToDTO(saved);
    }

}
