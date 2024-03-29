package com.br.daniel.provaTecnicaAise.service;

import com.br.daniel.provaTecnicaAise.domain.Debito;
import com.br.daniel.provaTecnicaAise.domain.DebitoParcela;
import com.br.daniel.provaTecnicaAise.domain.Pessoa;
import com.br.daniel.provaTecnicaAise.dto.DebitoDto;
import com.br.daniel.provaTecnicaAise.dto.DebitoDtoPost;
import com.br.daniel.provaTecnicaAise.dto.DebitoParcelaDto;
import com.br.daniel.provaTecnicaAise.repository.DebitoRepository;
import com.br.daniel.provaTecnicaAise.repository.PessoaRepository;
import com.br.daniel.provaTecnicaAise.service.exception.RegraNegocioException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Data
@Component
@AllArgsConstructor
public class DebitoService {
    private DebitoRepository debitoRepository;
    private PessoaRepository pessoaRepository;

    public DebitoDto findById(Long id) {
        Debito found = this.debitoRepository.findById(id)
                .orElseThrow();
        return DebitoDto.convertToDTO(found);
    }

    public List<DebitoDto> findAllByPessoaIdPessoa(Long id) {
        List<DebitoDto> debitosDtos = this.debitoRepository.findAllByPessoaIdPessoa(id)
                .stream().map(DebitoDto::convertToDTO).toList();

        return debitosDtos;
    }

    public List<DebitoDto> findAllByPessoaPfjPessoa(String pfj) {
        List<DebitoDto> debitosDtos = this.debitoRepository.findAllByPessoaPfjPessoa(pfj)
                .stream().map(DebitoDto::convertToDTO).toList();

        return debitosDtos;
    }

    public List<DebitoDto> findAllByPessoaNomePessoa(String nome) {
        List<DebitoDto> debitosDtos = this.debitoRepository.findAllByPessoaNomePessoa(nome)
                .stream().map(DebitoDto::convertToDTO).toList();

        return debitosDtos;
    }

    public Double getValorTotal() {
        List<Debito> debitos = debitoRepository.findAll();
        double valorTotal = 0;

        for (Debito debito : debitos) {
            valorTotal = valorTotal + debito.getParcela().stream().mapToDouble(i -> i.getValor().doubleValue()).sum();
        }

        return valorTotal;
    }

    private void gravaDebitoParcela(Debito debito, DebitoDtoPost debitoDtoPost) {
        List<DebitoParcela> debitoParcela = debito.getParcela();
        for (DebitoParcelaDto debitoParcelaDto : debitoDtoPost.getDebitoParcela()) {

            DebitoParcela debitoParcelas = DebitoParcelaDto.convertToEntity(debitoParcelaDto, debito);

            if (debitoParcelas.validaDataVencimento()) {
                throw new RegraNegocioException("Data de Vencimento deve ser maior que hoje");
            }

            if (debitoParcelas.validaValorParcela()) {
                throw new RegraNegocioException("Valor da Parcela deve ser maior que zero");
            }

            debitoParcela.add(debitoParcelas);
        }
    }

    @Transactional
    public DebitoDto save(DebitoDtoPost debitoDtoPost) throws Exception {
        Pessoa pessoa = pessoaRepository.findById(debitoDtoPost.getIdPessoa()).orElseThrow();

        Debito debito = DebitoDtoPost.newEntity(debitoDtoPost);
        debito.setPessoa(pessoa);

        gravaDebitoParcela(debito, debitoDtoPost);

        if (debito.validaMinimoParcela()) {
            throw new RegraNegocioException("Obrigatório no minímo uma parcela");
        }

        if (debito.validaDataLancamento()) {
            throw new RegraNegocioException("Data Lançamento deve ser menor ou igual hoje");
        }

        Debito saved = debitoRepository.save(debito);
        return DebitoDto.convertToDTO(saved);
    }

    @Transactional
    public void delete(Long id) {
        Debito found;
        found = this.debitoRepository.findById(id).orElseThrow();
        getDebitoRepository().delete(found);
    }

    private Debito debitoFoundDebito(Long idDebito) {
        return this.debitoRepository.findById(idDebito).orElseThrow(() -> new RegraNegocioException("Débito não encontrado"));
    }

    @Transactional
    public Debito updateDataVencimento(Long idDebito, Long parcela, LocalDate dataVencimento) {
        Debito debito = debitoFoundDebito(idDebito);

        DebitoParcela debitoParcela = debito.getParcela().stream()
                .filter(debitoParcela1 -> debitoParcela1.getParcela().equals(parcela))
                .findFirst().orElseThrow(() -> new RegraNegocioException("Parcela não encontrada para o débito"));

        debitoParcela.setDataVencimento(dataVencimento);

        return this.debitoRepository.save(debito);
    }
    public Page<Debito> getDebitosPaginados(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return debitoRepository.findAll(pageRequest);
    }

    @Transactional
    public Page<Debito> getDebitosPaginadosComParametro(int pageNumber, int pageSize, LocalDate dataInicial, LocalDate dataFinal, String pfj, String nome) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        if (dataInicial != null && dataFinal != null) {
            return debitoRepository.findByDataLancamentoBetween(dataInicial, dataFinal, pageRequest);
        } else {
            return this.debitoRepository.findAll(pageRequest);
        }
    }
}

