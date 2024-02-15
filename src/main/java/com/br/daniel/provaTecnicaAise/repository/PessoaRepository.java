package com.br.daniel.provaTecnicaAise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.daniel.provaTecnicaAise.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
