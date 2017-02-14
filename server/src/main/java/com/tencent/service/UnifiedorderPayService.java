package com.tencent.service;

import com.tencent.common.Configure;
import com.tencent.protocol.pay_protocol.UnifiedorderPayReqData;

public class UnifiedorderPayService extends BaseService{

	public UnifiedorderPayService()
			throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		super(Configure.UNIFIEDORDER_API);
		// TODO Auto-generated constructor stub
	}
	public String request(UnifiedorderPayReqData unifiedorderPayReqData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(unifiedorderPayReqData);

        return responseString;
    }
}
