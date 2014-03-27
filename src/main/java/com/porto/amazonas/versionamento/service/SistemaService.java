package com.porto.amazonas.versionamento.service;

import java.util.List;

import com.porto.amazonas.versionamento.exceptions.VersionamentoException;
import com.porto.amazonas.versionamento.model.EnumStatus;
import com.porto.amazonas.versionamento.model.Sistema;

/**
 * Interface que define o comportamento da classe de neg�cio para opera��es com
 * a Entidade Sistema.
 * @author BRUNO VIANA
 */
public interface SistemaService extends BaseService<Sistema> {

	/**
	 * M�todo utilizado para listar todos os sistemas registrado na aplicação
	 * pelo status. 
	 * @param status que ser� utilizado para consulta.
	 * @return List com todos os sistemas resultados da consulta.
	 * @throws VersionamentoException para qualquer erro que ocorra durante a execu��o do m�todo.
	 */
	List<Sistema> listarTodosSistemasPorStatus(EnumStatus status) throws VersionamentoException;

} // fim da interface SistemaService