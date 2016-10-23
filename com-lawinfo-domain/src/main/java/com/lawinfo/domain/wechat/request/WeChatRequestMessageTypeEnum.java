package com.lawinfo.domain.wechat.request;

/**
 * Created by wangrongtao on 2016/10/23.
 */
public enum WeChatRequestMessageTypeEnum {
    TEXT("text","文本"),
    IMAGE("image","图片"),
    LINK("link","链接"),
    LOCATION("location","地理位置"),
    VOICE("voice","音频"),
    EVENT("event","推送");

    private String type;
    private String desc;

    WeChatRequestMessageTypeEnum(String type, String desc) {
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
