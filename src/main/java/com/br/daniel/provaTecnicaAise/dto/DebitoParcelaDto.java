package com.br.daniel.provaTecnicaAise.dto;

import com.br.daniel.provaTecnicaAise.converter.SituacaoParcelaConverter;
import com.br.daniel.provaTecnicaAise.domain.Debito;
import com.br.daniel.provaTecnicaAise.domain.DebitoParcela;
import com.br.daniel.provaTecnicaAise.enumerator.SituacaoParcela;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class DebitoParcelaDto {

    private Long idDebitoParcela;
    private Long parcela;
    private LocalDate dataVencimento;
    private BigDecimal valor;
    private SituacaoParcela situacao;

    public static DebitoParcelaDto newEntity (Long parcela, LocalDate dataVencimento, BigDecimal valor) {
        DebitoParcelaDto debitoParcelaDto = new DebitoParcelaDto();
        debitoParcelaDto.setParcela(parcela);
        debitoParcelaDto.setDataVencimento(dataVencimento);
        debitoParcelaDto.setSituacao(SituacaoParcela.ABERTO);
        debitoParcelaDto.setValor(valor);

        return debitoParcelaDto;
    }

    public static DebitoParcelaDto convertToDTO(DebitoParcela entity) {
        DebitoParcelaDto debitoParcelaDto = new DebitoParcelaDto();
        debitoParcelaDto.setIdDebitoParcela(entity.getIdDebitoParcela());
        debitoParcelaDto.setDataVencimento(entity.getDataVencimento());
        debitoParcelaDto.setParcela(entity.getParcela());
        debitoParcelaDto.setValor(entity.getValor());
        debitoParcelaDto.setSituacao(entity.getSituacao());
        return debitoParcelaDto;
    }
    public static DebitoParcela convertToEntity(DebitoParcelaDto debitoParcelaDto, Debito debito) {
        DebitoParcela entity = new DebitoParcela();
        entity.setParcela(debitoParcelaDto.getParcela());
        entity.setDataVencimento(debitoParcelaDto.getDataVencimento());
        entity.setParcela(debitoParcelaDto.getParcela());
        entity.setValor(debitoParcelaDto.getValor());
        entity.setSituacao(debitoParcelaDto.getSituacao());
        entity.setDebito(debito);

        return entity;
    }

    public static List<DebitoParcelaDto> convertToListDTO(List<DebitoParcela> debitosParcelas) {
        List<DebitoParcelaDto> debitoParcelaDtos = new ArrayList<>();

        for (DebitoParcela entity: debitosParcelas) {
            DebitoParcelaDto dto = new DebitoParcelaDto();
            dto.setIdDebitoParcela(entity.getIdDebitoParcela());
            dto.setParcela(entity.getParcela());
            dto.setSituacao(entity.getSituacao());
            dto.setDataVencimento(entity.getDataVencimento());
            dto.setValor(entity.getValor());

            debitoParcelaDtos.add(dto);
        }
        return debitoParcelaDtos;
    }

}
