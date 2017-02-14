package com.vds.app.base;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.vds.app.exception.MyException;

@Repository
@Transactional
public class BaseJpaImpl<T> implements BaseJpa<T> {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public int countRecord(String hql, List<Object> objs) throws MyException {
		Query query = manager.createQuery(hql);
		if (null != objs) {
			for (int i = 0; i < objs.size(); i++) {
				query.setParameter(i, objs.get(i));
			}
		}
		int count = Integer.valueOf(query.getSingleResult().toString());
		manager.close();
		return count;
	}

	@Override
	public List<?> select(String hql, int indexBegin, int size, List<Object> objs) throws MyException {
		Query query = manager.createQuery(hql);
		if (null != objs) {
			for (int i = 0; i < objs.size(); i++) {
				query.setParameter(i, objs.get(i));
			}
		}
		query.setFirstResult(indexBegin);
		query.setMaxResults(size);

		return query.getResultList();
	}

	@Override
	public List<?> select(String hql) throws MyException {
		Query query = manager.createQuery(hql);
		return query.getResultList();
	}

	@Override
	public List<?> select(String hql, List<Object> objs) throws MyException {
		Query query = manager.createQuery(hql);
		if (null != objs) {
			for (int i = 0; i < objs.size(); i++) {
				query.setParameter(i, objs.get(i));
			}
		}
		return query.getResultList();
	}

	@Override
	public List<?> selectSql(String hql) throws MyException {
		Query query = manager.createNativeQuery(hql);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<?> result = query.getResultList();
		return result;
	}

	@Override
	@Transactional
	public T save(T t) throws MyException {
		manager.merge(t);
		return t;
	}

	@Override
	@Transactional
	public void remove(T t) throws MyException {
		manager.remove(manager.merge(t));
	}

	@Override
	@Transactional
	public void updateHql(String hql) throws MyException {
		Query query = manager.createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public T findByOne(Class<T> t, String id) throws MyException {
		// TODO Auto-generated method stub
		return manager.find(t, id);
	}

}
