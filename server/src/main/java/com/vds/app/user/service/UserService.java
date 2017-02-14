package com.vds.app.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vds.app.base.BaseService;
import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.user.model.User;
import com.vds.app.user.model.UserOther;

public interface UserService extends BaseService<User> {

	User findByUserNameAndUsType(String userName, String type) throws MyException;

	User findByUserNameAndUTypeAndPassword(String userName, String type, String password) throws MyException;

	/**
	 * 登录
	 * 
	 * @author Cay
	 * @data 2016年12月21日
	 * @param userName
	 * @param type
	 * @param password
	 * @return
	 * @throws MyException
	 */
	Msg login(String userName, String type, String password) throws MyException;

	/**
	 * 注册
	 * 
	 * @author Cay
	 * @data 2016年12月21日
	 * @param loginName
	 * @param password
	 * @param code
	 * @param userType
	 * @param loginType
	 * @return
	 * @throws MyException
	 */
	Msg register(String loginName, String password, String code, String userType, String loginType) throws MyException;

	User findByUsPhoneAndUsTypeAndUsIsRemoveByPassword(String usPhone, String password, String usType, Integer isRemove)
			throws MyException;

	User findByUsPhoneAndUsTypeAndUsIsRemove(String usPhone, String usType, Integer isRemove) throws MyException;

	User findByUsUsernameAndUsIsRemoveAndUsType(String userName, Integer isRemove, String usType) throws MyException;

	User findByUsUsernameAndUsIsRemoveAndUsTypeByPassword(String userName, Integer isRemove, String password,
			String uType) throws MyException;

	Object updateOtherLogin(String account, String openId, String loginType, String usType, String nikeName, String pic)
			throws MyException;

	/**
	 * 添加一个USer中所有的逻辑
	 * 
	 * @author Cay
	 * @data 2016年12月21日
	 * @param user
	 * @param loginType
	 * @param userOther
	 * @return
	 * @throws MyException
	 */
	Msg addUser(User user, String loginType, UserOther userOther) throws MyException;

	/**
	 * 登录-含登录类型
	 * 
	 * @author Cay
	 * @data 2016年12月21日
	 * @param loginName
	 * @param password
	 * @param usType
	 * @param loginType
	 * @return
	 * @throws MyException
	 */
	Msg login(String loginName, String password, String usType, String loginType) throws MyException;

	Msg update(User user) throws MyException;

	Page<User> findAll(Pageable pageable) throws MyException;

	Msg findUserAndCarByUId(User user) throws MyException;

}
