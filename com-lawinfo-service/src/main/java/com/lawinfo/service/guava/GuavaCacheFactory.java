package com.lawinfo.service.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.lawinfo.service.constant.WeChatInfo;
import com.lawinfo.service.wechat.utils.WechatUtils;
import net.sf.json.JSONObject;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangrongtao on 2016/10/25.
 */
public class GuavaCacheFactory {
    public static String ACCESS_TOKEN = "accessToken";
    // 没有使用CacheLoader，access_token的有效期是7200秒（两小时
    public static LoadingCache<String, String> tokenCache = CacheBuilder.newBuilder()
            .expireAfterWrite(6000, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                public String load(String key) {
                    JSONObject s = WechatUtils.httpRequest("https://api.weixin.qq.com/cgi-bin/token?" +
                            "grant_type=client_credential&appid="+ WeChatInfo.appId+"&secret="+WeChatInfo.appSecret,"GET",null);
                    return s.get("access_token").toString();
                }
            });

}
