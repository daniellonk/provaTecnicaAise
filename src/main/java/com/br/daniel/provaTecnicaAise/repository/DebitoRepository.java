package com.br.daniel.provaTecnicaAise.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.br.daniel.provaTecnicaAise.domain.Debito;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface DebitoRepository extends JpaRepository<Debito, Long> {
    List<Debito> findAllByPessoaIdPessoa(Long id);
    List<Debito> findAllByPessoaNomePessoa(String nome);
    List<Debito> findAllByPessoaPfjPessoa(String pfj);

    @Query("SELECT d FROM Debito d WHERE d.dataLancamento BETWEEN :dataInicial AND :dataFinal")
    Page<Debito> findByDataLancamentoBetween(LocalDate dataInicial, LocalDate dataFinal, Pageable pageable);
}
