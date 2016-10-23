package com.lawinfo.domain.wechat.event;

/**
 * Created by wangrongtao on 2016/10/23.
 */
public enum WeChatMessageEventTypeEnum {
    SUBSCRIBE("subscribe","用户未关注时，进行关注后的事件推送"),
    UNSUBSCRIBE("unsubscribe","unsubscribe(取消订阅)"),
    SCAN("SCAN","用户已关注时的事件推送"),
    LOCATION("LOCATION","上报地理位置事件"),
    VIEW("VIEW","点击菜单跳转链接时的事件推送"),
    CLICK("CLICK","点击菜单拉取消息时的事件推送");

    private String type;
    private String desc;

    WeChatMessageEventTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
