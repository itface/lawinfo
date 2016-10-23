package com.lawinfo.domain.wechat.request;

import com.lawinfo.domain.wechat.request.WeChatBaseRequestMessage;

/**
 * Created by wangrongtao on 2016/10/22.
 */
public class WeChatRequestVoiceMessage extends WeChatBaseRequestMessage {

    // 媒体ID
    private String MediaId;
    // 语音格式
    private String Format;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }
}
