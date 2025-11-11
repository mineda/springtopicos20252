package br.gov.sp.fatec.springtopicos20252.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lst_lista")
public class Lista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lst_id")
    private Long id;

    @Column(name = "lst_descricao")
    private String descricao;

    @Column(name = "lst_data_hora_verificacao")
    private LocalDateTime dataHoraVerificacao;

    @Column(name = "lst_comentario_verificacao")
    private String comentarioVerificacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lst_ant_id")
    private Anotacao anotacao;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHoraVerificacao() {
        return dataHoraVerificacao;
    }

    public void setDataHoraVerificacao(LocalDateTime dataHoraVerificacao) {
        this.dataHoraVerificacao = dataHoraVerificacao;
    }

    public String getComentarioVerificacao() {
        return comentarioVerificacao;
    }

    public void setComentarioVerificacao(String comentarioVerificacao) {
        this.comentarioVerificacao = comentarioVerificacao;
    }

    public Anotacao getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(Anotacao anotacao) {
        this.anotacao = anotacao;
    }
}
