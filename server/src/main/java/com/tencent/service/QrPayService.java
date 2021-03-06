package com.tencent.service;

import com.tencent.common.Configure;
import com.tencent.protocol.precreate.QrpayReqData;

/**
 * 二维码支付
 * @author ETYQ001
 *
 */
public class QrPayService extends BaseService{

	public QrPayService() throws ClassNotFoundException,
			IllegalAccessException, InstantiationException {
		super(Configure.QR_PAY_API);
		
	}
    /**
     * 请求支付查询服务
     * @param scanPayQueryReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的XML数据
     * @throws Exception
     */
    public String request(QrpayReqData data) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(data);

        return responseString;
    }
}
