package com.porto.amazonas.versionamento.service.impl;

import com.porto.amazonas.versionamento.dao.SistemaDao;
import com.porto.amazonas.versionamento.exceptions.VersionamentoException;
import com.porto.amazonas.versionamento.model.EnumStatus;
import com.porto.amazonas.versionamento.model.Sistema;
import com.porto.amazonas.versionamento.service.SistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementacao de SistemaService.
 *
 * @author BRUNO VIANA
 */
@Service
@Qualifier("sistemaService")
public class SistemaServiceImpl implements SistemaService {

    @Autowired(required = true)
    @Qualifier("sistemaDao")
    private SistemaDao sistemaDao;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void salvar(Sistema sistema) throws VersionamentoException {
        try {
            this.sistemaDao.salvar(sistema);
        } catch (Exception e) {
            throw new VersionamentoException(e.getMessage(), e.getCause());
        } // fim do bloco try/catch
    } // fim do metodo salvarSistema

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void editar(Sistema sistema) throws VersionamentoException {
        try {
            this.sistemaDao.editar(sistema);
        } catch (Exception e) {
            throw new VersionamentoException(e.getMessage(), e.getCause());
        } // fim do bloco try/catch
    } // fim do metodo editarSistema

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deletar(Sistema sistema) throws VersionamentoException {
        try {
            this.sistemaDao.deletar(sistema);
        } catch (Exception e) {
            throw new VersionamentoException(e.getMessage(), e.getCause());
        } // fim do bloco try/catch
    } // fim do metodo deletarSistema

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Sistema buscarPorId(Integer id) throws VersionamentoException {
        Sistema sistema = new Sistema();
        try {
            sistema = this.sistemaDao.buscarPorId(id);
        } catch (Exception e) {
            throw new VersionamentoException(e.getMessage(), e.getCause());
        } // fim do bloco try/catch
        return sistema;
    } // fim do metodo buscarPorId

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Sistema> listarTodos() throws VersionamentoException {
        List<Sistema> sistemas = new ArrayList<Sistema>();
        try {
            sistemas = this.sistemaDao.listarTodos();
        } catch (Exception e) {
            throw new VersionamentoException(e.getMessage(), e.getCause());
        } // fim do bloco try/catch
        return sistemas;
    } // fim do metodo listarTodos

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Sistema> listarTodosSistemasPorStatus(EnumStatus statusSistema) throws VersionamentoException {
        List<Sistema> sistemas = new ArrayList<Sistema>();
        try {
            sistemas = this.sistemaDao.listarTodosSistemasPorStatus(statusSistema);
        } catch (Exception e) {
            throw new VersionamentoException(e.getMessage(), e.getCause());
        } // fim do bloco try/catch
        return sistemas;
    } // fim do metodo listaTodosSistemasPorStatus

} // fim da classe SistemaServiceImpl