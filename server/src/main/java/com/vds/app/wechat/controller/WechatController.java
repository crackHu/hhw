package com.vds.app.wechat.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vds.app.exception.MyException;
import com.vds.app.user.service.UserService;
import com.vds.app.util.CayUtil;
import com.vds.app.util.HttpRequest;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("v2/open/wechat")
public class WechatController {

	@Inject
	private UserService userService;

	@RequestMapping("getWechatUser")
	public Object getWechatUser(String code) throws MyException {

		String return_json = HttpRequest.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token",
				CayUtil.getWechatParamString(code));
		JSONObject json;
		String access_token;
		if (null == return_json || return_json.equals("")) {
			throw new MyException("9999", "微信授权异常");
		}
		try {
			json = JSONObject.fromObject(return_json);
			access_token = json.getString("access_token");
			if (access_token == null || access_token.equals("")) {
				throw new MyException("9999", "微信授权异常");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("9999", "微信授权异常");
		}
		String refresh_token = json.getString("refresh_token");
		String openid = json.getString("openid");
		String scope = json.getString("scope");

		String user_json = HttpRequest.sendGet("https://api.weixin.qq.com/sns/userinfo",
				CayUtil.getWechatParamUserInfo(access_token, openid));

		JSONObject userJson = JSONObject.fromObject(user_json);
		String nickname = userJson.getString("nickname");
		String sex = userJson.getString("sex");
		String province = userJson.getString("province");
		String city = userJson.getString("city");

		String country = userJson.getString("country");
		String headimgurl = userJson.getString("headimgurl");

		return userService.updateOtherLogin("", openid, "WECHAT", "UN", nickname, headimgurl);
	}

}
