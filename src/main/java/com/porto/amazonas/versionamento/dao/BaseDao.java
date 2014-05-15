package com.porto.amazonas.versionamento.dao;

import java.util.List;

/**
 * Interface base para todas as classes DAO da aplica��o. Ela define os m�todo
 * b�sicos CRUD para opera��es no banco de dados.
 *
 * @param <T> tipo gen�rico que em tempo de execu��o assume o tipo mapeado da tabela no banco de dados.
 * @author BRUNO VIANA
 */
public interface BaseDao<T> {

    /**
     * M�todo utilizado para salvar uma entidade no banco de dados da aplica��o.
     *
     * @param entidade a ser salva no banco de dados.
     */
    void salvar(T entidade);

    /**
     * M�todo utilizado para editar uma entidade no banco de dados da aplica��o.
     *
     * @param entidade a ser editada no banco de dados.
     */
    void editar(T entidade);

    /**
     * M�todo utilizado para deletar uma entidade no banco de dados da
     * aplica��o.
     *
     * @param entidade a ser deletado no banco da dados.
     */
    void deletar(T entidade);

    /**
     * M�todo utilizado para pesquisar uma entidade no banco de dados da
     * aplica��o utilizando seu id.
     *
     * @param id que ser� utilizado na consulta.
     * @return Objeto resulta da consulta.
     */
    T buscarPorId(Integer id);

    /**
     * M�todo utilizado para listar todas entidades de determinada tabela.
     *
     * @return List contendo os registros retornados na consulta.
     */
    List<T> listarTodos();

} // fim da interface BaseDao
