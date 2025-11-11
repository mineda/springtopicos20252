package br.gov.sp.fatec.springtopicos20252.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springtopicos20252.entity.Lista;
import br.gov.sp.fatec.springtopicos20252.repository.ListaRepository;

@Service
public class ListaServiceImpl implements ListaService {

    private ListaRepository repo;

    private AnotacaoService anotacaoService;

    public ListaServiceImpl(ListaRepository repo, AnotacaoService anotacaoService) {
        this.repo = repo;
        this.anotacaoService = anotacaoService;
    }

    @Override
    public Lista nova(Lista lista) {
        if(lista == null ||
                lista.getDescricao() == null ||
                lista.getDescricao().isBlank() ||
                lista.getAnotacao() == null ||
                lista.getAnotacao().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados inválidos!");
        }
        lista.setId(null); //garante que é um novo registro
        lista.setAnotacao(anotacaoService.buscarPorId(lista.getAnotacao().getId()));
        return repo.save(lista);
    }

    @Override
    public List<Lista> todas() {
        return repo.findAll();
    }

    @Override
    public Lista buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista não encontrada!");
        });
    }

    @Override
    public List<Lista> buscarPorTextoAnotacaoEDescricao(String textoAnotacao, String descricao) {
        return repo.findByAnotacaoTextoContainsAndDescricaoContains(textoAnotacao, descricao);
    }
}
