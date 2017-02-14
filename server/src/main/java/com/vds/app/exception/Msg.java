package com.vds.app.exception;

import java.io.Serializable;

import com.vds.app.util.ConstantMsg;

/**
 * 消息返回类
 * 
 * @author Cay
 * 
 */
public class Msg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3270665804855015222L;
	private String code;// 编号
	private String message;// 消息
	private Object data;// 返回值

	/**
	 * 消息构造方法
	 * 
	 * @param code
	 *            传入编号
	 * @param message
	 *            传入消息
	 * @param data
	 *            返回值
	 */
	public Msg(String code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	/**
	 * 操作成功返回的信息
	 * @author Cay
	 * @data 2016年12月28日
	 * @return
	 */
	public static Msg MsgSuccess() {

		return new Msg(ConstantMsg.MSG_SUCCESS, ConstantMsg.MSG_SUCCESS_CONTANT, "");
	}

	/**
	 * 操作成功返回的信息
	 * @author Cay
	 * @data 2016年12月28日
	 * @param data 返回前端的数据
	 * @return
	 */
	public static Msg MsgSuccess(Object data) {

		return new Msg(ConstantMsg.MSG_SUCCESS, ConstantMsg.MSG_SUCCESS_CONTANT, data);
	}

	/**
	 * 返回失败消息
	 * 
	 * @param data
	 * @return
	 */
	public static Msg MsgError(String data) {
		String msg = ConstantMsg.MSG_ERROR_CONTANT;
		if (null != data) {
			msg = data.toString();
		}
		return new Msg(ConstantMsg.MSG_ERROR, msg, null);
	}

	public static Msg MsgError(Object data) {
		String msg = ConstantMsg.MSG_ERROR_CONTANT;
		return new Msg(ConstantMsg.MSG_ERROR, msg, data);
	}

	public static Msg MsgError(MyException data) {
		String msg = ConstantMsg.MSG_ERROR_CONTANT;
		return new Msg(ConstantMsg.MSG_ERROR, msg, data.toString());
	}

	/**
	 * 返回未登录消息
	 * 
	 * @param data
	 * @return
	 */
	public static Msg msgNoLogin(Object data) {

		return new Msg(ConstantMsg.MSG_NOLOGIN, ConstantMsg.MSG_NOLOGIN_CONTANT, data);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
