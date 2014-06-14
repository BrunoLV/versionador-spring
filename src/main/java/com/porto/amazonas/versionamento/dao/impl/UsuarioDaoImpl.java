package com.porto.amazonas.versionamento.dao.impl;

import com.porto.amazonas.versionamento.dao.UsuarioDao;
import com.porto.amazonas.versionamento.model.Usuario;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Implementacao de UsuarioDao.
 *
 * @author BRUNO VIANA
 */
@Repository
@Qualifier("usuarioDao")
public class UsuarioDaoImpl extends BaseDaoImpl<Usuario> implements UsuarioDao {

    private static final Class<Usuario> CLASSE_PERSISTENTE = Usuario.class;

    /**
     * Metodo construtor.
     */
    public UsuarioDaoImpl() {
        this.classePersistente = CLASSE_PERSISTENTE;
    } // fim do Metodo construtor

    @Override
    public Usuario buscarUsuarioPorMatricula(String matricula) {
        Criteria criteria = this.obterCriteria(this.classePersistente);
        criteria.add(Restrictions.eq("matricula", matricula));
        return (Usuario) criteria.uniqueResult();
    } // fim do Metodo buscarUsuarioPorMatricula

    @Override
    public Usuario buscarUsuarioPorMatriculaESenha(String matricula, String senha) {
        Criteria criteria = this.obterCriteria(this.classePersistente);
        criteria.add(Restrictions.and(Restrictions.eq("matricula", matricula), Restrictions.eqProperty("senha", senha)));
        return (Usuario) criteria.uniqueResult();
    } // fim do Metodo buscarUsuarioPorMatriculaESenha

} // fim da classe UsuarioDaoImpl