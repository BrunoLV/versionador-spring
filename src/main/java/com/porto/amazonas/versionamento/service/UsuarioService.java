package com.porto.amazonas.versionamento.service;

import com.porto.amazonas.versionamento.exceptions.VersionamentoException;
import com.porto.amazonas.versionamento.model.EnumPapel;
import com.porto.amazonas.versionamento.model.Usuario;

/**
 * Interface que define o comportamento da classe de negócio para operações com
 * a entidade Usuario.
 * @author BRUNO VIANA
 */
public interface UsuarioService extends BaseService<Usuario> {
	
	void cadastrarUsuarioComDeterminadoPapel(Usuario usuario, EnumPapel papel) throws VersionamentoException;

	/**
	 * Método utilizado para consultar um usuario na aplicação utilizando a
	 * matricula.
	 * @param matricula que será utilizada na consulta.
	 * @return Usuario resultado da consulta.
	 * @throws VersionamentoException para qualquer erro que ocorra durante a execução do método.
	 */
	Usuario buscarUsuarioPorMatricula(String matricula) throws VersionamentoException;

} // fim da interface UsuarioService