package br.gov.sp.fatec.springtopicos20252.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.springtopicos20252.entity.Item;
import br.gov.sp.fatec.springtopicos20252.service.ItemService;

@RestController
@CrossOrigin
@RequestMapping(value = "/item")
public class ItemController {

    private ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<Item> todos() {
        return service.todos();
    }

    @GetMapping("/{id}")
    public Item buscarPorId(@PathVariable("id") Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/buscar")
    public List<Item> buscarPorDescricaoListaEPrecoFinal(@RequestParam("lista") String descricaoLista, @RequestParam("preco") BigDecimal precoFinal) {
        return service.buscarPorDescricaoListaEPrecoFinal(descricaoLista, precoFinal);
    }

    @PostMapping
    public ResponseEntity<Item> criar(@RequestBody Item item) {
        Item criado = service.novo(item);
        return ResponseEntity.created(URI.create("/item/" + criado.getId())).body(criado);
    }

}
