package com.porto.amazonas.versionamento.dao;

import com.porto.amazonas.versionamento.model.Usuario;

/**
 * Interface que determina o comportamento do DAO que trata das opera��es no
 * banco de dados para a entidade Usuario.
 *
 * @author BRUNO VIANA
 */
public interface UsuarioDao extends BaseDao<Usuario> {

    /**
     * M�todo utilizado para pesquisar um Usuario no banco de dados utilizando a
     * matricula como parametro de consulta.
     *
     * @param matricula que ser� utilizada como parametro de consulta.
     * @return Usuario retornado na consulta.
     */
    Usuario buscarUsuarioPorMatricula(final String matricula);

    /**
     * M�todo utilizado para pesquisar um Usuario no banco de dados utilizando a
     * matricula e senha como parametro de consulta.
     *
     * @param matricula que ser� utilizado como parametro de consulta.
     * @param senha     que ser� utilizada como paramentro de consulta.
     * @return Usuario retornado na consulta.
     */
    Usuario buscarUsuarioPorMatriculaESenha(final String matricula, final String senha);

} // fim da interface UsuarioDao
