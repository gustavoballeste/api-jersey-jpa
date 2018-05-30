package br.com.itsimple.smartcities.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.itsimple.smartcities.dao.GenericDAO;

public class GenericDAOImpl<T, K> implements GenericDAO<T, K> {

    protected EntityManager em;
    private Class<T> clazz;

    @SuppressWarnings("unchecked")
    public GenericDAOImpl(EntityManager em) {
        this.em = em;
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void cadastrar(T entidade) {
        em.persist(entidade);
    }

    @Override
    public void atualizar(T entidade) {
        em.merge(entidade);
    }

    @Override
    public T buscar(K chave) {
        return em.find(clazz, chave);
    }

    @Override
    public void remover(K chave) throws Exception {
        T entidade = buscar(chave);
        if (entidade == null) {
            throw new Exception("Entidade não encontrada");
        }
        em.remove(entidade);
    }

    @Override
    public List<T> listar() {
        return em.createQuery("from " + clazz.getName(), clazz).getResultList();
    }

    @Override
    public void commit() throws Exception {
        try {
            em.getTransaction().begin();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("Erro no commit");
        }
    }
}
