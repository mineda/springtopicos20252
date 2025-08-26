package br.gov.sp.fatec.springtopicos20252.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springtopicos20252.entity.Autorizacao;
import br.gov.sp.fatec.springtopicos20252.entity.Usuario;
import br.gov.sp.fatec.springtopicos20252.repository.AutorizacaoRepository;
import br.gov.sp.fatec.springtopicos20252.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private UsuarioRepository repo;

    private AutorizacaoRepository autRepo;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepo, AutorizacaoRepository autRepo) {
        this.repo = usuarioRepo;
        this.autRepo = autRepo;
    }

    @Override
    @Transactional
    public Usuario novo(Usuario usuario) {
        if(usuario == null ||
                usuario.getNome() == null ||
                usuario.getNome().isBlank() ||
                usuario.getSenha() == null ||
                usuario.getSenha().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Faltam informações!");
        }
        usuario.setId(null);
        Set<Autorizacao> autorizacoes = new HashSet<Autorizacao>();
        for(Autorizacao aut: usuario.getAutorizacoes()) {
            autorizacoes.add(buscarAutorizacaoPorId(aut.getId()));
        }
        usuario.setAutorizacoes(autorizacoes);
        return repo.save(usuario);
    }

    private Autorizacao buscarAutorizacaoPorId(Long id) {
        if(id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código de autorização nulo!");
        }
        return autRepo.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Autorização não existe!"); 
        });
    }

    @Override
    public List<Usuario> buscarTodos() {
        return repo.findAll();
    }

    @Override
    public Usuario buscarPeloId(Long id) {
        Optional<Usuario> usuarioOp = repo.findById(id);
        if(usuarioOp.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não existe!");
        }
        return usuarioOp.get();
    }
    
}
