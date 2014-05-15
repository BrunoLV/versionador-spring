package com.porto.amazonas.versionamento.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Classe que representa a entidade Sistema dentro da aplicacao.
 *
 * @author BRUNO VIANA
 */
@Entity
@Table(name = "tb_sistema")
public class Sistema implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_sistema")
    private Integer id;

    @Column(name = "nome_sistema", length = 70, unique = true, nullable = false)
    private String nome;

    @Column(name = "descricao_sistema", length = 255)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_sistema", length = 20, nullable = false)
    private EnumStatus status;

    public Sistema() {
    }

    public Sistema(String nome, EnumStatus status) {
        this.nome = nome;
        this.status = status;
    }

    public Sistema(Integer id, String nome, EnumStatus status) {
        this.id = id;
        this.nome = nome;
        this.status = status;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public EnumStatus getStatus() {
        return this.status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Sistema [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", status=" + status + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ((o == null) || (getClass() != o.getClass()))
            return false;

        Sistema sistema = (Sistema) o;

        if (this.id != null ? !this.id.equals(sistema.id) : sistema.id != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return this.id != null ? this.id.hashCode() : 0;
    }

} // fim da classe Sistema.