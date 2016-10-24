package com.lawinfo.service.wechat.impl;

import com.lawinfo.common.utils.XmlUtils;
import com.lawinfo.domain.wechat.event.WeChatMessageEventTypeEnum;
import com.lawinfo.domain.wechat.request.WeChatCommonRequestMessage;
import com.lawinfo.domain.wechat.request.WeChatRequestMessageTypeEnum;
import com.lawinfo.domain.wechat.response.WeChatBaseResponseMessage;
import com.lawinfo.domain.wechat.response.WeChatResponseMessageTypeEnum;
import com.lawinfo.domain.wechat.response.WeChatResponseTextMessage;
import com.lawinfo.service.wechat.WeChatProcessMessageService;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wangrongtao on 2016/10/23.
 */
@Service
public class WeChatProcessMessageServiceImpl implements WeChatProcessMessageService {
    private static Logger logger = LoggerFactory.getLogger(WeChatProcessMessageServiceImpl.class);
    @Override
    public String processMessage(HttpServletRequest request) throws Exception {
        Map<String, String> map = parseXml(request);
       /* Class[] arr = new Class[]{WeChatCommonRequestMessage.class};
        WeChatCommonRequestMessage weChatCommonRequestMessage = (WeChatCommonRequestMessage)XmlUtils.xmlStr2Obj(msgXml,arr);
        WeChatResponseTextMessage weChatResponseTextMessage = new WeChatResponseTextMessage();
        // 回复文本消息
        weChatResponseTextMessage.setToUserName(map.get("FromUserName"));
        weChatResponseTextMessage.setFromUserName(map.get("ToUserName"));
        weChatResponseTextMessage.setCreateTime(new Date().getTime());
        weChatResponseTextMessage.setMsgType(WeChatResponseMessageTypeEnum.TEXT.getType());
        weChatResponseTextMessage.setFuncFlag(0);*/
        String respContent = null;
        String msgType = map.get("MsgType");
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
            String eventType = map.get("Event");
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
        String xml = "<xml>\n" +
                "<ToUserName><![CDATA["+map.get("FromUserName")+"]]></ToUserName>\n" +
                "<FromUserName><![CDATA["+map.get("ToUserName")+"]]></FromUserName>\n" +
                "<CreateTime>"+new Date().getTime()+"</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA["+respContent+"]]></Content>\n" +
                "</xml>\n";
        return xml;
    }
    /**
     * 解析微信发来的请求（XML）
     *
     * @param request 封装了请求信息的HttpServletRequest对象
     * @return map 解析结果
     * @throws Exception
     */
    public Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
            logger.info("messageElement:"+e.getName()+":"+e.getText());
        }
        // 释放资源
        inputStream.close();
        return map;
    }
    public static void main(String[] args) throws Exception {
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
