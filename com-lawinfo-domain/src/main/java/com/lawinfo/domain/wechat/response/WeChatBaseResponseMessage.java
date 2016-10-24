package com.lawinfo.domain.wechat.response;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by wangrongtao on 2016/10/22.
 */
@XmlRootElement(name = "xml")
public class WeChatBaseResponseMessage implements Serializable {
    // 开发者微信号
    private String ToUserName;
    // 发送方帐号（一个OpenID）
    private String FromUserName;
    // 消息创建时间 （整型）
    private long CreateTime;
    // 消息类型（text/image/location/link）
    private String MsgType;
    // 位0x0001被标志时，星标刚收到的消息
    private int FuncFlag;

    public String getToUserName() {
        return "<![CDATA["+ToUserName+"]]>";
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return "<![CDATA["+FromUserName+"]]>";
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return "<![CDATA["+MsgType+"]]>";
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public int getFuncFlag() {
        return FuncFlag;
    }

    public void setFuncFlag(int funcFlag) {
        FuncFlag = funcFlag;
    }
}
