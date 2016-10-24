package com.lawinfo.domain.wechat.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by wangrongtao on 2016/10/22.
 */
@XmlRootElement(name = "xml")
public class WeChatResponseTextMessage extends WeChatBaseResponseMessage {
    // 回复的消息内容
    private String Content;

    public String getContent() {
        return "<![CDATA["+Content+"]]>";
    }

    public void setContent(String content) {
        Content = content;
    }

}
