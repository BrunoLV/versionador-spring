package com.porto.amazonas.versionamento.service;

import java.util.List;

import com.porto.amazonas.versionamento.exceptions.VersionamentoException;
import com.porto.amazonas.versionamento.model.Dependencia;
import com.porto.amazonas.versionamento.model.Sistema;

/**
 * Interface que define o comportamento da classe de negócio para operações 
 * com a Entidade Dependencia.
 * @author BRUNO VIANA
 */
public interface DependenciaService extends BaseService<Dependencia> {
	
	/**
	 * Método utilizado para listar todas as dependencias de um sistema.
	 * @param sistema que será utilizado como parametro.
	 * @return lista com as dependencias encontradas.
	 * @throws VersionamentoException para qualquer erro que ocorra durante a execução do método.
	 */
	List<Dependencia> listarDependenciasDeUmSistema(Sistema sistema) throws VersionamentoException;
	
	/**
	 * Método utilizado para listar os sistemas relacionados a uma dependencia.
	 * @param dependencia que será utilizada como parametro.
	 * @return lista de dependencias encontradas.
	 * @throws VersionamentoException para qualquer erro que ocorra durante a execução do método.
	 */
	List<Sistema> listarSistemasRelacionadosAUmaDependencia(Dependencia dependencia) throws VersionamentoException;

} // fim da interface DependenciaService
