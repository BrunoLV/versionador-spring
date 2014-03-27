package com.porto.amazonas.versionamento.service;

import java.util.Date;
import java.util.List;

import com.porto.amazonas.versionamento.exceptions.VersionamentoException;
import com.porto.amazonas.versionamento.model.Sistema;
import com.porto.amazonas.versionamento.model.Versao;

/**
 * Interface que define o comportamento da classe de neg�cio para opera��es com a entidade Versao.
 * @author BRUNO VIANA
 */
public interface VersaoService extends BaseService<Versao> {

	/**
	 * M�todo utilizado para listar todas as vers�es registradas na aplica��o
	 * utilizando um sistema.
	 * @param sistema que ser� utilizado na consulta.
	 * @return List com todas as vers�es resultadas da consulta.
	 * @throws VersionamentoException para qualquer erro que ocorra durante a execu��o do m�todo.
	 */
	List<Versao> listarTodasVersoesDeUmSistema(Sistema sistema) throws VersionamentoException;

	/**
	 * M�todo utilizado para listar todas as versões registradas na aplica��o
	 * utilizando um sistema e uma faixa de datas.
	 * @param sistema que ser� utilizado na consulta.
	 * @param dataInicial que ser� utilizada na consulta.
	 * @param dataFinal que ser� utilizada na consulta.
	 * @return List com todas as versões resultadas da consulta.
	 * @throws VersionamentoException para qualquer erro que ocorra durante a execu��o do m�todo.
	 */
	List<Versao> listarTodasVersoesDeUmSistemaPorPeriodo(Sistema sistema, Date dataInicial, Date dataFinal) throws VersionamentoException;
	
	void atualizarStatusProducaoDeVersaosDiferentesDeUmaEmEspecifico(Versao versao, Boolean status) throws VersionamentoException;

} // fim da intarface VersaoService