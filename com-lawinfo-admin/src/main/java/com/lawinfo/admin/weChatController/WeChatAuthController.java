package com.lawinfo.admin.weChatController;

import com.lawinfo.domain.wechat.WeChatAuth;
import com.lawinfo.domain.wechat.WeChatInfo;
import com.lawinfo.domain.wechat.request.WeChatCommonRequestMessage;
import com.lawinfo.domain.wechat.response.WeChatResponseTextMessage;
import com.lawinfo.service.wechat.WeChatProcessMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by wangrongtao on 2016/10/22.
 */
@Controller
@RequestMapping("/wechat")
public class WeChatAuthController {

    @Resource
    private WeChatInfo weChatInfo;

    @Resource
    private WeChatProcessMessageService weChatProcessMessageService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseBody
    public String auth(WeChatAuth weChatAuth) throws NoSuchAlgorithmException {
        if (weChatAuth!=null&&weChatAuth.checkSignature(weChatInfo.getToken())) {
            return weChatAuth.getEchostr();
        }
        return null;
    }
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public String sendData(WeChatAuth weChatAuth,HttpServletRequest request) throws Exception{
        if (weChatAuth!=null&&weChatAuth.checkSignature(weChatInfo.getToken())) {
            return weChatProcessMessageService.processMessage(request);
        }
        return null;
    }
}
