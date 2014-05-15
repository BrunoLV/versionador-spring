/**
 *
 */
package com.porto.amazonas.versionamento.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Classe que representa a entidade Papel dentro da aplicacao.
 *
 * @author BRUNO VIANA
 */
@Entity
@Table(name = "tb_papeis")
public class Papel implements Serializable, GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_papel")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "nome_papel", length = 30, nullable = false, unique = true)
    private EnumPapel nomePapel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EnumPapel getNomePapel() {
        return nomePapel;
    }

    public void setNomePapel(EnumPapel nomePapel) {
        this.nomePapel = nomePapel;
    }

    @Override
    public String toString() {
        return "Papel [id=" + id + ", nomePapel=" + nomePapel + "]";
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
        Papel other = (Papel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    @Transient
    public String getAuthority() {
        return nomePapel.name();
    }

} // fim da classe Papel
