package com.lawinfo.service.wechat;

import java.util.concurrent.ExecutionException;

/**
 * Created by wangrongtao on 2016/11/16.
 */
public interface WeChatUserInfoService {
    public String getOpenId(String code) throws ExecutionException;
}
