package com.lawinfo.service.wechat.impl;

import com.lawinfo.service.constant.WeChatInfo;
import com.lawinfo.service.guava.GuavaCacheFactory;
import com.lawinfo.service.wechat.WeChatUserInfoService;
import com.lawinfo.service.wechat.utils.WechatUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

/**
 * Created by wangrongtao on 2016/11/16.
 */
@Service
public class WeChatUserInfoServiceImpl implements WeChatUserInfoService {
    @Override
    public String getOpenId(String code) throws ExecutionException {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ WeChatInfo.appId+"&secret="+WeChatInfo.appSecret+"&code="+code+"&grant_type=authorization_code";
        String accessToken = GuavaCacheFactory.tokenCache.get(GuavaCacheFactory.ACCESS_TOKEN);
        JSONObject s = WechatUtils.httpRequest(url, "GET", accessToken);
        if (s != null) {
            return s.get("openid")==null?null:s.get("openid").toString();
        }
        return null;
    }
}
