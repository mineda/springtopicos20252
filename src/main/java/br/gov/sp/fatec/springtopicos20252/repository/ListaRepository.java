package br.gov.sp.fatec.springtopicos20252.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springtopicos20252.entity.Lista;

public interface ListaRepository extends JpaRepository<Lista, Long> {

    public List<Lista> findByAnotacaoTextoContainsAndDescricaoContains(String textoAnotacao, String descricao);
    
}
