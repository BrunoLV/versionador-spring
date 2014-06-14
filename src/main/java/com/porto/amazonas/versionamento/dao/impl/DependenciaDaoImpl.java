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
 * Implementacao DependenciaDao.
 *
 * @author BRUNO VIANA
 */
@Repository
@Qualifier("dependenciaDao")
public class DependenciaDaoImpl extends BaseDaoImpl<Dependencia> implements DependenciaDao {

    private static final Class<Dependencia> CLASSE_PERSISTENTE = Dependencia.class;

    public DependenciaDaoImpl() {
        this.classePersistente = CLASSE_PERSISTENTE;
    } // fim do metodo construtor

    @SuppressWarnings("unchecked")
    @Override
    public List<Dependencia> listarDependenciasDeUmSistema(Sistema sistema) {
        Criteria criteria = this.obterCriteria(this.classePersistente);
        criteria.createAlias("sistemas", "sis").add(Restrictions.eq("sis.id", sistema.getId()));
        return criteria.list();
    } // fim do metodo listarDependenciasDeUmSistema

    @Override
    public Dependencia buscarDependenciaComSistemasRelacionados(Dependencia dependencia) {
        Criteria criteria = this.obterCriteria(this.classePersistente);
        criteria.add(Restrictions.eq("id", dependencia.getId()));
        criteria.setFetchMode("sistemas", FetchMode.JOIN);
        return ((Dependencia) criteria.uniqueResult());
    } // fim do metodo listarSistemasRelacionadosAUmaDependencia

} // fim da classe DependenciaDaoImpl