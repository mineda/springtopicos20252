package br.gov.sp.fatec.springtopicos20252.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.springtopicos20252.entity.Revisao;
import br.gov.sp.fatec.springtopicos20252.service.RevisaoService;

@RestController
@CrossOrigin
@RequestMapping(value = "/revisao")
public class RevisaoController {

    private RevisaoService service;

    public RevisaoController(RevisaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Revisao>> buscarTodos() {
        return new ResponseEntity<List<Revisao>>(service.todas(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Revisao> nova(@RequestBody Revisao revisao) {
        Revisao nova = service.nova(revisao);
        return ResponseEntity.created(URI.create("/revisao/" + nova.getId())).body(nova);
    }

    @GetMapping(value = "/busca")
    public ResponseEntity<List<Revisao>> buscarPorTituloSecaoEDataHoraCriacao(@RequestParam("secao") String secao, @RequestParam("dataHoraCriacao") LocalDateTime dataHoraCriacao) {
        return new ResponseEntity<List<Revisao>>(service.buscarPorTituloSecaoEDataHoraCriacao(secao, dataHoraCriacao), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Revisao> buscarPorId(@PathVariable("id") Long id) {
        return new ResponseEntity<Revisao>(service.buscarPorId(id), HttpStatus.OK);
    }
    
}