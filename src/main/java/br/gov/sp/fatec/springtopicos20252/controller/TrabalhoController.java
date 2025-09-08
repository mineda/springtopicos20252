package br.gov.sp.fatec.springtopicos20252.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.springtopicos20252.entity.Trabalho;
import br.gov.sp.fatec.springtopicos20252.service.TrabalhoService;

@RestController
@CrossOrigin
@RequestMapping(value = "/trabalho")
public class TrabalhoController {

    @Autowired
    private TrabalhoService service;

    @GetMapping
    public ResponseEntity<List<Trabalho>> buscarTodos() {
        return ResponseEntity.ok().body(service.buscarTodos());
    }
    
    @PostMapping
    public ResponseEntity<Trabalho> cadastrarNovo(@RequestBody Trabalho trabalho) {
        trabalho = service.novoTrabalho(trabalho);
        return ResponseEntity
            .created(URI.create("/trabalho"))
            .body(trabalho);
    }

    @GetMapping(value = "/tituloNomeUsuario")
    public ResponseEntity<List<Trabalho>> buscarPorTituloENomeUsuario(@RequestParam("titulo") String titulo, @RequestParam("nome") String nomeUsuario) {
        return ResponseEntity.ok().body(service.buscarPorTituloENomeUsuario(titulo, nomeUsuario));
    }
}
