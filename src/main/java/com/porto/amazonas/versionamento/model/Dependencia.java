package com.porto.amazonas.versionamento.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Classe que representa a entidade Dependencia dentro da aplicacao.
 *
 * @author BRUNO VIANA
 */
@Entity
@Table(name = "tb_dependencia")
public class Dependencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_dependencia")
    private Integer id;

    @Column(name = "nome_dependencia", length = 100, unique = true, nullable = false)
    private String nome;

    @ManyToMany
    @JoinTable(name = "tb_dependencia_sistema", joinColumns = @JoinColumn(name = "id_dependencia"), inverseJoinColumns = @JoinColumn(name = "id_sistema"))
    private List<Sistema> sistemas;

    public Dependencia() {
    }

    public Dependencia(String nome) {
        super();
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Sistema> getSistemas() {
        return sistemas;
    }

    public void setSistemas(List<Sistema> sistemas) {
        this.sistemas = sistemas;
    }

    @Override
    public String toString() {
        return "Dependencia [id=" + id + ", nome=" + nome + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Dependencia other = (Dependencia) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

} // fim da classe Dependencia