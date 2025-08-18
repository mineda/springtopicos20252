package br.gov.sp.fatec.springtopicos20252.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springtopicos20252.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
