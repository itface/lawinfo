package com.lawinfo.service.wechat.impl;

import com.lawinfo.common.utils.XmlUtils;
import com.lawinfo.domain.wechat.event.WeChatMessageEventTypeEnum;
import com.lawinfo.domain.wechat.request.WeChatCommonRequestMessage;
import com.lawinfo.domain.wechat.request.WeChatRequestMessageTypeEnum;
import com.lawinfo.domain.wechat.response.WeChatBaseResponseMessage;
import com.lawinfo.domain.wechat.response.WeChatResponseMessageTypeEnum;
import com.lawinfo.domain.wechat.response.WeChatResponseTextMessage;
import com.lawinfo.service.wechat.WeChatProcessMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.xml.bind.JAXBException;
import java.util.Date;

/**
 * Created by wangrongtao on 2016/10/23.
 */
@Service
public class WeChatProcessMessageServiceImpl implements WeChatProcessMessageService {
    private static Logger logger = LoggerFactory.getLogger(WeChatProcessMessageServiceImpl.class);
    @Override
    public String processMessage(String msgXml) throws JAXBException {
        logger.info("msgxml:{0}",msgXml);
        if (StringUtils.isEmpty(msgXml)) {
            return null;
        }
        Class[] arr = new Class[]{WeChatCommonRequestMessage.class};
        WeChatCommonRequestMessage weChatCommonRequestMessage = (WeChatCommonRequestMessage)XmlUtils.xmlStr2Obj(msgXml,arr);
        WeChatResponseTextMessage weChatResponseTextMessage = new WeChatResponseTextMessage();
        // 回复文本消息
        weChatResponseTextMessage.setToUserName(weChatCommonRequestMessage.getFromUserName());
        weChatResponseTextMessage.setFromUserName(weChatCommonRequestMessage.getToUserName());
        weChatResponseTextMessage.setCreateTime(new Date().getTime());
        weChatResponseTextMessage.setMsgType(WeChatResponseMessageTypeEnum.TEXT.getType());
        weChatResponseTextMessage.setFuncFlag(0);
        String respContent = null;
        String msgType = weChatCommonRequestMessage.getMsgType();
        // 文本消息
        if (msgType.equals(WeChatRequestMessageTypeEnum.TEXT.getType())) {
            respContent = "您发送的是文本消息！";
        }
        // 图片消息
        else if (msgType.equals(WeChatRequestMessageTypeEnum.IMAGE.getType())) {
            respContent = "您发送的是图片消息！";
        }
        // 地理位置消息
        else if (msgType.equals(WeChatRequestMessageTypeEnum.LOCATION.getType())) {
            respContent = "您发送的是地理位置消息！";
        }
        // 链接消息
        else if (msgType.equals(WeChatRequestMessageTypeEnum.LINK.getType())) {
            respContent = "您发送的是链接消息！";
        }
        // 音频消息
        else if (msgType.equals(WeChatRequestMessageTypeEnum.VOICE.getType())) {
            respContent = "您发送的是音频消息！";
        }
        // 事件推送
        else if (msgType.equals(WeChatRequestMessageTypeEnum.EVENT.getType())) {
            // 事件类型
            String eventType = weChatCommonRequestMessage.getMsgType();
            // 订阅
            if (eventType.equals(WeChatMessageEventTypeEnum.SUBSCRIBE.getType())) {
                respContent = "谢谢您的关注！";
            }
            // 取消订阅
            else if (eventType.equals(WeChatMessageEventTypeEnum.SUBSCRIBE.getType())) {
                // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
            }
            // 自定义菜单点击事件
            else if (eventType.equals(WeChatMessageEventTypeEnum.LOCATION.getType())) {
                respContent = "您发送的是位置消息！";
            }else if (eventType.equals(WeChatMessageEventTypeEnum.SCAN.getType())) {
                respContent = "您发送的是二维码消息！";
            }else if (eventType.equals(WeChatMessageEventTypeEnum.CLICK.getType())) {
                respContent = "您发送的是点击菜单拉取消息！";
            }else if (eventType.equals(WeChatMessageEventTypeEnum.VIEW.getType())) {
                respContent = "您发送的是点击菜单跳转链接消息！";
            }
        }
        weChatResponseTextMessage.setContent(respContent);
        String respXml = XmlUtils.Obj2XmlStr(weChatResponseTextMessage);
        logger.info("respXml:{0}",respXml);
        return respXml;
    }

    public static void main(String[] args) throws JAXBException {
        WeChatResponseTextMessage weChatResponseTextMessage = new WeChatResponseTextMessage();
        // 回复文本消息
        weChatResponseTextMessage.setToUserName("aa");
        weChatResponseTextMessage.setFromUserName("bb");
        weChatResponseTextMessage.setCreateTime(new Date().getTime());
        weChatResponseTextMessage.setMsgType(WeChatResponseMessageTypeEnum.TEXT.getType());
        weChatResponseTextMessage.setFuncFlag(0);
        weChatResponseTextMessage.setContent("您发送的是点击菜单跳转链接消息");
        String xml = XmlUtils.Obj2XmlStr(weChatResponseTextMessage);
        System.out.println(xml);

        Class[] arr = new Class[]{WeChatBaseResponseMessage.class, WeChatResponseTextMessage.class};
        WeChatResponseTextMessage weChatResponseTextMessage2 = (WeChatResponseTextMessage)XmlUtils.xmlStr2Obj(xml,arr);
        System.out.println(weChatResponseTextMessage2.getContent());
    }
}
