package br.gov.sp.fatec.springtopicos20252.service;

import java.util.List;

import br.gov.sp.fatec.springtopicos20252.entity.Estoque;

public interface EstoqueService {

    public List<Estoque> todos();

    public Estoque novo(Estoque estoque);

    public Estoque buscarPorId(Long id);

    public Estoque buscarPorNome(String nome);

    public List<Estoque> buscarPorNomeUsuarioEDataHoraDesde(String nomeUsuario, java.time.LocalDateTime dataHora);
    
}
