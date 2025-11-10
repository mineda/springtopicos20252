package br.gov.sp.fatec.springtopicos20252.service;

import java.util.List;

import br.gov.sp.fatec.springtopicos20252.entity.Comentario;

public interface ComentarioService {

    public Comentario novo(Comentario comentario);

    public List<Comentario> todos();

    public List<Comentario> buscarPorTituloTrabalhoEUrgencia(String tituloTrabalho, Integer urgencia);

    public Comentario buscarPorId(Long id);
    
}