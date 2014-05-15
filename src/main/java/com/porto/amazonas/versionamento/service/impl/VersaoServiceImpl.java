package com.porto.amazonas.versionamento.service.impl;

import com.porto.amazonas.versionamento.dao.VersaoDao;
import com.porto.amazonas.versionamento.exceptions.VersionamentoException;
import com.porto.amazonas.versionamento.model.Sistema;
import com.porto.amazonas.versionamento.model.Versao;
import com.porto.amazonas.versionamento.service.VersaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementacao de VersaoService
 *
 * @author BRUNO VIANA
 */
@Service
@Qualifier("versaoService")
public class VersaoServiceImpl implements VersaoService {

    @Autowired(required = true)
    @Qualifier("versaoDao")
    private VersaoDao versaoDao;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void salvar(Versao versao) throws VersionamentoException {
        try {
            this.versaoDao.salvar(versao);
            atualizarStatusProducao(versao);
        } catch (Exception e) {
            throw new VersionamentoException("Erro lan�ado: \n" + e.getMessage(), e.getCause());
        } // fim do bloco try/catch
    } // fim do metodo salvarVersao

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void editar(Versao versao) throws VersionamentoException {
        try {
            this.versaoDao.editar(versao);
            atualizarStatusProducao(versao);
        } catch (Exception e) {
            throw new VersionamentoException(e.getMessage(), e.getCause());
        } // fim do bloco try/catch
    } // fim do metodo editarVersao

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deletar(Versao versao) throws VersionamentoException {
        try {
            this.versaoDao.deletar(versao);
        } catch (Exception e) {
            throw new VersionamentoException(e.getMessage(), e.getCause());
        } // fim do blobo try/catch
    } // fim do metodo deletarVersao

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Versao buscarPorId(Integer id) throws VersionamentoException {
        Versao versao = new Versao();
        try {
            versao = this.versaoDao.buscarPorId(id);
        } catch (Exception e) {
            throw new VersionamentoException(e.getMessage(), e.getCause());
        } // fim do bloco try/catch
        return versao;
    } // fim do metodo buscarPorId

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Versao> listarTodos() throws VersionamentoException {
        List<Versao> versoes = new ArrayList<Versao>();
        try {
            versoes = this.versaoDao.listarTodos();
        } catch (Exception e) {
            throw new VersionamentoException(e.getMessage(), e.getCause());
        } // fim do bloco try/catch
        return versoes;
    } // fim do metodo listarTodos

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Versao> listarTodasVersoesDeUmSistema(Sistema sistema) throws VersionamentoException {
        List<Versao> versoes = new ArrayList<Versao>();
        try {
            versoes = this.versaoDao.listarTodasVersoesDeUmSistema(sistema);
        } catch (Exception e) {
            throw new VersionamentoException(e.getMessage(), e.getCause());
        } // fim do bloco try/catch
        return versoes;
    } // fim do metodo listarTodasVersoesDeUmSistema

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Versao> listarTodasVersoesDeUmSistemaPorPeriodo(Sistema sistema, Date dataInicial, Date dataFinal) throws VersionamentoException {
        List<Versao> versoes = new ArrayList<Versao>();
        try {
            versoes = this.versaoDao.listarTodasVersoesDeUmSistemaPorPeriodo(sistema, dataInicial, dataFinal);
        } catch (Exception e) {
            throw new VersionamentoException(e.getMessage(), e.getCause());
        } // fim do bloco try/catch
        return versoes;
    } // fim do metodo listarTodasVersoesDeUmSistemaPorPeriodo

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void atualizarStatusProducaoDeVersaosDiferentesDeUmaEmEspecifico(
            Versao versao, Boolean status) throws VersionamentoException {
        try {
            this.versaoDao.atualizarStatusProducaoDeVersaosDiferentesDeUmaEmEspecifico(versao, status);
        } catch (Exception e) {
            throw new VersionamentoException(e.getMessage(), e.getCause());
        } // fim do bloco try/catch
    } // fim do metodo atualizarStatusProducaoVersaoDiferentesDeUmaEmEspecifico

    /**
     * @param versao
     * @throws VersionamentoException
     */
    private void atualizarStatusProducao(Versao versao)
            throws VersionamentoException {
        if (versao.getProducao().equals(Boolean.TRUE)) {
            this.atualizarStatusProducaoDeVersaosDiferentesDeUmaEmEspecifico(versao, Boolean.FALSE);
        } // fim do bloco if
    } // fim do metodo atualizarStatusProducao

} // fim da classe VersaoServiceImpl