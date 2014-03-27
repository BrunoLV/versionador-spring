package com.porto.amazonas.versionamento.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.porto.amazonas.versionamento.dao.DependenciaDao;
import com.porto.amazonas.versionamento.exceptions.VersionamentoException;
import com.porto.amazonas.versionamento.model.Dependencia;
import com.porto.amazonas.versionamento.model.Sistema;
import com.porto.amazonas.versionamento.service.DependenciaService;

/**
 * Implementação de DependenciaService.
 * @author BRUNO VIANA
 */
@Service
@Qualifier("dependenciaService")
public class DependenciaServiceImpl implements DependenciaService {

	@Autowired(required = true)
	@Qualifier("dependenciaDao")
	private DependenciaDao dependenciaDao;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void salvar(Dependencia dependencia) throws VersionamentoException {
		try {
			this.dependenciaDao.salvar(dependencia);
		} catch (Exception e) {
			throw new VersionamentoException(e.getMessage(), e.getCause());
		} // fim do bloco try/catch
	} // fim do método salvarDependencia

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void editar(Dependencia dependencia) throws VersionamentoException {
		try {
			this.dependenciaDao.editar(dependencia);
		} catch (Exception e) {
			throw new VersionamentoException(e.getMessage(), e.getCause());
		} // fim do bloco try/catch
	} // fim do método editarDependencia

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deletar(Dependencia dependencia) throws VersionamentoException {
		try {
			this.dependenciaDao.deletar(dependencia);
		} catch (Exception e) {
			throw new VersionamentoException(e.getMessage(), e.getCause());
		} // fim do bloco try/catch
	} // fim do método deletarDependencia

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
	public Dependencia buscarPorId(Integer id) throws VersionamentoException {
		Dependencia dependencia = new Dependencia();
		try {
			dependencia = this.dependenciaDao.buscarPorId(id);
		} catch (Exception e) {
			throw new VersionamentoException(e.getMessage(), e.getCause());
		} // fim do bloco try/catch
		return dependencia;
	} // fim do método buscarPorId

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
	public List<Dependencia> listarTodos() throws VersionamentoException {
		List<Dependencia> dependencias = new ArrayList<Dependencia>();
		try {
			dependencias = this.dependenciaDao.listarTodos();
		} catch (Exception e) {
			throw new VersionamentoException(e.getMessage(), e.getCause());
		} // fim do bloco try/catch
		return dependencias;
	} // fim do método listarTodos

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
	public List<Dependencia> listarDependenciasDeUmSistema(Sistema sistema) throws VersionamentoException {
		List<Dependencia> dependencias = new ArrayList<Dependencia>();
		try {
			dependencias = this.dependenciaDao.listarDependenciasDeUmSistema(sistema);
		} catch (Exception e) {
			throw new VersionamentoException(e.getMessage(), e.getCause());
		} // fim do bloco try/catch
		return dependencias;
	} // fim do método listarDependenciasDeUmSistema

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
	public List<Sistema> listarSistemasRelacionadosAUmaDependencia(Dependencia dependencia) throws VersionamentoException {
		List<Sistema> sistemas = null;
		try {
			sistemas = this.dependenciaDao.buscarDependenciaComSistemasRelacionados(dependencia).getSistemas();
		} catch (Exception e) {
			sistemas = new ArrayList<Sistema>();
			throw new VersionamentoException(e.getMessage(), e.getCause());
		} // fim do bloco try/catch
		return sistemas;
	} // fim do método listarSistemasRelacionadosAUmaDependencia

} // fim da classe DependenciaServiceImpl