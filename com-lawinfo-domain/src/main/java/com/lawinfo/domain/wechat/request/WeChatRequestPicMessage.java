package com.lawinfo.domain.wechat.request;

import com.lawinfo.domain.wechat.request.WeChatBaseRequestMessage;

/**
 * Created by wangrongtao on 2016/10/22.
 */
public class WeChatRequestPicMessage extends WeChatBaseRequestMessage {
    // 图片链接
    private String PicUrl;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}
