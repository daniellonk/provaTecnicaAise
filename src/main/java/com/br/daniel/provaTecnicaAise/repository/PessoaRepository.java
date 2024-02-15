package com.br.daniel.provaTecnicaAise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.daniel.provaTecnicaAise.domain.Pessoa;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findByPfjPessoa(String pfjPessoa);
    Optional<Pessoa> findByNomePessoa(String nomePessoa);
    boolean existsByPfjPessoa(String pfjPessoa);
}
