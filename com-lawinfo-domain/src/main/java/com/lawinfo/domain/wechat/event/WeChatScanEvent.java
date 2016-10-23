package com.lawinfo.domain.wechat.event;

/**
 * Created by wangrongtao on 2016/10/23.
 * 扫描带参数二维码事件,用户已关注时的事件推送:事件类型，SCAN
 * 如果用户已经关注公众号，则微信会将带场景值扫描事件推送给开发者。
 */
public class WeChatScanEvent extends WeChatBaseEvent {

    /**
     *事件KEY值，qrscene_为前缀，后面为二维码的参数值
     */
    private String EventKey;
    /**
     *二维码的ticket，可用来换取二维码图片
     */
    private String Ticket;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }
}
