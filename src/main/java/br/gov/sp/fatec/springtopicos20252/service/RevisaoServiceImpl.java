package br.gov.sp.fatec.springtopicos20252.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springtopicos20252.entity.Revisao;
import br.gov.sp.fatec.springtopicos20252.repository.RevisaoRepository;

@Service
public class RevisaoServiceImpl implements RevisaoService {

    private RevisaoRepository repo;

    private SecaoService secaoService;

    public RevisaoServiceImpl(RevisaoRepository repo, SecaoService secaoService) {
        this.repo = repo;
        this.secaoService = secaoService;
    }

    @Override
    public Revisao nova(Revisao revisao) {
        if(revisao == null ||
                revisao.getFeedback() == null ||
                revisao.getFeedback().isBlank() ||
                revisao.getSecao() == null ||
                revisao.getSecao().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Feedback e Seção são obrigatórios!");
        }
        if(revisao.getDataHoraCriacao() != null &&
                revisao.getDataHoraCorrecao() != null &&
                revisao.getDataHoraCriacao().isAfter(revisao.getDataHoraCorrecao())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data/hora inválida!");
        }
        if(revisao.getDataHoraCriacao() == null) {
            revisao.setDataHoraCriacao(LocalDateTime.now());
        }
        revisao.setSecao(secaoService.buscarPorId(revisao.getSecao().getId()));
        return repo.save(revisao);
    }

    @Override
    public List<Revisao> todas() {
        return repo.findAll();
    }

    @Override
    public List<Revisao> buscarPorTituloSecaoEDataHoraCriacao(String tituloSecao, LocalDateTime dataHoraCriacao) {
        return repo.findBySecaoTituloContainsAndDataHoraCriacaoLessThan(tituloSecao, dataHoraCriacao);
    }

    @Override
    public Revisao buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Revisão não encontrada!");
        });
    }
    
}