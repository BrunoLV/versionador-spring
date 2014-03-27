package com.porto.amazonas.versionamento.service;

import java.util.Date;
import java.util.List;

import com.porto.amazonas.versionamento.exceptions.VersionamentoException;
import com.porto.amazonas.versionamento.model.Sistema;
import com.porto.amazonas.versionamento.model.Versao;

/**
 * Interface que define o comportamento da classe de negócio para operações com a entidade Versao.
 * @author BRUNO VIANA
 */
public interface VersaoService extends BaseService<Versao> {

	/**
	 * Método utilizado para listar todas as versões registradas na aplicação
	 * utilizando um sistema.
	 * @param sistema que será utilizado na consulta.
	 * @return List com todas as versões resultadas da consulta.
	 * @throws VersionamentoException para qualquer erro que ocorra durante a execução do método.
	 */
	List<Versao> listarTodasVersoesDeUmSistema(Sistema sistema) throws VersionamentoException;

	/**
	 * Método utilizado para listar todas as versÃµes registradas na aplicação
	 * utilizando um sistema e uma faixa de datas.
	 * @param sistema que será utilizado na consulta.
	 * @param dataInicial que será utilizada na consulta.
	 * @param dataFinal que será utilizada na consulta.
	 * @return List com todas as versÃµes resultadas da consulta.
	 * @throws VersionamentoException para qualquer erro que ocorra durante a execução do método.
	 */
	List<Versao> listarTodasVersoesDeUmSistemaPorPeriodo(Sistema sistema, Date dataInicial, Date dataFinal) throws VersionamentoException;
	
	void atualizarStatusProducaoDeVersaosDiferentesDeUmaEmEspecifico(Versao versao, Boolean status) throws VersionamentoException;

} // fim da intarface VersaoService