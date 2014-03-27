package com.porto.amazonas.versionamento.dao;

import java.util.List;

/**
 * Interface base para todas as classes DAO da aplicação. Ela define os método
 * básicos CRUD para operações no banco de dados.
 * @author BRUNO VIANA
 * @param <T> tipo genérico que em tempo de execução assume o tipo mapeado da tabela no banco de dados.
 */
public interface BaseDao<T> {

	/**
	 * Método utilizado para salvar uma entidade no banco de dados da aplicação.
	 * @param entidade a ser salva no banco de dados.
	 */
	void salvar(T entidade);

	/**
	 * Método utilizado para editar uma entidade no banco de dados da aplicação.
	 * @param entidade a ser editada no banco de dados.
	 */
	void editar(T entidade);

	/**
	 * Método utilizado para deletar uma entidade no banco de dados da
	 * aplicação.
	 * @param entidade a ser deletado no banco da dados.
	 */
	void deletar(T entidade);

	/**
	 * Método utilizado para pesquisar uma entidade no banco de dados da
	 * aplicação utilizando seu id.
	 * @param id que será utilizado na consulta.
	 * @return Objeto resulta da consulta.
	 */
	T buscarPorId(Integer id);

	/**
	 * Método utilizado para listar todas entidades de determinada tabela.
	 * @return List contendo os registros retornados na consulta.
	 */
	List<T> listarTodos();

} // fim da interface BaseDao
