package com.porto.amazonas.versionamento.dao.impl;

import com.porto.amazonas.versionamento.dao.SistemaDao;
import com.porto.amazonas.versionamento.model.EnumStatus;
import com.porto.amazonas.versionamento.model.Sistema;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementacao de SistemaDao.
 *
 * @author BRUNO VIANA
 */
@Repository
@Qualifier("sistemaDao")
public class SistemaDaoImpl extends BaseDaoImpl<Sistema> implements SistemaDao {

    private static final Class<Sistema> CLASSE_PERSISTENTE = Sistema.class;

    /**
     * Metodo construtor.
     */
    public SistemaDaoImpl() {
        this.classePersistente = CLASSE_PERSISTENTE;
    } // fim do Metodo construtor

    @SuppressWarnings("unchecked")
    @Override
    public List<Sistema> listarTodosSistemasPorStatus(EnumStatus statusSistema) {
        Criteria criteria = this.obterCriteria(this.classePersistente);
        criteria.add(Restrictions.eq("status", statusSistema));
        return criteria.list();
    } // fim do Metodo listarTodosSistemasPorStatus

} // fim da classe SistemaDaoImpl