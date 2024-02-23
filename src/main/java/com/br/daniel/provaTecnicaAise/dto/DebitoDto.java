package com.br.daniel.provaTecnicaAise.dto;

import com.br.daniel.provaTecnicaAise.domain.Debito;
import com.br.daniel.provaTecnicaAise.domain.Pessoa;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DebitoDto {

    private Long idDebito;
    private Pessoa pessoa;
    private LocalDate dataLancamento;
    private String origemDebito;

    private List<DebitoParcelaDto> debitoParcelasDto;

    public static DebitoDto convertToDTO(Debito entity) {
        DebitoDto dto = new DebitoDto();
        dto.setIdDebito(entity.getIdDebito());
        dto.setPessoa(entity.getPessoa());
        dto.setDataLancamento(entity.getDataLancamento());
        dto.setOrigemDebito(entity.getOrigemDebito());
        dto.setDebitoParcelasDto(DebitoParcelaDto.convertToListDTO(entity.getParcela()));

        return dto;
    }
    public static Debito convertToEntity(DebitoDto dto) {
        Debito entity = new Debito();
        entity.setPessoa(dto.getPessoa());
        entity.setDataLancamento(dto.getDataLancamento());
        entity.setOrigemDebito(dto.getOrigemDebito());

        return entity;
    }
}
