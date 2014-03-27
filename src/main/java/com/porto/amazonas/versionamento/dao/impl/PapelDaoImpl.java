package com.porto.amazonas.versionamento.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.porto.amazonas.versionamento.dao.PapelDao;
import com.porto.amazonas.versionamento.model.EnumPapel;
import com.porto.amazonas.versionamento.model.Papel;

@Repository
@Qualifier("papelDao")
public class PapelDaoImpl extends BaseDaoImpl<Papel> implements PapelDao {
	
	private static final Class<Papel> CLASSE_PERSISTENTE = Papel.class;
	
	public PapelDaoImpl() {
		this.classePersistente = CLASSE_PERSISTENTE;
	}

	@Override
	public Papel buscarPapelPorDescricao(EnumPapel enumPapel) {
		Criteria criteria = this.obterCriteria(this.classePersistente);
		criteria.add(Restrictions.eq("nomePapel", enumPapel));
		return (Papel) criteria.uniqueResult();
	}

}
