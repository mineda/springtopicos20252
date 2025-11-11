package br.gov.sp.fatec.springtopicos20252.service;

import java.math.BigDecimal;
import java.util.List;

import br.gov.sp.fatec.springtopicos20252.entity.Item;

public interface ItemService {
    
    public Item novo(Item Item);

    public List<Item> todos();

    public Item buscarPorId(Long id);

    public List<Item> buscarPorDescricaoListaEPrecoFinal(String descricaoLista, BigDecimal precoFinal);

}
