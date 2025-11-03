package br.gov.sp.fatec.springtopicos20252.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class MainController {

    @GetMapping
    public String main() {
        return "Página principal! Sem nada aqui!";
    }
    
}
