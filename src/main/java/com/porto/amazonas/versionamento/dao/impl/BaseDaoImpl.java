package com.porto.amazonas.versionamento.dao.impl;

import com.porto.amazonas.versionamento.dao.BaseDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Implementa��o de BaseDao.
 * Esta classe serve de base de todos os outros DAO's da aplica��o. Cont�m a implementa��o de todas as opera��es CRUD.
 *
 * @param <T> Tipo de Entidade que ser� tratada no DAO.
 * @author BRUNO VIANA
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

    @Autowired(required = true)
    protected SessionFactory sessionFactory;
    protected Class<T> classePersistente;

    @Override
    public void salvar(T entidade) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(entidade);
    } // fim do m�todo salvar

    @Override
    public void editar(T entidade) {
        this.sessionFactory.getCurrentSession().update(entidade);
    } // fim do m�todo editar

    @Override
    public void deletar(T entidade) {
        this.sessionFactory.getCurrentSession().delete(entidade);
    } // fim do m�todo deletar

    @SuppressWarnings("unchecked")
    @Override
    public T buscarPorId(Integer id) {
        return (T) this.sessionFactory.getCurrentSession().get(this.classePersistente, id);
    } // fim do m�todo buscarPorId

    @SuppressWarnings("unchecked")
    @Override
    public List<T> listarTodos() {
        Criteria criteria = this.obterCriteria(this.classePersistente);
        return criteria.list();
    } // fim do m�todo listarTodos

    /**
     * M�todo utilizado para encapsular o modo como se obt�m uma Criteria para execu��o de consultas no banco de dados.
     *
     * @param classePersitente que representa a entidade que ser� trabalhada.
     * @return criteria mapeada para a entidade solicitada.
     */
    protected Criteria obterCriteria(Class<T> classePersitente) {
        return this.sessionFactory.getCurrentSession().createCriteria(classePersitente);
    } // fim do m�todo obterCriteria

    protected Query obterQuery(String hql) {
        return this.sessionFactory.getCurrentSession().createQuery(hql);
    }

} // fim da classe BaseDaoImpl
