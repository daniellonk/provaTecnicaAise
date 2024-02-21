package com.br.daniel.provaTecnicaAise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.daniel.provaTecnicaAise.domain.Pessoa;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findByPfjPessoa(String pfjPessoa);
    Optional<Pessoa> findByNomePessoa(String nomePessoa);
    boolean existsByPfjPessoa(String pfjPessoa);
}
