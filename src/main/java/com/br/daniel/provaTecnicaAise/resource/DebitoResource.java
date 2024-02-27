package com.br.daniel.provaTecnicaAise.resource;

import com.br.daniel.provaTecnicaAise.domain.Debito;
import com.br.daniel.provaTecnicaAise.dto.DebitoDto;
import com.br.daniel.provaTecnicaAise.dto.DebitoDtoPost;
import com.br.daniel.provaTecnicaAise.service.DebitoService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/debito")
@RequiredArgsConstructor
@Data
public class DebitoResource {
    private final DebitoService debitoService;

    @GetMapping("/{id}")
    public ResponseEntity<DebitoDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(this.debitoService.findById(id));
    }

    @GetMapping("/pessoa/{id}")
    public ResponseEntity<List<DebitoDto>> findAllByPessoaIdPessoa(@PathVariable @Valid Long id){
        return ResponseEntity.ok(this.debitoService.findAllByPessoaIdPessoa(id));
    }

    @GetMapping("/pessoa/pfj/{pfj}")
    public ResponseEntity<List<DebitoDto>> findAllByPessoaPfjPessoa(@PathVariable @Valid String pfj){
        return ResponseEntity.ok(this.debitoService.findAllByPessoaPfjPessoa(pfj));
    }
    @GetMapping("/pessoa/nome/{nome}")
    public ResponseEntity<List<DebitoDto>> findAllByPessoaNomePessoa(@PathVariable @Valid String nome){
        return ResponseEntity.ok(this.debitoService.findAllByPessoaNomePessoa(nome));
    }
    @RequestMapping("/valortotallancado")
    @GetMapping
    public ResponseEntity<Double> getValorTotal() {
        Double valorTotalDebitos = this.getDebitoService().getValorTotal();
        return ResponseEntity.ok(valorTotalDebitos);
    }
    @PostMapping
    public ResponseEntity<DebitoDto> save(@RequestBody DebitoDtoPost debitoDtoPost) throws Exception{
        return ResponseEntity.ok(this.debitoService.save(debitoDtoPost));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Valid Long id) {
        this.getDebitoService().delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateDataVencimento/{id}/{parcela}/{datavencimento}")
    public ResponseEntity<Debito> updateDataVencimento(@PathVariable Long id, @PathVariable Long parcela, @PathVariable LocalDate datavencimento) {
        return ResponseEntity.ok(this.getDebitoService().updateDataVencimento(id, parcela, datavencimento));
    }



}
