package com.porto.amazonas.versionamento.dao;

import com.porto.amazonas.versionamento.model.Dependencia;
import com.porto.amazonas.versionamento.model.Sistema;

import java.util.List;

/**
 * Interface que determina o comportamento do DAO que trata das operações no
 * banco de dados para a entidade Dependencia.
 *
 * @author BRUNO VIANA
 */
public interface DependenciaDao extends BaseDao<Dependencia> {

    /**
     * M�todo utilizado para consultar todas as dependencias utilizando um sistema como paramentro de consulta.
     *
     * @param sistema que ser� utilizado como parametro.
     * @return lista com as dependencias encontradas.
     */
    List<Dependencia> listarDependenciasDeUmSistema(Sistema sistema);

    /**
     * M�todo utilizado para consultar uma dependencia trazendo seus seus relacionamentos.
     *
     * @param dependencia que ser� consultada.
     * @return dependencia resultante da consulta.
     */
    Dependencia buscarDependenciaComSistemasRelacionados(Dependencia dependencia);

} // fim da interface DependenciaDao