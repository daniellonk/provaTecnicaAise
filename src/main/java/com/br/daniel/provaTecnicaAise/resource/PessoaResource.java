package com.br.daniel.provaTecnicaAise.resource;

import com.br.daniel.provaTecnicaAise.domain.Pessoa;
import com.br.daniel.provaTecnicaAise.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/pessoa")
@RequiredArgsConstructor
public class PessoaResource {

    private final PessoaService pessoaService;

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable Long id){
        return ResponseEntity.ok(pessoaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa){
        return ResponseEntity.created(URI.create("")).body(pessoaService.salvarPessoa(pessoa));
    }

}
