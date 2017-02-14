package com.vds.app.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.util.QueryHelper;
import com.vds.app.util.SearchFilter;

public class BaseServiceImpl<T> implements BaseService<T> {

	@Inject
	public BaseJpa<T> jpa;

	private Class<T> cls = null;

	@Override
	public Msg add(T t) throws MyException {

		return Msg.MsgSuccess(jpa.save(t));
	}

	@Override
	public Msg modify(T t) throws MyException {

		return Msg.MsgSuccess(jpa.save(t));
	}

	@Override
	public Msg remove(T t) throws MyException {
		jpa.remove(t);
		return Msg.MsgSuccess();
	}

	public Msg removew(T t, String re, String id, String idv) throws MyException {
		jpa.updateHql("update from " + t.getClass() + " set " + re + "=1 where " + id + "='" + idv + "'");
		return Msg.MsgSuccess();
	}

	@Override
	public Msg findPage(Pageable pageable) throws MyException {

		return Msg.MsgSuccess();
	}

	public Page<?> findAll(String hql, Map<String, Object> map, Pageable pageable) throws MyException {
		List<?> list = null;
		int total = 0;
		int cpage = pageable.getPageNumber();// 第几页
		int size = pageable.getPageSize();// 每页显示多上行
		int index = (cpage - 1) * size;

		Sort sort = pageable.getSort();

		String od = "";
		if (null != sort) {
			od = sort.toString().replaceAll(":", "");
		}
		if (null != map) {
			hql += SearchFilter.parses(map);
		}
		// System.out.println("hql:"+hql);
		String cHql = "select count(*) " + getFromClause(hql);// 查询条数
		total = this.jpa.countRecord(cHql, null);
		if (StringUtils.isNoneBlank(od)) {
			hql += " order by " + od;
		}

		list = this.jpa.select(hql, index, size, null);

		pageable = new PageRequest(cpage - 1, size);
		Page<?> page = new PageImpl<>(list, pageable, total);

		return page;
	}

	/**
	 * 从sql中找出from子句
	 * 
	 * @param sql
	 * @return
	 */
	private String getFromClause(String hql) {
		String sql2 = hql.toLowerCase();
		int index = sql2.indexOf(" from ");
		if (index < 0) {
			return null;
		} else {
			int i1 = sql2.lastIndexOf(" order by ");
			int i2 = sql2.lastIndexOf(" group by ");

			if (i1 >= 0 && i2 >= 0) {
				return hql.substring(index, i1 > i2 ? i2 : i1);
			} else if (i1 >= 0) {
				return hql.substring(index, i1);
			} else if (i2 >= 0) {
				return hql.substring(index, i2);
			} else {
				return hql.substring(index);
			}
		}
	}

	public Page<?> findAll(QueryHelper queryHelper, Pageable pageable, Map<String, Object> map) throws MyException {
		if (null != map) {
			int i = 0;
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				queryHelper.addCondition(entry.getKey() + "=?" + i, entry.getValue());
				i++;
			}
		}
		return findAll(queryHelper, pageable);
	}

	public Page<?> findAll(QueryHelper queryHelper, Pageable pageable) throws MyException {
		List<?> list = null;
		int total = 0;
		int cpage = pageable.getPageNumber();// 第几页
		int size = pageable.getPageSize();// 每页显示多上行
		int index = (cpage - 1) * size;

		List<Object> obj = queryHelper.getParameters();

		total = this.jpa.countRecord(queryHelper.getCountQueryHql(), obj);

		list = this.jpa.select(queryHelper.getListQueryHql(), index, size, obj);
		pageable = new PageRequest(cpage - 1, size);
		Page<?> page = new PageImpl<>(list, pageable, total);
		return page;
	}

	@Override
	public Msg findPage(Map<String, Object> map, Pageable pageable) throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> find(String hql) throws MyException {
		return jpa.select(hql);
	}

	@Override
	public Msg findByOne(String id) throws MyException {
		T t = findOne(id);
		if (null == t) {
			return Msg.MsgError("查询不到对应id 的记录");
		}
		return Msg.MsgSuccess(t);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public T findOne(String id) throws MyException {
		if (null == id || id.equals("")) {
			throw new MyException("0002", "id 不能为空");
		}
		Class clz = this.getClass();
		ParameterizedType type = (ParameterizedType) clz.getGenericSuperclass();
		Type[] types = type.getActualTypeArguments();
		cls = (Class<T>) types[0];
		T t = jpa.findByOne(cls, id);
		return t;
	}

}
