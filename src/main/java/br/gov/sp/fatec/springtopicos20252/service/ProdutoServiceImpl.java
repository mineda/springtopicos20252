package br.gov.sp.fatec.springtopicos20252.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springtopicos20252.entity.Produto;
import br.gov.sp.fatec.springtopicos20252.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private ProdutoRepository repo;

    private EstoqueService estoqueService;

    public ProdutoServiceImpl(ProdutoRepository repo, EstoqueService estoqueService) {
        this.repo = repo;
        this.estoqueService = estoqueService;
    }
    
    @Override
    public List<Produto> todos() {
        return repo.findAll();
    }

    @Override
    public Produto novo(Produto produto) {
        if(produto == null ||
                produto.getDescricao() == null ||
                produto.getDescricao().isBlank() ||
                produto.getEstoque() == null ||
                produto.getEstoque().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados inválidos!");
        }
        if(produto.getDataHora() == null) {
            produto.setDataHora(LocalDateTime.now());
        }
        if(produto.getContador() == null) {
            produto.setContador(1);
        }
        produto.setId(null);
        produto.setEstoque(estoqueService.buscarPorId(produto.getEstoque().getId()));
        return repo.save(produto);
    }

    @Override
    public Produto buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!");
        });
    }

    @Override
    @Transactional
    public void incrementarProduto(String nomeEstoque, String descricao) {
        repo.incrementarProduto(nomeEstoque, descricao);
    }
    
}
