package com.porto.amazonas.versionamento.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.porto.amazonas.versionamento.dao.BaseDao;

/**
 * Implementação de BaseDao. 
 * Esta classe serve de base de todos os outros DAO's da aplicação. Contém a implementação de todas as operações CRUD.
 * @author BRUNO VIANA
 * @param <T> Tipo de Entidade que será tratada no DAO.
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired(required = true)
	protected SessionFactory sessionFactory;
	protected Class<T> classePersistente;

	@Override
	public void salvar(T entidade) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(entidade);
	} // fim do método salvar

	@Override
	public void editar(T entidade) {
		this.sessionFactory.getCurrentSession().update(entidade);
	} // fim do método editar

	@Override
	public void deletar(T entidade) {
		this.sessionFactory.getCurrentSession().delete(entidade);
	} // fim do método deletar

	@SuppressWarnings("unchecked")
	@Override
	public T buscarPorId(Integer id) {
		return (T) this.sessionFactory.getCurrentSession().get(this.classePersistente, id);
	} // fim do método buscarPorId

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listarTodos() {
		Criteria criteria = this.obterCriteria(this.classePersistente);
		return criteria.list();
	} // fim do método listarTodos
	
	/**
	 * Método utilizado para encapsular o modo como se obtém uma Criteria para execução de consultas no banco de dados.
	 * @param classePersitente que representa a entidade que será trabalhada.
	 * @return criteria mapeada para a entidade solicitada.
	 */
	protected Criteria obterCriteria(Class<T> classePersitente) {
		return this.sessionFactory.getCurrentSession().createCriteria(classePersitente);
	} // fim do método obterCriteria
	
	protected Query obterQuery(String hql) {
		return this.sessionFactory.getCurrentSession().createQuery(hql);
	}

} // fim da classe BaseDaoImpl
