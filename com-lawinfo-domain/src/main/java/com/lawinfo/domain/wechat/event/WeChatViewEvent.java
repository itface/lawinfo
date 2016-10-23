package com.lawinfo.domain.wechat.event;

/**
 * Created by wangrongtao on 2016/10/23.
 * 自定义菜单事件:点击菜单跳转链接时的事件推送,
 * 用户点击自定义菜单后，微信会把点击事件推送给开发者，请注意，点击菜单弹出子菜单，不会产生上报。
 */
public class WeChatViewEvent extends WeChatBaseEvent {
    /**
     * 事件KEY值，设置的跳转URL
     */
    private String EventKey;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}
