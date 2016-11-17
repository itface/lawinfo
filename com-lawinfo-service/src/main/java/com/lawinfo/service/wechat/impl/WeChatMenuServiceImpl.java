package com.lawinfo.service.wechat.impl;

import com.lawinfo.service.constant.WeChatInfo;
import com.lawinfo.service.guava.GuavaCacheFactory;
import com.lawinfo.service.wechat.WeChatMenuService;
import com.lawinfo.service.wechat.utils.WechatUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

/**
 * Created by wangrongtao on 2016/10/26.
 */
@Service
public class WeChatMenuServiceImpl implements WeChatMenuService {
    @Override
    public String createMenu(String menuJson) throws ExecutionException {
        String buttonUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ WeChatInfo.appId+"&redirect_uri="+WeChatInfo.url+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
        String accessToken = GuavaCacheFactory.tokenCache.get(GuavaCacheFactory.ACCESS_TOKEN);
        String menustr = "{\"button\":[{\"name\":\"管理系统\",\"type\":\"view\",\"url\":\""+buttonUrl+"\"}]}";
        JSONObject s = WechatUtils.httpRequest("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" +accessToken,"POST",menustr);
        return s.toString();
    }
}
