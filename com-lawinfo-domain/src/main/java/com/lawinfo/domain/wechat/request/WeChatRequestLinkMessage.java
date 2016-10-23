package com.lawinfo.domain.wechat.request;

import com.lawinfo.domain.wechat.request.WeChatBaseRequestMessage;

/**
 * Created by wangrongtao on 2016/10/22.
 */
public class WeChatRequestLinkMessage extends WeChatBaseRequestMessage {

    // 消息标题
    private String Title;
    // 消息描述
    private String Description;
    // 消息链接
    private String Url;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
