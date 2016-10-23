package com.lawinfo.domain.wechat.response;

/**
 * Created by wangrongtao on 2016/10/23.
 */
public enum WeChatResponseMessageTypeEnum {
    TEXT("text","文本"),
    MUSIC("music","音乐"),
    NEWS("news","图文");

    private String type;
    private String desc;

    WeChatResponseMessageTypeEnum(String type, String desc) {
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
