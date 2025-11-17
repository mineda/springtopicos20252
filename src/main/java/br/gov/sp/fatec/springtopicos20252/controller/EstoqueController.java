package br.gov.sp.fatec.springtopicos20252.controller;

import java.net.URI;
import java.time.LocalDateTime;
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

import br.gov.sp.fatec.springtopicos20252.entity.Estoque;
import br.gov.sp.fatec.springtopicos20252.service.EstoqueService;

@RestController
@CrossOrigin
@RequestMapping("/estoque")
public class EstoqueController {

    private EstoqueService service;

    public EstoqueController(EstoqueService service) {
        this.service = service;
    }

    @GetMapping
    public List<Estoque> todos() {
        return service.todos();
    }

    @PostMapping
    public ResponseEntity<Estoque> adicionar(@RequestBody Estoque estoque) {
        Estoque novo = service.novo(estoque);
        return ResponseEntity.created(URI.create("/estoque/" + novo.getId())).body(novo);
    }

    @GetMapping("/{id}")
    public Estoque buscarPorId(@PathVariable("id") Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/buscar")
    public List<Estoque> buscarPorNomeUsuarioEDataHoraDesde(@RequestParam("usuario") String nomeUsuario, @RequestParam("dataHora") LocalDateTime dataHora) {
        return service.buscarPorNomeUsuarioEDataHoraDesde(nomeUsuario, dataHora);
    }

}