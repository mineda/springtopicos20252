package br.gov.sp.fatec.springtopicos20252.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springtopicos20252.entity.Comentario;
import br.gov.sp.fatec.springtopicos20252.repository.ComentarioRepository;

@Service
public class ComentarioServiceImpl implements ComentarioService{

    private ComentarioRepository repo;

    private TrabalhoService trabService;

    public ComentarioServiceImpl(ComentarioRepository repo, TrabalhoService trabService) {
        this.repo = repo;
        this.trabService = trabService;
    }

    @Override
    public Comentario novo(Comentario comentario) {
        if(comentario == null ||
                comentario.getConteudo() == null ||
                comentario.getConteudo().isBlank() ||
                comentario.getUrgencia() == null ||
                comentario.getUrgencia() <= 0 ||
                comentario.getUrgencia() > 3 ||
                comentario.getTrabalho() == null ||
                comentario.getTrabalho().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados obrigatórios não informados!");
        }
        if(comentario.getDataHoraCriacao() == null) {
            comentario.setDataHoraCriacao(LocalDateTime.now());
        }
        comentario.setTrabalho(trabService.buscarPorId(comentario.getTrabalho().getId()));
        return repo.save(comentario);
    }

    @Override
    public List<Comentario> todos() {
        return repo.findAll();
    }

    @Override
    public List<Comentario> buscarPorTituloTrabalhoEUrgencia(String tituloTrabalho, Integer urgencia) {
        return repo.findByTrabalhoTituloContainsAndUrgenciaLessThan(tituloTrabalho, urgencia);
    }

    @Override
    public Comentario buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comentário não encontrado!");
        });
    }
    
}