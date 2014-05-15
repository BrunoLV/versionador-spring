package com.porto.amazonas.versionamento.service;

import com.porto.amazonas.versionamento.exceptions.VersionamentoException;
import com.porto.amazonas.versionamento.model.EnumStatus;
import com.porto.amazonas.versionamento.model.Sistema;

import java.util.List;

/**
 * Interface que define o comportamento da classe de negocio para operacoes com
 * a Entidade Sistema.
 *
 * @author BRUNO VIANA
 */
public interface SistemaService extends BaseService<Sistema> {

    /**
     * Metodo utilizado para listar todos os sistemas registrado na aplicação
     * pelo status.
     *
     * @param status que sera utilizado para consulta.
     * @return List com todos os sistemas resultados da consulta.
     * @throws VersionamentoException para qualquer erro que ocorra durante a execucao do Metodo.
     */
    List<Sistema> listarTodosSistemasPorStatus(EnumStatus status) throws VersionamentoException;

} // fim da interface SistemaService