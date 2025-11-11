package br.gov.sp.fatec.springtopicos20252.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.springtopicos20252.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("select i from Item i where i.lista.descricao like %:descricao% and (i.preco - (i.preco * i.percentualDesconto / 100))  <= :precoFinal")
    public List<Item> buscarPorDescricaoListaEPrecoFinal(String descricao, BigDecimal precoFinal);
    
}
