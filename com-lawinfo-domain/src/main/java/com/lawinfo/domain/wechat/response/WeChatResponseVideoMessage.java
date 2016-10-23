package com.lawinfo.domain.wechat.response;

/**
 * Created by wangrongtao on 2016/10/22.
 */
public class WeChatResponseVideoMessage extends WeChatBaseResponseMessage {

    private long MediaId;
    private String Title;
    private String Description;

    public long getMediaId() {
        return MediaId;
    }

    public void setMediaId(long mediaId) {
        MediaId = mediaId;
    }

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
}
