package com.lawinfo.admin.weChatController;

import com.lawinfo.domain.wechat.WeChatAuth;
import com.lawinfo.service.guava.GuavaCacheFactory;
import com.lawinfo.service.util.WeChatInfo;
import com.lawinfo.service.wechat.WeChatMenuService;
import com.lawinfo.service.wechat.WeChatProcessMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by wangrongtao on 2016/10/22.
 */
@Controller
@RequestMapping("/wechat")
public class WeChatAuthController {

    @Resource
    private WeChatProcessMessageService weChatProcessMessageService;
    @Resource
    private WeChatMenuService weChatMenuService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseBody
    public String auth(WeChatAuth weChatAuth) throws NoSuchAlgorithmException {
        if (weChatAuth!=null&&weChatAuth.checkSignature(WeChatInfo.token)) {
            return weChatAuth.getEchostr();
        }
        return null;
    }
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public String sendData(WeChatAuth weChatAuth,HttpServletRequest request) throws Exception{
        if (weChatAuth!=null&&weChatAuth.checkSignature(WeChatInfo.token)) {
            return weChatProcessMessageService.processMessage(request);
        }
        return null;
    }
    /*@RequestMapping(value = "/createMenu")
    @ResponseBody
    public String createMenu() throws Exception{
        return weChatMenuService.createMenu(null);
    }
    @RequestMapping(value = "/test")
    @ResponseBody
    public String test() throws Exception{
        return GuavaCacheFactory.tokenCache.get(GuavaCacheFactory.ACCESS_TOKEN);
    }*/
}
