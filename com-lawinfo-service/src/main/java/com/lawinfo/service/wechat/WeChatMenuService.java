package com.lawinfo.service.wechat;

import java.util.concurrent.ExecutionException;

/**
 * Created by wangrongtao on 2016/10/24.
 */
public interface WeChatMenuService {
    String createMenu(String menuJson) throws ExecutionException;
}
