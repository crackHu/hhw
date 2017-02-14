package com.vds.app.user.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vds.app.base.BaseServiceImpl;
import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.other.jpa.OtherCarJpa;
import com.vds.app.user.jpa.SecurityCodeJpa;
import com.vds.app.user.jpa.TokenJpa;
import com.vds.app.user.jpa.UserJpa;
import com.vds.app.user.jpa.UserOtherJpa;
import com.vds.app.user.model.SecurityCode;
import com.vds.app.user.model.User;
import com.vds.app.user.model.UserOther;
import com.vds.app.user.model.UserToken;
import com.vds.app.user.service.TokenService;
import com.vds.app.user.service.UserService;
import com.vds.app.user.util.LoginEnum;
import com.vds.app.user.util.UserEnum;
import com.vds.app.util.MD5Util;

@Transactional(rollbackOn = Exception.class)
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Inject
	private UserJpa userJpa;

	@Inject
	private SecurityCodeJpa securityCodeJpa;

	@Inject
	private TokenJpa tokenJpa;

	@Inject
	private UserOtherJpa userOtherJpa;

	@Inject
	private TokenService tokenService;

	@Inject
	private OtherCarJpa otherCarJpa;

	@Override
	public User findByUserNameAndUTypeAndPassword(String userName, String type, String password) throws MyException {
		User user = findByUserNameAndUsType(userName, type);
		if (user == null) {
			throw new MyException("0001", "没有该账号");
		}
		if (null == password || password.equals("")) {
			throw new MyException("0004", "密码不能为空");
		}
		if (!user.getUsPassword().equals(MD5Util.md5(password))) {
			return null;
		}
		return user;
	}

	@Override
	public Msg login(String userName, String type, String password) throws MyException {
		User user = findByUserNameAndUTypeAndPassword(userName, type, password);
		if (user == null) {
			throw new MyException("0003", "密码错误");
		}
		return Msg.MsgSuccess(user);
	}

	@Transactional(rollbackOn = Exception.class)
	public Msg register(String loginName, String password, String code, String userType, String loginType)
			throws MyException {
		User user = null;
		if (null == loginType || loginType.equalsIgnoreCase(LoginEnum.PHONE.getValue())) {
			user = this.findByUsPhoneAndUsTypeAndUsIsRemove(loginName, UserEnum.getCodeByValue(userType).getCode(), 0);
		}
		if (loginType.equalsIgnoreCase(LoginEnum.NAME.getValue())) {
			user = this.findByUsUsernameAndUsIsRemoveAndUsType(loginName, 0,
					UserEnum.getCodeByValue(userType).getCode());
		}
		if (user != null) {
			throw new MyException("0005", "用户已存在");
		} else {
			if (null == loginType || loginType.equalsIgnoreCase(LoginEnum.PHONE.getValue())) {
				SecurityCode securityCode = securityCodeJpa.findByScPhone(loginName);
				if (securityCode == null) {
					throw new MyException("0006", "未发验证码");
				}
				if (securityCode.getScExpireTime().after(new Date())) {
					if (!code.equals(securityCode.getScCode())) {
						throw new MyException("0007", "验证码错误");
					}
				} else {
					throw new MyException("0008", "验证码超时");
				}
			} else if (loginType.equalsIgnoreCase(LoginEnum.NAME.getValue())) {

			} else {
				throw new MyException("0009", "没有该注册方式");
			}

			user = User.getRegisterUser(loginName, password, userType, loginType);

			return this.addUser(user, loginType, null);
		}

	}

	@Transactional(rollbackOn = Exception.class)
	public Msg addUser(User user, String loginType, UserOther userOther) throws MyException {
		Map<String, Object> map = new HashMap<String, Object>();
		user.setUsPassword(MD5Util.md5(user.getUsPassword()));
		userJpa.save(user);// 添加用户信息

		if (LoginEnum.isOtherLogin(loginType)) {
			userOther.setUoUsId(user.getUsId());
			userOtherJpa.save(userOther);// 添加用户其他信息
		} else if (LoginEnum.isDefaultLogin(loginType)) {

		} else {
			throw new MyException("0009", "没有该登陆方式");
		}

		UserToken tk = UserToken.getTokenByUser(user);
		tokenJpa.save(tk);// 添加用户token

		map.put("token", tk.getTkTokenContent());

		return Msg.MsgSuccess(map);
	}

	@Override
	public User findByUserNameAndUsType(String userName, String type) throws MyException {
		return userJpa.findByUsUserNameAndUsType(userName, type);
	}

	@Override
	public User findByUsPhoneAndUsTypeAndUsIsRemoveByPassword(String usPhone, String password, String usType,
			Integer isRemove) throws MyException {
		if (password == null || password.equals("")) {
			throw new MyException("0002", "参数为null");
		}
		User user = this.findByUsPhoneAndUsTypeAndUsIsRemove(usPhone, usType, isRemove);
		if (null == user) {
			throw new MyException("0004", "用户名不存在");
		}
		if (!user.getUsPassword().equals(MD5Util.md5(password))) {
			throw new MyException("0003", "登陆密码错误");
		}
		return user;
	}

	@Override
	public User findByUsPhoneAndUsTypeAndUsIsRemove(String usPhone, String usType, Integer isRemove)
			throws MyException {
		if (usType == null || usPhone == null || usPhone.equals("") || usType.equals("")) {
			throw new MyException("0002", "参数为null");
		}
		User u = userJpa.findByUsPhoneAndUsTypeAndUsIsRemove(usPhone, usType, isRemove);
		return u;
	}

	@Override
	public User findByUsUsernameAndUsIsRemoveAndUsType(String userName, Integer isRemove, String usType)
			throws MyException {
		if (usType == null || userName == null || userName.equals("") || usType.equals("")) {
			throw new MyException("0002", "参数为null");
		}
		return userJpa.findByUsUserNameAndUsTypeAndUsIsRemove(userName, usType, isRemove);
	}

	@Override
	public User findByUsUsernameAndUsIsRemoveAndUsTypeByPassword(String userName, Integer isRemove, String password,
			String uType) throws MyException {
		if (password == null || password.equals("")) {
			throw new MyException("0002", "参数为null");
		}
		User user = this.findByUsUsernameAndUsIsRemoveAndUsType(userName, isRemove, uType);
		if (null == user) {
			throw new MyException("0004", "用户名不存在");
		}
		if (!user.getUsPassword().equals(MD5Util.md5(password))) {
			throw new MyException("0003", "登陆密码错误");
		}
		return user;
	}

	@Override
	public Object updateOtherLogin(String account, String openId, String loginType, String uType, String nikeName,
			String pic) throws MyException {
		Map<String, Object> map = new HashMap<String, Object>();

		if (openId == null || openId.equals("") || loginType == null || loginType.equals("")) {
			throw new MyException("0002", "参数为null");
		}
		if (!LoginEnum.isOtherLogin(loginType)) {
			throw new MyException("0009", "没有该登陆方式");
		}
		UserOther userOther = userOtherJpa.findByUoOpenIdAndUoType(openId, UserEnum.getCodeByValue(uType).getCode());
		if (null == userOther) {
			User user = User.getTheThirdRegister(openId, nikeName, uType, pic);
			userOther = new UserOther(user.getUsId(), openId, uType, account);
			return addUser(user, loginType, userOther);
		} else {
			UserToken token = tokenService.updateToken(userOther.getUoUsId());
			User user = User.getTheThirdRegister(openId, nikeName, uType, pic);
			user.setUsId(userOther.getUoUsId());
			userJpa.save(user);
			map.put("token", token.getTkTokenContent());
			return Msg.MsgSuccess(map);
		}
	}

	@Override
	public Msg login(String loginName, String password, String usType, String loginType) throws MyException {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = null;
		if (loginType.equalsIgnoreCase(LoginEnum.PHONE.getValue())) {

			user = this.findByUsPhoneAndUsTypeAndUsIsRemoveByPassword(loginName, password, usType, 0);

		} else if (loginType.equalsIgnoreCase(LoginEnum.NAME.getValue())) {

			user = this.findByUsUsernameAndUsIsRemoveAndUsTypeByPassword(loginName, 0, password,
					LoginEnum.NAME.getCode());

		} else {
			throw new MyException("0009", "没有该登陆方式 loginType错误");
		}

		userJpa.save(user);
		UserToken tk = tokenService.updateToken(user.getUsId());
		map.put("token", tk.getTkTokenContent());
		return Msg.MsgSuccess(map);
	}

	@Override
	public Msg update(User user) throws MyException {

		return null;
	}

	@Override
	public Page<User> findAll(Pageable pageable) throws MyException {
		// TODO Auto-generated method stub
		return userJpa.findAll(pageable);
	}

	@Override
	public Msg findUserAndCarByUId(User user) throws MyException {
		// TODO Auto-generated method stub

		user.setCars(otherCarJpa.findByOtCarUsId(user.getUsId()));

		return Msg.MsgSuccess(user);
	}

}