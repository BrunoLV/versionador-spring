package com.porto.amazonas.versionamento.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Classe que representa a entidade Versao dentro da aplicacao.
 *
 * @author BRUNO VIANA
 */
@Entity
@Table(name = "tb_versao")
public class Versao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_versao")
    private Integer id;

    @Column(name = "nome_versao", length = 50, nullable = false)
    private String nome;

    @Column(name = "nome_branch", length = 50, nullable = false)
    private String nomeBranch;

    @Column(name = "descricao_versao", length = 255, nullable = false)
    private String descricao;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_criacao", nullable = false)
    private Date dataCriacao;

    @Column(name = "nome_autor", length = 50, nullable = false)
    private String nomeAutor;

    @Column(name = "flag_producao", nullable = false)
    private Boolean producao;

    @Column(name = "flag_head", nullable = false)
    private Boolean head;

    @ManyToOne
    @JoinColumn(name = "id_sistema", nullable = false)
    private Sistema sistema;

    public Versao() {
    }

    public Versao(String nomeBranch, String nome, String descricao,
                  Date dataCriacao, String nomeAutor, Boolean producao, Boolean head,
                  Sistema sistema) {
        this.nome = nome;
        this.nomeBranch = nomeBranch;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.nomeAutor = nomeAutor;
        this.producao = producao;
        this.head = head;
        this.sistema = sistema;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeBranch() {
        return this.nomeBranch;
    }

    public void setNomeBranch(String nomeBranch) {
        this.nomeBranch = nomeBranch;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getNomeAutor() {
        return this.nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public Boolean getProducao() {
        return this.producao;
    }

    public void setProducao(Boolean producao) {
        this.producao = producao;
    }

    public Boolean getHead() {
        return this.head;
    }

    public void setHead(Boolean head) {
        this.head = head;
    }

    public Sistema getSistema() {
        return this.sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    @Override
    public String toString() {
        return "Versao{id=" + this.id + ", nomeBranch='" + this.nomeBranch
                + '\'' + ", nomeVersao='" + this.nome + '\''
                + ", descricao='" + this.descricao + '\'' + ", dataCriacao="
                + this.dataCriacao + ", nomeAutor='" + this.nomeAutor + '\''
                + ", producao=" + this.producao + ", head=" + this.head
                + ", sistema=" + this.sistema + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ((o == null) || (getClass() != o.getClass()))
            return false;

        Versao that = (Versao) o;

        if (this.id != null ? !this.id.equals(that.id) : that.id != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return this.id != null ? this.id.hashCode() : 0;
    }

} // fim da classe Versao.
