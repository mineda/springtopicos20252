package br.gov.sp.fatec.springtopicos20252.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springtopicos20252.entity.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    public Optional<Estoque> findByNome(String nome);

    public List<Estoque> findByUsuarioNomeAndDataHoraGreaterThan(String nomeUsuario, LocalDateTime dataHora);
    
}
