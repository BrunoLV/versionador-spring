package com.porto.amazonas.versionamento.dao;

import java.util.Date;
import java.util.List;

import com.porto.amazonas.versionamento.model.Sistema;
import com.porto.amazonas.versionamento.model.Versao;

/**
 * Interface que determina o comportamento do DAO que trata das operações no
 * banco de dados para a entidade Versao.
 * @author BRUNO VIANA
 */
public interface VersaoDao extends BaseDao<Versao> {

	/**
	 * Método utilizado para listar todas as versões cadastradas no banco de
	 * dados utilizando um sistema como parametro de consulta.
	 * @param sistema que será utilizado como parametro na consulta.
	 * @return List com todas as versões retornadas na consulta.
	 */
	List<Versao> listarTodasVersoesDeUmSistema(Sistema sistema);

	/**
	 * Método utilizado para listar todas as versões cadastradas no banco de
	 * dados utiliza um sistema e uma faixa de datas como parametro de consulta.
	 * @param sistema que será utilizado como parametro na consulta.
	 * @param dataInicio que será utilizada como inicio da faixa de tempo da consulta.
	 * @param dataFim que será utilizada ocmo final da faixa de tempo da consulta.
	 * @return List com todas as versões retornadas na consulta.
	 */
	List<Versao> listarTodasVersoesDeUmSistemaPorPeriodo(Sistema sistema, Date dataInicio, Date dataFim);
	
	/**
	 * Método utilizado para atualizar o Status de Versões no banco de dados diferentes da Versão passada como parametro.
	 * O sistema ao qual a versão esta vinculada é levado em consideração na query.
	 * @param versao que será utilizada como comparação.
	 * @param status que será atribuido para os registros.
	 */
	void atualizarStatusProducaoDeVersaosDiferentesDeUmaEmEspecifico(Versao versao, Boolean status);

} // fim da interface VersaoDao 