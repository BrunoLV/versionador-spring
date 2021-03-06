package com.porto.amazonas.versionamento.dao.impl;

import com.porto.amazonas.versionamento.dao.PapelDao;
import com.porto.amazonas.versionamento.model.EnumPapel;
import com.porto.amazonas.versionamento.model.Papel;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Implementacao de PapelDao.
 */
@Repository
@Qualifier("papelDao")
public class PapelDaoImpl extends BaseDaoImpl<Papel> implements PapelDao {

    private static final Class<Papel> CLASSE_PERSISTENTE = Papel.class;

    public PapelDaoImpl() {
        this.classePersistente = CLASSE_PERSISTENTE;
    } // fim do construtor

    @Override
    public Papel buscarPapelPorDescricao(EnumPapel enumPapel) {
        Criteria criteria = this.obterCriteria(this.classePersistente);
        criteria.add(Restrictions.eq("nomePapel", enumPapel));
        return (Papel) criteria.uniqueResult();
    } // fim do método buscarPapelPorDescricao

} // fim da classe PapelDaoImpl
