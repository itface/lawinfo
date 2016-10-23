package com.lawinfo.service.wechat;

import com.lawinfo.domain.wechat.request.WeChatBaseRequestMessage;
import com.lawinfo.domain.wechat.request.WeChatCommonRequestMessage;
import com.lawinfo.domain.wechat.response.WeChatResponseTextMessage;

import javax.xml.bind.JAXBException;

/**
 * Created by wangrongtao on 2016/10/23.
 */
public interface WeChatProcessMessageService {

    public String processMessage(String msgXml) throws JAXBException;
}
