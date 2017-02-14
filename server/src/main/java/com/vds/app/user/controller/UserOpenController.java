package com.vds.app.user.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.MyException;
import com.vds.app.user.model.SecurityCode;
import com.vds.app.user.service.SecurityCodeService;
import com.vds.app.user.service.UserService;

@RestController
@RequestMapping("/v2/open/user")
public class UserOpenController {

	@Inject
	private UserService userService;

	@Inject
	private SecurityCodeService securityCodeService;
 
	/**
	 * ============================== start 验证码
	 * ======================================
	 */

	/**
	 * 获取验证码
	 * 
	 * @author only_U
	 * @date:2016年10月20日 下午5:20:15
	 * @param phone
	 *            手机号码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("postVerficationCode")
	public Object postVerficationCode(String phone) throws MyException {

		SecurityCode securityCode = SecurityCode.postFwSecurityCode(phone);
		return securityCodeService.add(securityCode);
	}

	/**
	 * 验证手机短信验证码
	 * 
	 * @author Cay
	 * @data 2016年12月16日
	 * @param phone
	 *            手机号码
	 * @param code
	 *            验证码
	 * @return
	 * @throws MyException
	 */
	@RequestMapping("verificationPhoneCode")
	public Object verificationPhoneCode(String phone, String code) throws MyException {
		return securityCodeService.verificationPhoneCode(phone, code);
	}

	/**
	 * ============================ end 验证码
	 * =====================================
	 */

	@RequestMapping(value = "login")
	public Object login(String loginName, String password, String loginType) throws MyException {

		return userService.login(loginName, loginType, password);
	}

	/**
	 * 注册
	 * 
	 * @author Cay
	 * @data 2016年12月21日
	 * @param loginName
	 *            登录的字段属性
	 * @param password
	 *            密码
	 * @param userType
	 *            用户类型
	 * @param loginType
	 *            登录类型
	 * @param code
	 *            验证码
	 * @return
	 * @throws MyException
	 */
	@RequestMapping("register")
	public Object register(String loginName, String password, String userType, String loginType, String code) throws MyException {

		return userService.register(loginName, password, code, userType, loginType);
	}

	/**
	 * 第三方登录
	 * 
	 * @author Cay
	 * @data 2016年12月21日
	 * @param openId
	 *            第三方的登录凭证
	 * @param loginType
	 *            登录类型(QQ or WECHAR)
	 * @param userType
	 *            用户类型(UN 普通用户)
	 * @param nikeName
	 *            昵称(第三方的)
	 * @param pic
	 *            头像(URl)
	 * @param account
	 *            第三方账号
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("otherLogin")
	public Object otherLogin(String openId, String loginType, String userType, String nikeName, String pic, String account) throws Exception {

		return userService.updateOtherLogin(account, openId, loginType, userType, nikeName, pic);
	}

	/**
	 * 
	 * @author Cay
	 * @data 2016年12月28日
	 * @param loginName
	 *            登录用户名
	 * @param password
	 *            登录密码
	 * @param userType
	 *            用户类型
	 * @param loginType
	 *            登录类型
	 * @return
	 * @throws MyException
	 */
	@RequestMapping("realLogin")
	public Object realLogin(String loginName, String password, String userType, String loginType) throws MyException {

		return userService.login(loginName, password, userType, loginType);
	}

}
