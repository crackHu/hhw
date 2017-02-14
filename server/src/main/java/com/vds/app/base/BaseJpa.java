package com.vds.app.base;

import java.util.List;

import com.vds.app.exception.MyException;

public interface BaseJpa<T> {

	/**
	 * 记算总记录数
	 * 
	 * @Title: countRecord
	 * @Description: 根据指定条件查询指定表的总记录数
	 * @param e
	 *            :指定表对象
	 * @param conditionMap
	 *            :条件数组
	 * @return int：返回符合条件的总记录数
	 * @throws Exception
	 */
	public int countRecord(String hql, List<Object> objs) throws MyException;

	/**
	 * 
	 * @Title: select 分页查询
	 * @param hql
	 *            （传入的hql语句）
	 * @param indexBegin
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public List<?> select(String hql, int indexBegin, int size, List<Object> objs) throws MyException;

	public List<?> select(String hql) throws MyException;

	public List<?> select(String hql, List<Object> objs) throws MyException;

	public List<?> selectSql(String hql) throws MyException;

	public T save(T t) throws MyException;

	public void remove(T t) throws MyException;

	public void updateHql(String hql) throws MyException;

	public T findByOne(Class<T> t,String id) throws MyException;
	
}
