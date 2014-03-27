package com.porto.amazonas.versionamento.service;

import java.util.List;

import com.porto.amazonas.versionamento.exceptions.VersionamentoException;

/**
 * Interface base para os classes Service da aplicação. Ela define as operações
 * CRUD que todas as classes service da aplicação possuem. 
 * @author BRUNO VIANA
 * @param <T>
 */
public interface BaseService<T> {

	/**
	 * Método definido para a ação de gravar um objeto na aplicação.
	 * @param t objeto que será gravado na aplicaÃ§Ã£o.
	 * @throws VersionamentoException exceção que encapsula todas as outras exceptions lançadas na execução do método.
	 */
	void salvar(T t) throws VersionamentoException;

	/**
	 * Método definido para a ação de editar um objeto na aplicação.
	 * @param t objeto que será gravado na aplicação.
	 * @throws VersionamentoException exceção que encapsula todas as outras exceptions lançadas na execução do método.
	 */
	void editar(T t) throws VersionamentoException;

	/**
	 * MÃ©todo definido para a aÃ§Ã£o de deletar um objeto na aplicaÃ§Ã£o.
	 * @param t objeto que serÃ¡ gravado na aplicaÃ§Ã£o.
	 * @throws VersionamentoException exceÃ§Ã£o que encapsula todas as outras exceptions lanÃ§adas na execuÃ§Ã£o do mÃ©todo.
	 */
	void deletar(T t) throws VersionamentoException;

	/**
	 * Método definido para a ação de buscar um objeto na aplicação utilizando
	 * seu id.
	 * @param id do objeto a ser consultado na aplicação.
	 * @return objeto resultado da pesquisa.
	 * @throws VersionamentoException exceção que encapsula todas as outras exceptions lançadas na execução do método.
	 */
	T buscarPorId(Integer id) throws VersionamentoException;

	/**
	 * Método definido para a ação de buscar uma lista com todos os objetos
	 * gravados na aplicação.
	 * @return lista com todos os resultados da consulta.
	 * @throws VersionamentoException exceção que encapsula todas as outras exceptions lançadas na execução do método.
	 */
	List<T> listarTodos() throws VersionamentoException;

} // fim da interface BaseService
