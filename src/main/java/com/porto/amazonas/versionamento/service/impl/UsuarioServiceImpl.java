package com.porto.amazonas.versionamento.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.porto.amazonas.versionamento.dao.PapelDao;
import com.porto.amazonas.versionamento.dao.UsuarioDao;
import com.porto.amazonas.versionamento.exceptions.VersionamentoException;
import com.porto.amazonas.versionamento.model.EnumPapel;
import com.porto.amazonas.versionamento.model.Papel;
import com.porto.amazonas.versionamento.model.Usuario;
import com.porto.amazonas.versionamento.service.UsuarioService;

/**
 * Implementação de UsuarioService.
 * @author BRUNO VIANA
 */
@Service
@Qualifier("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	@Qualifier("usuarioDao")
	private UsuarioDao usuarioDao;
	
	@Autowired
	@Qualifier("papelDao")
	private PapelDao papelDao;

	@Override
	public void salvar(Usuario t) throws VersionamentoException {
	} // fim do método salvar

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void editar(Usuario entidade) throws VersionamentoException {
		try {
			this.usuarioDao.editar(entidade);
		} catch (Exception e) {
			throw new VersionamentoException(e.getMessage(), e.getCause());
		} // fim do bloco try/catch
	} // fim do método editar

	@Override
	public void deletar(Usuario t) throws VersionamentoException {
	} // fim do método deletar

	@Override
	public Usuario buscarPorId(Integer id) throws VersionamentoException {
		return null;
	} // fim do método buscarPorId

	@Override
	public List<Usuario> listarTodos() throws VersionamentoException {
		return null;
	} // fim do método listarTodos
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
	public Usuario buscarUsuarioPorMatricula(String matricula) throws VersionamentoException {
		Usuario usuario = new Usuario();
		try {
			usuario = this.usuarioDao.buscarUsuarioPorMatricula(matricula);
		} catch (Exception e) {
			throw new VersionamentoException(e.getMessage(), e.getCause());
		} // fim do bloco try/catch
		return usuario;
	} // fim do método buscarUsuarioPorMatricula

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void cadastrarUsuarioComDeterminadoPapel(Usuario usuario, EnumPapel papel) throws VersionamentoException {
		try {
			usuarioDao.salvar(usuario);
			Papel papelUsuario = papelDao.buscarPapelPorDescricao(papel);
			Set<Papel> papeis = new HashSet<Papel>();
			papeis.add(papelUsuario);
			usuario.setPapeis(papeis);
			usuarioDao.editar(usuario);
		} catch (Exception e) {
			throw new VersionamentoException(e.getMessage(), e.getCause());
		}
	}

} // fim da classe UsuarioServiceImpl