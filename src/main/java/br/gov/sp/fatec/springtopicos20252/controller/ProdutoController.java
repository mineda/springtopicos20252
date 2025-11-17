package br.gov.sp.fatec.springtopicos20252.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.springtopicos20252.entity.Produto;
import br.gov.sp.fatec.springtopicos20252.service.ProdutoService;

@RestController
@CrossOrigin
@RequestMapping("/produto")
public class ProdutoController {

    private ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Produto> todos() {
        return service.todos();
    }

    @PatchMapping
    public void incrementarProduto(@RequestParam("estoque") String nomeEstoque, @RequestParam("descricao") String descricao) {
        service.incrementarProduto(nomeEstoque, descricao);
    }

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable("id") Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
        Produto criado = service.novo(produto);
        return ResponseEntity.created(URI.create("/produto/" + criado.getId())).body(criado);
    }

}