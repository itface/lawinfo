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
        String createMenuUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";

        String buttonUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ WeChatInfo.appId+"&redirect_uri=http://112.74.74.91/login/mobile&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
        String buttonUrlbak = "http://112.74.74.91/login/mobile";
        String accessToken = GuavaCacheFactory.tokenCache.get(GuavaCacheFactory.ACCESS_TOKEN);
        /*Menu menu = new Menu();
        Button[] buttons = new Button[1];
        ViewButton button = new ViewButton();
        button.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WeChatInfo.appId+"&redirect_uri=http://112.74.74.91/login/mobile&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
        button.setName("管理系统");
        button.setType("view");
        buttons[0] = button;
        *//*String str = "{\n" +
                "    \"button\": [\n" +
                "        {\n" +
                "\t    \"name\": \"管理系统\",\n" +
                "\t    \"url\": \"http://112.74.74.91/login/mobile\",\n" +
                "\t    \"type\": \"view\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";*/
        String menustr = "{\"button\":[{\"name\":\"管理系统\",\"type\":\"view\",\"url\":\""+buttonUrlbak+"\"}]}";

        JSONObject s = WechatUtils.httpRequest("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" +accessToken,"POST",menustr);
        return s.toString();
    }
}
