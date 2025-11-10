package br.gov.sp.fatec.springtopicos20252.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springtopicos20252.entity.Secao;
import br.gov.sp.fatec.springtopicos20252.repository.SecaoRepository;

@Service
public class SecaoServiceImpl implements SecaoService{

    private SecaoRepository repo;

    private TrabalhoService trabService;

    public SecaoServiceImpl(SecaoRepository repo, TrabalhoService trabService) {
        this.repo = repo;
        this.trabService = trabService;
    }

    @Override
    public Secao nova(Secao secao) {
        if(secao == null ||
                secao.getTitulo() == null ||
                secao.getTitulo().isBlank() ||
                secao.getConteudo() == null ||
                secao.getConteudo().isBlank() ||
                secao.getTrabalho() == null ||
                secao.getTrabalho().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Campos obrigatórios não preenchidos!");
        }
        if(secao.getDataHoraCriacao() == null) {
            secao.setDataHoraCriacao(LocalDateTime.now());
        }
        secao.setTrabalho(trabService.buscarPorId(secao.getTrabalho().getId()));
        return repo.save(secao);
    }

    @Override
    public List<Secao> todas() {
        return repo.findAll();
    }

    @Override
    public List<Secao> buscarPorTituloSecaoETituloTrabalho(String tituloSecao, String tituloTrabalho) {
        return repo.findByTituloContainsAndTrabalhoTituloContains(tituloSecao, tituloTrabalho);
    }

    @Override
    public Secao buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Seção não encontrada!");
        });  
    }
    
}