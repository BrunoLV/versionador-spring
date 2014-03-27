package com.porto.amazonas.versionamento.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.porto.amazonas.versionamento.dao.UsuarioDao;
import com.porto.amazonas.versionamento.model.Usuario;

/**
 * Implementa��o de UsuarioDao.
 * @author BRUNO VIANA
 */
@Repository
@Qualifier("usuarioDao")
public class UsuarioDaoImpl extends BaseDaoImpl<Usuario> implements UsuarioDao {

	private static final Class<Usuario> CLASSE_PERSISTENTE = Usuario.class;

	/**
	 * M�todo construtor.
	 */
	public UsuarioDaoImpl() {
		this.classePersistente = CLASSE_PERSISTENTE;
	} // fim do m�todo construtor

	@Override
	public Usuario buscarUsuarioPorMatricula(String matricula) {
		Criteria criteria = this.obterCriteria(this.classePersistente);
		criteria.add(Restrictions.eq("matricula", matricula));
		return (Usuario) criteria.uniqueResult();
	} // fim do m�todo buscarUsuarioPorMatricula

	@Override
	public Usuario buscarUsuarioPorMatriculaESenha(String matricula, String senha) {
		Criteria criteria = this.obterCriteria(this.classePersistente);
		criteria.add(Restrictions.and(Restrictions.eq("matricula", matricula), Restrictions.eqProperty("senha", senha)));
		return (Usuario) criteria.uniqueResult();
	} // fim do m�todo buscarUsuarioPorMatriculaESenha

} // fim da classe UsuarioDaoImpl