package br.gov.sp.fatec.springtopicos20252.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springtopicos20252.entity.Estoque;
import br.gov.sp.fatec.springtopicos20252.repository.EstoqueRepository;

@Service
public class EstoqueServiceImpl implements EstoqueService {

    private EstoqueRepository repo;

    private UsuarioService usuarioService;

    public EstoqueServiceImpl(EstoqueRepository repo, UsuarioService usuarioService) {
        this.repo = repo;
        this.usuarioService = usuarioService;
    }

    @Override
    public List<Estoque> todos() {
        return repo.findAll();
    }

    @Override
    public Estoque novo(Estoque estoque) {
        if(estoque == null ||
                estoque.getNome() == null ||
                estoque.getNome().isBlank() ||
                estoque.getUsuario() == null ||
                estoque.getUsuario().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados inválidos!");
        }
        if(estoque.getDataHora() == null) {
            estoque.setDataHora(LocalDateTime.now());
        }
        estoque.setId(null);
        estoque.setUsuario(usuarioService.buscarPeloId(estoque.getUsuario().getId()));
        return repo.save(estoque);
    }

    @Override
    public Estoque buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Estoque não encontrado!");
        });
    }

    @Override
    public Estoque buscarPorNome(String nome) {
        return repo.findByNome(nome).orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Estoque não encontrado!");
        });
    }

    @Override
    public List<Estoque> buscarPorNomeUsuarioEDataHoraDesde(String nomeUsuario, LocalDateTime dataHora) {
        return repo.findByUsuarioNomeAndDataHoraGreaterThan(nomeUsuario, dataHora);
    }
    
}
