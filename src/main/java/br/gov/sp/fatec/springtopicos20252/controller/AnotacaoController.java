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

import br.gov.sp.fatec.springtopicos20252.entity.Anotacao;
import br.gov.sp.fatec.springtopicos20252.service.AnotacaoService;

@RestController
@CrossOrigin
@RequestMapping(value = "/anotacao")
public class AnotacaoController {

    private AnotacaoService service;

    public AnotacaoController(AnotacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Anotacao> nova(@RequestBody Anotacao nova) {
        nova = service.nova(nova);
        return ResponseEntity.created(URI.create("/anotacao/" + nova.getId())).body(nova);
    }

    @GetMapping
    public List<Anotacao> todas() {
        return service.todas();
    }

    @GetMapping(value = "/{id}")
    public Anotacao buscarPorId(@PathVariable("id") Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping(value = "/busca")
    public List<Anotacao> buscarPorNomeUsuarioETexto(@RequestParam("usuario") String nomeUsuario, @RequestParam("texto") String texto) {
        return service.buscarPorNomeUsuarioETexto(nomeUsuario, texto);
    }
    
}
