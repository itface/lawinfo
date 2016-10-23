package com.lawinfo.domain.wechat.response;

/**
 * Created by wangrongtao on 2016/10/22.
 */
public class WeChatResponseMusicMessage extends WeChatBaseResponseMessage {

    private WeChatMusic weChatMusic;

    public WeChatMusic getWeChatMusic() {
        return weChatMusic;
    }

    public void setWeChatMusic(WeChatMusic weChatMusic) {
        this.weChatMusic = weChatMusic;
    }
}
