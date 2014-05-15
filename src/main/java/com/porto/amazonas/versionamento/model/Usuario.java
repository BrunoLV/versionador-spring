package com.porto.amazonas.versionamento.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * Classe que representa a entidade Usuario dentro da aplicacao.
 *
 * @author BRUNO VIANA
 */
@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    private Integer id;

    @Column(name = "nome_usuario", length = 50, nullable = false, unique = true)
    private String nome;

    @Column(name = "matricula_usuario", length = 10, nullable = false, unique = true)
    private String matricula;

    @Column(name = "senha_usuario", length = 100, nullable = false, unique = true)
    private String senha;

    @Column(name = "flag_ativo", columnDefinition = "BOOLEAN")
    private boolean ativo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_papeis_usuarios", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_papel"))
    private Set<Papel> papeis;

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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Set<Papel> getPapeis() {
        return papeis;
    }

    public void setPapeis(Set<Papel> papeis) {
        this.papeis = papeis;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nome=" + nome + ", matricula=" + matricula + ", senha=" + senha + ", ativo=" + ativo + ", papeis=" + papeis + "]";
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
        Usuario other = (Usuario) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.papeis;
    }

    @Override
    @Transient
    public String getPassword() {
        return this.senha;
    }

    @Override
    @Transient
    public String getUsername() {
        return this.matricula;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return this.ativo;
    }

    public boolean isConvidado() {
        for (Papel papel : papeis) {
            if (papel.getNomePapel().equals(EnumPapel.ROLE_CONVIDADO)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAnalistaManter() {
        for (Papel papel : papeis) {
            if (papel.getNomePapel().equals(EnumPapel.ROLE_MANTER)) {
                return true;
            }
        }
        return false;
    }

    public boolean isLider() {
        for (Papel papel : papeis) {
            if (papel.getNomePapel().equals(EnumPapel.ROLE_LIDER)) {
                return true;
            }
        }
        return false;
    }

} // fim da classe Usuario.
