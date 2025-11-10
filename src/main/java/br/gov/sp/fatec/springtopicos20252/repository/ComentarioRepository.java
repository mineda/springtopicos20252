package br.gov.sp.fatec.springtopicos20252.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springtopicos20252.entity.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

    public List<Comentario> findByTrabalhoTituloContainsAndUrgenciaLessThan(String tituloTrabalho, Integer urgencia);
    
}
