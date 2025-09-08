package br.gov.sp.fatec.springtopicos20252.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springtopicos20252.entity.Trabalho;
import br.gov.sp.fatec.springtopicos20252.repository.TrabalhoRepository;

@Service
public class TrabalhoServiceImpl implements TrabalhoService{

    @Autowired
    private TrabalhoRepository trabalhoRepo;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public List<Trabalho> buscarTodos() {
        return trabalhoRepo.findAll();
    }

    @Override
    public Trabalho novoTrabalho(Trabalho trabalho) {
        if(trabalho == null ||
                trabalho.getTitulo() == null ||
                trabalho.getTitulo().isBlank() ||
                trabalho.getUsuario() == null ||
                trabalho.getUsuario().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Título e usuário são obrigatórios!");
        }
        if(trabalho.getDataHoraEntrega() == null) {
            trabalho.setDataHoraEntrega(LocalDateTime.now());
        }
        trabalho.setUsuario(usuarioService.buscarPeloId(trabalho.getUsuario().getId()));
        return trabalhoRepo.save(trabalho);
    }

    @Override
    public List<Trabalho> buscarPorTituloENomeUsuario(String titulo, String nomeUsuario) {
        return trabalhoRepo.buscarPorTituloENomeUsuario(titulo, nomeUsuario);
    }
    
}
