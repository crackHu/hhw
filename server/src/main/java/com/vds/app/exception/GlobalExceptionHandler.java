package com.vds.app.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vds.app.util.CayUtil;

@ControllerAdvice
public class GlobalExceptionHandler {

	Logger log = Logger.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = MyException.class)
	@ResponseBody
	public Msg jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {

		log.error(CayUtil.getInterfaceMethod(req), e);

		return new Msg(e.getRetCd(), e.getMsgDes(), null);
	}

	@ExceptionHandler(value = RuntimeException.class)
	@ResponseBody
	public Msg jsonErrorHandler2(HttpServletRequest req, RuntimeException runtimeException) throws Exception {

		log.error(CayUtil.getInterfaceMethod(req), runtimeException);

		return new Msg("9999", runtimeException.getMessage(), null);
	}

}
