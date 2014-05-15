package com.porto.amazonas.versionamento.dao.impl;

import com.porto.amazonas.versionamento.dao.DependenciaDao;
import com.porto.amazonas.versionamento.model.Dependencia;
import com.porto.amazonas.versionamento.model.Sistema;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementa��o DependenciaDao.
 *
 * @author BRUNO VIANA
 */
@Repository
@Qualifier("dependenciaDao")
public class DependenciaDaoImpl extends BaseDaoImpl<Dependencia> implements DependenciaDao {

    private static final Class<Dependencia> CLASSE_PERSISTENTE = Dependencia.class;

    public DependenciaDaoImpl() {
        this.classePersistente = CLASSE_PERSISTENTE;
    } // fim do m�todo construtor

    @SuppressWarnings("unchecked")
    @Override
    public List<Dependencia> listarDependenciasDeUmSistema(Sistema sistema) {
        Criteria criteria = this.obterCriteria(this.classePersistente);
        criteria.createAlias("sistemas", "sis").add(Restrictions.idEq(sistema.getId())).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    } // fim do m�todo listarDependenciasDeUmSistema

    @Override
    public Dependencia buscarDependenciaComSistemasRelacionados(Dependencia dependencia) {
        Criteria criteria = this.obterCriteria(this.classePersistente);
        criteria.add(Restrictions.eq("id", dependencia.getId()));
        criteria.setFetchMode("sistemas", FetchMode.JOIN);
        return ((Dependencia) criteria.uniqueResult());
    } // fim do m�todo listarSistemasRelacionadosAUmaDependencia

} // fim da classe DependenciaDaoImpl