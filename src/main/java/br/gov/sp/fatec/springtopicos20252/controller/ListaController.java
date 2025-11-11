package br.gov.sp.fatec.springtopicos20252.controller;

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

import br.gov.sp.fatec.springtopicos20252.entity.Lista;
import br.gov.sp.fatec.springtopicos20252.service.ListaService;

@RestController
@CrossOrigin
@RequestMapping(value = "/lista")
public class ListaController {

    private ListaService service;

    public ListaController(ListaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Lista> todas() {
        return service.todas();
    }

    @GetMapping("/buscar")
    public List<Lista> buscarPorTextoAnotacaoEDescricao(@RequestParam("anotacao") String textoAnotacao, @RequestParam("descricao") String descricao) {
        return service.buscarPorTextoAnotacaoEDescricao(textoAnotacao, descricao);
    }

    @GetMapping("/{id}")
    public Lista buscarPorId(@PathVariable("id") Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Lista> criar(@RequestBody Lista lista) {
        Lista criada = service.nova(lista);
        return ResponseEntity.created(URI.create("/lista/" + criada.getId())).body(criada);
    }

}
