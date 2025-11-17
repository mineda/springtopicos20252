package br.gov.sp.fatec.springtopicos20252.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.springtopicos20252.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Modifying
    @Query("update Produto p set p.contador = p.contador + 1 where p.estoque.nome = :nomeEstoque and p.descricao = :descricao")
    public void incrementarProduto(String nomeEstoque, String descricao);
    
}
