package com.porto.amazonas.versionamento.service;

import com.porto.amazonas.versionamento.exceptions.VersionamentoException;
import com.porto.amazonas.versionamento.model.EnumPapel;
import com.porto.amazonas.versionamento.model.Usuario;

/**
 * Interface que define o comportamento da classe de neg�cio para opera��es com
 * a entidade Usuario.
 *
 * @author BRUNO VIANA
 */
public interface UsuarioService extends BaseService<Usuario> {

    void cadastrarUsuarioComDeterminadoPapel(Usuario usuario, EnumPapel papel) throws VersionamentoException;

    /**
     * M�todo utilizado para consultar um usuario na aplica��o utilizando a
     * matricula.
     *
     * @param matricula que ser� utilizada na consulta.
     * @return Usuario resultado da consulta.
     * @throws VersionamentoException para qualquer erro que ocorra durante a execu��o do m�todo.
     */
    Usuario buscarUsuarioPorMatricula(String matricula) throws VersionamentoException;

} // fim da interface UsuarioService