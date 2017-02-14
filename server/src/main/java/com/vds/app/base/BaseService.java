package com.vds.app.base;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;

public interface BaseService<T> {

	/**
	 * 新增
	 * 
	 * @param t
	 * @return
	 */
	Msg add(T t) throws MyException;

	/**
	 * 修改
	 * 
	 * @param t
	 * @return
	 */
	Msg modify(T t) throws MyException;

	/**
	 * 删除
	 * 
	 * @param t
	 * @return
	 */
	Msg remove(T t) throws MyException;

	/**
	 * 分页查询
	 * 
	 * @param pageable
	 * @return
	 */
	Msg findPage(Pageable pageable) throws MyException;

	Msg findPage(Map<String, Object> map, Pageable pageable) throws MyException;

	List<?> find(String hql) throws MyException;

	Msg findByOne(String id) throws MyException;

	T findOne(String id) throws MyException;


}
