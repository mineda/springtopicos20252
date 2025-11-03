package br.gov.sp.fatec.springtopicos20252.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springtopicos20252.entity.Anotacao;

public interface AnotacaoRepository extends JpaRepository<Anotacao, Long>{

    public List<Anotacao> findByUsuarioNomeAndTextoContains(String nomeUsuario, String texto);
    
}
