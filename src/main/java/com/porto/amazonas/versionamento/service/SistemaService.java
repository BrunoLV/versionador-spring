package com.porto.amazonas.versionamento.service;

import java.util.List;

import com.porto.amazonas.versionamento.exceptions.VersionamentoException;
import com.porto.amazonas.versionamento.model.EnumStatus;
import com.porto.amazonas.versionamento.model.Sistema;

/**
 * Interface que define o comportamento da classe de negócio para operações com
 * a Entidade Sistema.
 * @author BRUNO VIANA
 */
public interface SistemaService extends BaseService<Sistema> {

	/**
	 * Método utilizado para listar todos os sistemas registrado na aplicaÃ§Ã£o
	 * pelo status. 
	 * @param status que será utilizado para consulta.
	 * @return List com todos os sistemas resultados da consulta.
	 * @throws VersionamentoException para qualquer erro que ocorra durante a execução do método.
	 */
	List<Sistema> listarTodosSistemasPorStatus(EnumStatus status) throws VersionamentoException;

} // fim da interface SistemaService