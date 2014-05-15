package com.porto.amazonas.versionamento.service;

import com.porto.amazonas.versionamento.exceptions.VersionamentoException;
import com.porto.amazonas.versionamento.model.Dependencia;
import com.porto.amazonas.versionamento.model.Sistema;

import java.util.List;

/**
 * Interface que define o comportamento da classe de neg�cio para opera��es
 * com a Entidade Dependencia.
 *
 * @author BRUNO VIANA
 */
public interface DependenciaService extends BaseService<Dependencia> {

    /**
     * M�todo utilizado para listar todas as dependencias de um sistema.
     *
     * @param sistema que ser� utilizado como parametro.
     * @return lista com as dependencias encontradas.
     * @throws VersionamentoException para qualquer erro que ocorra durante a execu��o do m�todo.
     */
    List<Dependencia> listarDependenciasDeUmSistema(Sistema sistema) throws VersionamentoException;

    /**
     * M�todo utilizado para listar os sistemas relacionados a uma dependencia.
     *
     * @param dependencia que ser� utilizada como parametro.
     * @return lista de dependencias encontradas.
     * @throws VersionamentoException para qualquer erro que ocorra durante a execu��o do m�todo.
     */
    List<Sistema> listarSistemasRelacionadosAUmaDependencia(Dependencia dependencia) throws VersionamentoException;

} // fim da interface DependenciaService
