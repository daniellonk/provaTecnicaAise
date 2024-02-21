package com.br.daniel.provaTecnicaAise.resource;

import com.br.daniel.provaTecnicaAise.dto.DebitoDto;
import com.br.daniel.provaTecnicaAise.dto.DebitoDtoPost;
import com.br.daniel.provaTecnicaAise.service.DebitoService;
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
@RequestMapping("/api/debito")
@RequiredArgsConstructor
public class DebitoResource {
    private final DebitoService debitoService;

    @GetMapping("/{id}")
    public ResponseEntity<DebitoDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(this.debitoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<DebitoDto> save(@RequestBody DebitoDtoPost debitoDtoPost) throws Exception{
        return ResponseEntity.ok(this.debitoService.save(debitoDtoPost));
    }

}
