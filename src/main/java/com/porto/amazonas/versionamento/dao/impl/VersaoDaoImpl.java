package com.porto.amazonas.versionamento.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.porto.amazonas.versionamento.dao.VersaoDao;
import com.porto.amazonas.versionamento.model.Sistema;
import com.porto.amazonas.versionamento.model.Versao;

/**
 * Implementação de VersaoDao.
 * @author BRUNO VIANA
 */
@Repository
@Qualifier("versaoDao")
public class VersaoDaoImpl extends BaseDaoImpl<Versao> implements VersaoDao {

	private static final Class<Versao> CLASSE_PERSISTENTE = Versao.class;

	/**
	 * Método construtor.
	 */
	public VersaoDaoImpl() {
		this.classePersistente = CLASSE_PERSISTENTE;
	} // fim do método construtor.

	@SuppressWarnings("unchecked")
	@Override
	public List<Versao> listarTodasVersoesDeUmSistema(Sistema sistema) {
		Criteria criteria = this.obterCriteria(this.classePersistente);
		criteria.add(Restrictions.eq("sistema", sistema));
		return criteria.list();
	} // fim do método listarTodasDeUmSistema

	@SuppressWarnings("unchecked")
	@Override
	public List<Versao> listarTodasVersoesDeUmSistemaPorPeriodo(Sistema sistema, Date dataInicio, Date dataFinal) {
		Criteria criteria = this.obterCriteria(this.classePersistente);
		criteria.add(Restrictions.eq("sistema", sistema)).add(Restrictions.between("dataCriacao", dataInicio, dataFinal));
		return criteria.list();
	} // fim do método listarTodasVersoesDeUmSistemaPorPeriodo

	@Override
	public void atualizarStatusProducaoDeVersaosDiferentesDeUmaEmEspecifico(
			Versao versao, Boolean status) {
		String hql = "update Versao set producao = :novoStatus where id <> :idVersao and sistema.id = :idSistema";
		Query query = this.obterQuery(hql);
		query.setParameter("novoStatus", status);
		query.setParameter("idVersao", versao.getId());
		query.setParameter("idSistema", versao.getSistema().getId());
		query.executeUpdate();
	} // fim do método atualizarStatusProducaoDeVersaosDiferentesDeUmaEmEspecifico

} // fim da classe VersaoDaoImpl