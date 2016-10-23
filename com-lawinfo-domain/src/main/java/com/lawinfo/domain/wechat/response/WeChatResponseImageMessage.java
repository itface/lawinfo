package com.lawinfo.domain.wechat.response;

/**
 * Created by wangrongtao on 2016/10/22.
 */
public class WeChatResponseImageMessage extends WeChatBaseResponseMessage {
    private long MediaId;

    public long getMediaId() {
        return MediaId;
    }

    public void setMediaId(long mediaId) {
        MediaId = mediaId;
    }


}
