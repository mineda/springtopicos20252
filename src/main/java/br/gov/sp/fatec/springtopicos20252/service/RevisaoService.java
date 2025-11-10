package br.gov.sp.fatec.springtopicos20252.service;

import java.time.LocalDateTime;
import java.util.List;

import br.gov.sp.fatec.springtopicos20252.entity.Revisao;

public interface RevisaoService {

    public Revisao nova(Revisao revisao);

    public List<Revisao> todas();

    public List<Revisao> buscarPorTituloSecaoEDataHoraCriacao(String tituloSecao, LocalDateTime dataHoraCriacao);

    public Revisao buscarPorId(Long id);
    
}