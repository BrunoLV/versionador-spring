package com.porto.amazonas.versionamento.service;

import com.porto.amazonas.versionamento.exceptions.VersionamentoException;

import java.util.List;

/**
 * Interface base para os classes Service da aplicacao. Ela define as operacoes
 * CRUD que todas as classes service da aplicacao possuem.
 *
 * @param <T>
 * @author BRUNO VIANA
 */
public interface BaseService<T> {

    /**
     * Metodo definido para a acao de gravar um objeto na aplicacao.
     *
     * @param t objeto que sera gravado na aplicação.
     * @throws VersionamentoException excecao que encapsula todas as outras exceptions lancadas na execucao do Metodo.
     */
    void salvar(T t) throws VersionamentoException;

    /**
     * Metodo definido para a acao de editar um objeto na aplicacao.
     *
     * @param t objeto que sera gravado na aplicacao.
     * @throws VersionamentoException excecao que encapsula todas as outras exceptions lancadas na execucao do Metodo.
     */
    void editar(T t) throws VersionamentoException;

    /**
     * Método definido para a ação de deletar um objeto na aplicação.
     *
     * @param t objeto que será gravado na aplicação.
     * @throws VersionamentoException exceção que encapsula todas as outras exceptions lançadas na execução do método.
     */
    void deletar(T t) throws VersionamentoException;

    /**
     * Metodo definido para a acao de buscar um objeto na aplicacao utilizando
     * seu id.
     *
     * @param id do objeto a ser consultado na aplicacao.
     * @return objeto resultado da pesquisa.
     * @throws VersionamentoException excecao que encapsula todas as outras exceptions lancadas na execucao do Metodo.
     */
    T buscarPorId(Integer id) throws VersionamentoException;

    /**
     * Metodo definido para a acao de buscar uma lista com todos os objetos
     * gravados na aplicacao.
     *
     * @return lista com todos os resultados da consulta.
     * @throws VersionamentoException excecao que encapsula todas as outras exceptions lancadas na execucao do Metodo.
     */
    List<T> listarTodos() throws VersionamentoException;

} // fim da interface BaseService
