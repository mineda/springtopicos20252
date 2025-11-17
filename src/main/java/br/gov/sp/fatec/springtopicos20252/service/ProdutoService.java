package br.gov.sp.fatec.springtopicos20252.service;

import java.util.List;

import br.gov.sp.fatec.springtopicos20252.entity.Produto;

public interface ProdutoService {

    public List<Produto> todos();

    public Produto novo(Produto produto);

    public Produto buscarPorId(Long id);

    void incrementarProduto(String nomeEstoque, String descricao);
}
