package com.lawinfo.domain.wechat.event;

/**
 * Created by wangrongtao on 2016/10/23.
 * 扫描带参数二维码事件,用户未关注时，进行关注后的事件推送:事件类型，subscribe
 * 如果用户还未关注公众号，则用户可以关注公众号，关注后微信会将带场景值关注事件推送给开发者。
 */
public class WeChatQrsceneEvent extends WeChatBaseEvent {

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
