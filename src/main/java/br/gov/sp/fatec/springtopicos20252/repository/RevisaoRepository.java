package br.gov.sp.fatec.springtopicos20252.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springtopicos20252.entity.Revisao;

public interface RevisaoRepository extends JpaRepository<Revisao, Long>{

    public List<Revisao> findBySecaoTituloContainsAndDataHoraCriacaoLessThan(String tituloSecao, LocalDateTime dataHoraCriacao);
    
}