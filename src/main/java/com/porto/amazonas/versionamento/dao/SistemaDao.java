package com.porto.amazonas.versionamento.dao;

import java.util.List;

import com.porto.amazonas.versionamento.model.EnumStatus;
import com.porto.amazonas.versionamento.model.Sistema;

/**
 * Interface que determina o comportamento do DAO que trata das opera��es no
 * banco de dados para a entidade Sistema.
 * @author BRUNO VIANA
 */
public interface SistemaDao extends BaseDao<Sistema> {

	/**
	 * M�todo utilizado para listar todos os sistemas cadastrado no banco de
	 * dados utilizando o status como paramentro de consulta. 
	 * @param status que ser� utilizado como parametro de consulta.
	 * @return List com todos os sistemas retornados na pesquisa.
	 */
	List<Sistema> listarTodosSistemasPorStatus(EnumStatus status);

} // fim da interface Sistema Dao