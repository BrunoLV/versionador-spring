package com.porto.amazonas.versionamento.service;

import java.util.List;

import com.porto.amazonas.versionamento.exceptions.VersionamentoException;

/**
 * Interface base para os classes Service da aplica��o. Ela define as opera��es
 * CRUD que todas as classes service da aplica��o possuem. 
 * @author BRUNO VIANA
 * @param <T>
 */
public interface BaseService<T> {

	/**
	 * M�todo definido para a a��o de gravar um objeto na aplica��o.
	 * @param t objeto que ser� gravado na aplicação.
	 * @throws VersionamentoException exce��o que encapsula todas as outras exceptions lan�adas na execu��o do m�todo.
	 */
	void salvar(T t) throws VersionamentoException;

	/**
	 * M�todo definido para a a��o de editar um objeto na aplica��o.
	 * @param t objeto que ser� gravado na aplica��o.
	 * @throws VersionamentoException exce��o que encapsula todas as outras exceptions lan�adas na execu��o do m�todo.
	 */
	void editar(T t) throws VersionamentoException;

	/**
	 * Método definido para a ação de deletar um objeto na aplicação.
	 * @param t objeto que será gravado na aplicação.
	 * @throws VersionamentoException exceção que encapsula todas as outras exceptions lançadas na execução do método.
	 */
	void deletar(T t) throws VersionamentoException;

	/**
	 * M�todo definido para a a��o de buscar um objeto na aplica��o utilizando
	 * seu id.
	 * @param id do objeto a ser consultado na aplica��o.
	 * @return objeto resultado da pesquisa.
	 * @throws VersionamentoException exce��o que encapsula todas as outras exceptions lan�adas na execu��o do m�todo.
	 */
	T buscarPorId(Integer id) throws VersionamentoException;

	/**
	 * M�todo definido para a a��o de buscar uma lista com todos os objetos
	 * gravados na aplica��o.
	 * @return lista com todos os resultados da consulta.
	 * @throws VersionamentoException exce��o que encapsula todas as outras exceptions lan�adas na execu��o do m�todo.
	 */
	List<T> listarTodos() throws VersionamentoException;

} // fim da interface BaseService
