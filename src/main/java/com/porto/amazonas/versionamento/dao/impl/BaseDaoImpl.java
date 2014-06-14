package com.porto.amazonas.versionamento.dao.impl;

import com.porto.amazonas.versionamento.dao.BaseDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Implementacao de BaseDao.
 * Esta classe serve de base de todos os outros DAO's da aplicacao. Contem a Implementacao de todas as operacoes CRUD.
 *
 * @param <T> Tipo de Entidade que sera tratada no DAO.
 * @author BRUNO VIANA
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

    @Autowired(required = true)
    protected SessionFactory sessionFactory;
    protected Class<T> classePersistente;

    @Override
    public void salvar(T entidade) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(entidade);
    } // fim do metodo salvar

    @Override
    public void editar(T entidade) {
        this.sessionFactory.getCurrentSession().update(entidade);
    } // fim do metodo editar

    @Override
    public void deletar(T entidade) {
        this.sessionFactory.getCurrentSession().delete(entidade);
    } // fim do metodo deletar

    @SuppressWarnings("unchecked")
    @Override
    public T buscarPorId(Integer id) {
        return (T) this.sessionFactory.getCurrentSession().get(this.classePersistente, id);
    } // fim do metodo buscarPorId

    @SuppressWarnings("unchecked")
    @Override
    public List<T> listarTodos() {
        Criteria criteria = this.obterCriteria(this.classePersistente);
        return criteria.list();
    } // fim do metodo listarTodos

    /**
     * Metodo utilizado para encapsular o modo como se obtem uma Criteria para execucao de consultas no banco de dados.
     *
     * @param classePersitente que representa a entidade que sera trabalhada.
     * @return criteria mapeada para a entidade solicitada.
     */
    protected Criteria obterCriteria(Class<T> classePersitente) {
        return this.sessionFactory.getCurrentSession().createCriteria(classePersitente);
    } // fim do metodo obterCriteria

    /**
     * Metodo utilizado para encapsular o modo como se obtem um Query para execucao de consultas no banco de dados.
     *
     * @param hql que representa a query de consulta.
     * @return Query mapeada para a consulta solicitada.
     */
    protected Query obterQuery(String hql) {
        return this.sessionFactory.getCurrentSession().createQuery(hql);
    } // fim do metodo obterQuery

} // fim da classe BaseDaoImpl
