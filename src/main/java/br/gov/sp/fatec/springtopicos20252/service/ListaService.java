package br.gov.sp.fatec.springtopicos20252.service;

import java.util.List;

import br.gov.sp.fatec.springtopicos20252.entity.Lista;

public interface ListaService {

    public Lista nova(Lista lista);

    public List<Lista> todas();

    public Lista buscarPorId(Long id);

    public List<Lista> buscarPorTextoAnotacaoEDescricao(String textoAnotacao, String descricao);
}
