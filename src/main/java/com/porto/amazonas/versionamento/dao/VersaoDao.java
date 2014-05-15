package com.porto.amazonas.versionamento.dao;

import com.porto.amazonas.versionamento.model.Sistema;
import com.porto.amazonas.versionamento.model.Versao;

import java.util.Date;
import java.util.List;

/**
 * Interface que determina o comportamento do DAO que trata das opera��es no
 * banco de dados para a entidade Versao.
 *
 * @author BRUNO VIANA
 */
public interface VersaoDao extends BaseDao<Versao> {

    /**
     * M�todo utilizado para listar todas as vers�es cadastradas no banco de
     * dados utilizando um sistema como parametro de consulta.
     *
     * @param sistema que ser� utilizado como parametro na consulta.
     * @return List com todas as vers�es retornadas na consulta.
     */
    List<Versao> listarTodasVersoesDeUmSistema(Sistema sistema);

    /**
     * M�todo utilizado para listar todas as vers�es cadastradas no banco de
     * dados utiliza um sistema e uma faixa de datas como parametro de consulta.
     *
     * @param sistema    que ser� utilizado como parametro na consulta.
     * @param dataInicio que ser� utilizada como inicio da faixa de tempo da consulta.
     * @param dataFim    que ser� utilizada ocmo final da faixa de tempo da consulta.
     * @return List com todas as vers�es retornadas na consulta.
     */
    List<Versao> listarTodasVersoesDeUmSistemaPorPeriodo(Sistema sistema, Date dataInicio, Date dataFim);

    /**
     * M�todo utilizado para atualizar o Status de Vers�es no banco de dados diferentes da Vers�o passada como parametro.
     * O sistema ao qual a vers�o esta vinculada � levado em considera��o na query.
     *
     * @param versao que ser� utilizada como compara��o.
     * @param status que ser� atribuido para os registros.
     */
    void atualizarStatusProducaoDeVersaosDiferentesDeUmaEmEspecifico(Versao versao, Boolean status);

} // fim da interface VersaoDao 