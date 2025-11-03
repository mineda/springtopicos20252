package br.gov.sp.fatec.springtopicos20252.service;

import java.util.List;

import br.gov.sp.fatec.springtopicos20252.entity.Anotacao;

public interface AnotacaoService {

    public Anotacao nova(Anotacao anotacao);

    public List<Anotacao> todas();

    public Anotacao buscarPorId(Long id);

    public List<Anotacao> buscarPorNomeUsuarioETexto(String nomeUsuario, String texto);
    
}
