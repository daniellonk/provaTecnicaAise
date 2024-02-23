package com.br.daniel.provaTecnicaAise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.daniel.provaTecnicaAise.domain.Debito;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DebitoRepository extends JpaRepository<Debito, Long> {
    List<Debito> findAllByPessoaIdPessoa(Long id);
    List<Debito> findAllByPessoaNomePessoa(String nome);
    List<Debito> findAllByPessoaPfjPessoa(String pfj);
}
