package br.gov.sp.fatec.springtopicos20252.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.springtopicos20252.entity.Secao;
import br.gov.sp.fatec.springtopicos20252.service.SecaoService;

@RestController
@CrossOrigin
@RequestMapping(value = "/secao")
public class SecaoController {

    private SecaoService service;

    public SecaoController(SecaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Secao> todas() {
        return service.todas();
    }

    @GetMapping(value = "/busca")
    public List<Secao> buscarPorTituloSecaoETituloTrabalho(@RequestParam("secao") String tituloSecao,
            @RequestParam("trabalho") String tituloTrabalho) {
        return service.buscarPorTituloSecaoETituloTrabalho(tituloSecao, tituloTrabalho);
    }

    @PostMapping
    public ResponseEntity<Secao> nova(@RequestBody Secao secao) {
        return ResponseEntity.created(URI.create("/secao")).body(service.nova(secao));
    }

}