package br.gov.sp.fatec.springtopicos20252.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springtopicos20252.entity.Item;
import br.gov.sp.fatec.springtopicos20252.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository repo;

    private ListaService listaService;

    public ItemServiceImpl(ItemRepository repo, ListaService listaService) {
        this.repo = repo;
        this.listaService = listaService;
    }

    @Override
    public Item novo(Item Item) {
        if (Item == null ||
                Item.getPreco() == null ||
                Item.getPreco().doubleValue() <= 0 ||
                Item.getDescricao() == null ||
                Item.getDescricao().isBlank() ||
                Item.getLista() == null ||
                Item.getLista().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados inválidos!");
        }
        if(Item.getPercentualDesconto() != null &&
                (Item.getPercentualDesconto() < 0 ||
                Item.getPercentualDesconto() > 100)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Desconto inválido!");
        }
        Item.setId(null); // garante que é um novo registro
        Item.setLista(listaService.buscarPorId(Item.getLista().getId()));
        return repo.save(Item);
    }

    @Override
    public List<Item> todos() {
        return repo.findAll();
    }

    @Override
    public Item buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!"));
    }

    @Override
    public List<Item> buscarPorDescricaoListaEPrecoFinal(String descricaoLista, BigDecimal precoFinal) {
        return repo.buscarPorDescricaoListaEPrecoFinal(descricaoLista, precoFinal);
    }
    
}
