package com.lawinfo.service.wechat.impl;

import com.lawinfo.domain.wechat.WeChatInfo;
import com.lawinfo.service.wechat.WeChatGetAccessTokenService;
import com.lawinfo.service.wechat.utils.WechatUtils;
import net.sf.json.JSONObject;

import javax.annotation.Resource;

/**
 * Created by wangrongtao on 2016/10/24.
 */
public class WeChatGetAccessTokenServiceImpl implements WeChatGetAccessTokenService {

    @Resource
    private WeChatInfo weChatInfo;

    @Override
    public String getAccessToken() {

        return null;
    }

    public static void main(String[] args) {
        JSONObject s = WechatUtils.httpRequest("https://api.weixin.qq.com/cgi-bin/token?" +
                "grant_type=client_credential&appid=appid&secret=secret","GET",null);
        System.out.println(s);
    }
}
