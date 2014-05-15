package com.porto.amazonas.versionamento.service;

import com.porto.amazonas.versionamento.exceptions.VersionamentoException;
import com.porto.amazonas.versionamento.model.Sistema;
import com.porto.amazonas.versionamento.model.Versao;

import java.util.Date;
import java.util.List;

/**
 * Interface que define o comportamento da classe de negocio para operacoes com a entidade Versao.
 *
 * @author BRUNO VIANA
 */
public interface VersaoService extends BaseService<Versao> {

    /**
     * Metodo utilizado para listar todas as versoes registradas na aplicacao
     * utilizando um sistema.
     *
     * @param sistema que sera utilizado na consulta.
     * @return List com todas as versoes resultadas da consulta.
     * @throws VersionamentoException para qualquer erro que ocorra durante a execucao do Metodo.
     */
    List<Versao> listarTodasVersoesDeUmSistema(Sistema sistema) throws VersionamentoException;

    /**
     * Metodo utilizado para listar todas as versões registradas na aplicacao
     * utilizando um sistema e uma faixa de datas.
     *
     * @param sistema     que sera utilizado na consulta.
     * @param dataInicial que sera utilizada na consulta.
     * @param dataFinal   que sera utilizada na consulta.
     * @return List com todas as versões resultadas da consulta.
     * @throws VersionamentoException para qualquer erro que ocorra durante a execucao do Metodo.
     */
    List<Versao> listarTodasVersoesDeUmSistemaPorPeriodo(Sistema sistema, Date dataInicial, Date dataFinal) throws VersionamentoException;

    /**
     * Metodo utilizado para atualizar o Status de producao de uma Versao em especifico.
     *
     * @param versao que sera atualizada.
     * @param status que sera imposto.
     * @throws VersionamentoException
     */
    void atualizarStatusProducaoDeVersaosDiferentesDeUmaEmEspecifico(Versao versao, Boolean status) throws VersionamentoException;

} // fim da intarface VersaoService