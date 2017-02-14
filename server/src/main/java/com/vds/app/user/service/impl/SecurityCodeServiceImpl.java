package com.vds.app.user.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.vds.app.base.BaseServiceImpl;
import com.vds.app.exception.Msg;
import com.vds.app.exception.MyException;
import com.vds.app.user.jpa.SecurityCodeJpa;
import com.vds.app.user.model.SecurityCode;
import com.vds.app.user.service.SecurityCodeService;

@Service
public class SecurityCodeServiceImpl extends BaseServiceImpl<SecurityCode> implements SecurityCodeService{

	@Inject
	private SecurityCodeJpa securityCodeJpa;
	
	public Msg add(SecurityCode securityCode) throws MyException {
		if (securityCode.getScPhone() == null || securityCode.getScPhone().equals("")) {
			throw new MyException("0002", "参数为null");
		}
		try {
			SecurityCode securityCode2 = securityCodeJpa.findByScPhone(
					securityCode.getScPhone());
			if (securityCode2 != null) {
				securityCode.setScId(securityCode2.getScId());
			}
			Msg msg = super.add(securityCode);
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("0004", "发送验证码失败");
		}
	}

	@Override
	public Msg verificationPhoneCode(String phone, String code) throws MyException {
		if (phone == null || phone.equals("") || code == null || code.equals("")) {
			throw new MyException("0002", "参数为null");
		}
		SecurityCode securityCode = securityCodeJpa.findByScPhone(phone);
		if (securityCode == null) {
			throw new MyException("0006", "未发送验证码");
		}
		if (!securityCode.getScCode().equals(code)) {
			throw new MyException("0007", "验证码错误");
		}
		return Msg.MsgSuccess("验证通过");
	}
	
	
	
	
}
