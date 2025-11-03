package br.gov.sp.fatec.springtopicos20252.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springtopicos20252.entity.Anotacao;
import br.gov.sp.fatec.springtopicos20252.repository.AnotacaoRepository;

@Service
public class AnotacaoServiceImpl implements AnotacaoService{

    private AnotacaoRepository repo;

    private UsuarioService usuarioService;

    public AnotacaoServiceImpl(AnotacaoRepository repo, UsuarioService usuarioService) {
        this.repo = repo;
        this.usuarioService = usuarioService;
    }

    @Override
    public Anotacao nova(Anotacao anotacao) {
        if(anotacao == null ||
                anotacao.getTexto() == null ||
                anotacao.getTexto().isBlank() ||
                anotacao.getUsuario() == null ||
                anotacao.getUsuario().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Texto e usuário precisam ser preenchidos!");
        }
        anotacao.setId(null);
        if(anotacao.getDataHora() == null) {
            anotacao.setDataHora(LocalDateTime.now());
        }
        anotacao.setUsuario(usuarioService.buscarPeloId(anotacao.getUsuario().getId()));
        return repo.save(anotacao);
    }

    @Override
    public List<Anotacao> todas() {
        return repo.findAll();
    }

    @Override
    public Anotacao buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Anotação não encontrada!");
        });
    }

    @Override
    public List<Anotacao> buscarPorNomeUsuarioETexto(String nomeUsuario, String texto) {
        return repo.findByUsuarioNomeAndTextoContains(nomeUsuario, texto);
    }
    
}
