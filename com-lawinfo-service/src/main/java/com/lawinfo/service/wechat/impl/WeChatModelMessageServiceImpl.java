package com.lawinfo.service.wechat.impl;

import com.lawinfo.domain.front.CaseInfo;
import com.lawinfo.domain.front.CaseInfoUser;
import com.lawinfo.domain.front.CaseProgressComment;
import com.lawinfo.domain.front.query.CaseInfoUserQuery;
import com.lawinfo.domain.org.Role;
import com.lawinfo.domain.org.User;
import com.lawinfo.domain.org.UserRole;
import com.lawinfo.domain.wechat.templetemsg.TempleteMsg;
import com.lawinfo.service.constant.WeChatInfo;
import com.lawinfo.service.front.CaseInfoService;
import com.lawinfo.service.front.CaseInfoUserService;
import com.lawinfo.service.guava.GuavaCacheFactory;
import com.lawinfo.service.org.UserService;
import com.lawinfo.service.wechat.WeChatModelMessageService;
import com.lawinfo.service.wechat.utils.WechatUtils;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by wangrongtao on 2017/3/18.
 */
@Service
public class WeChatModelMessageServiceImpl implements WeChatModelMessageService{

    private static Logger logger = LoggerFactory.getLogger(WeChatModelMessageServiceImpl.class);
    @Resource
    private UserService userService;
    @Resource
    private CaseInfoUserService caseInfoUserService;
    @Resource
    private CaseInfoService caseInfoService;

    public   String postModelMessage(TempleteMsg templeteMsg) {
        try {
            return doPost(templeteMsg);
        } catch (Exception e) {
            logger.error("postModelMessage exception",e);
        }
        return null;
    }
    private   String doPost(TempleteMsg templeteMsg)throws Exception{
        String msg = "{" +
                "           \"touser\":\""+templeteMsg.getToUserOpenId()+"\"," +
                "           \"template_id\":\"e4yQmW7zAOKr0YDPTPckwQ5pTsCY3bMBN6bhnuCaahI\"," +
                "           \"url\":\""+getUrl(templeteMsg.getCaseId())+"\",            " +
                "           \"data\":{" +
                "                   \"first\": {" +
                "                       \"value\":\""+templeteMsg.getFirst()+"\",\"color\":\"#173177\"" +
                "                   }," +
                "                   \"keyword1\":{" +
                "                       \"value\":\""+templeteMsg.getKeyword1()+"\",\"color\":\"#173177\"" +
                "                   }," +
                "                   \"keyword2\": {" +
                "                       \"value\":\""+templeteMsg.getKeyword2()+"\",\"color\":\"#173177\"" +
                "                   }," +
                "                   \"keyword3\": {" +
                "                       \"value\":\""+templeteMsg.getKeyword3()+"\",\"color\":\"#173177\"" +
                "                   }," +
                "                   \"remark\":{" +
                "                       \"value\":\""+templeteMsg.getRemark()+"\"" +
                "                   }" +
                "           }" +
                "       }";
        String accessToken = GuavaCacheFactory.tokenCache.get(GuavaCacheFactory.ACCESS_TOKEN);
        JSONObject s = WechatUtils.httpRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" +accessToken,"POST",msg);
        return s.toString();
    }
    private  String getUrl(long caseId){
        String reDirectUrl = WeChatInfo.POST_TEMPLETE_MESSAGE_URL_BASE+"/"+caseId;
        return  "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ WeChatInfo.appId+"&redirect_uri="+reDirectUrl+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
    }
    private void postTempleteMsg(String  userId,long caseId,int progress) throws Exception {
        if (!StringUtils.isEmpty(userId)) {
            User user = userService.findByUserid(userId);
            if (user != null && !StringUtils.isEmpty(user.getWechatopenid())) {
                TempleteMsg templeteMsg = new TempleteMsg();
                templeteMsg.setToUserOpenId(user.getWechatopenid());
                templeteMsg.setCaseId(caseId);
                templeteMsg.setFirst("");
                if (progress < 0) {
                    templeteMsg.setKeyword1("您有新的案件");
                    templeteMsg.setKeyword2("新增案件");
                    templeteMsg.setKeyword3("开始办理");
                }else{
                    templeteMsg.setKeyword1("您的案件有了新进展");
                    if (progress<100) {
                        templeteMsg.setKeyword2("诉讼中");
                    }else if (progress>=100&&progress<9999) {
                        templeteMsg.setKeyword2("执行中");
                    }else if (progress>=9999) {
                        templeteMsg.setKeyword2("案件已归档");
                    }else{
                        templeteMsg.setKeyword2("");
                    }
                    templeteMsg.setKeyword3("进度更新");
                }
                templeteMsg.setRemark("");
                postModelMessage(templeteMsg);
            }
        }
    }
    public void postCreateCaseTempleteMsg(String  contactids,String sslawyerids,long caseId){
        try {
            Set<String> set = new HashSet<String>();
            if (!StringUtils.isEmpty(contactids)) {
                String[] arr = contactids.split(",");
                for (String userid : arr) {
                    set.add(userid);
                }
            }
            if (!StringUtils.isEmpty(sslawyerids)) {
                String[] arr = sslawyerids.split(",");
                for (String userid : arr) {
                    set.add(userid);
                }
            }
            List<UserRole> userRoles = userService.findManagerList();
            if (!CollectionUtils.isEmpty(userRoles)) {
                for (UserRole userRole : userRoles) {
                    set.add(userRole.getUserid());
                }
            }
            Iterator<String> it =  set.iterator();
            while (it.hasNext()) {
                String userid = it.next();
                postTempleteMsg(userid, caseId,-1);
            }
        } catch (Exception e) {
            logger.error("postCreateCaseTempleteMsg exception",e);
        }
    }
    public void postUpdateCaseTempleteMsg(CaseProgressComment caseProgressComment){
        try {
            Set<String> set = new HashSet<String>();
            long caseid = caseProgressComment.getCaseinfoid();
            CaseInfo caseInfo = caseInfoService.findById(caseid);
            if (caseInfo != null) {
                CaseInfoUserQuery caseInfoUserQuery = new CaseInfoUserQuery();
                caseInfoUserQuery.setCaseinfoid(caseid);
                List<CaseInfoUser> caseInfoUsers = caseInfoUserService.findList(caseInfoUserQuery);
                if (!CollectionUtils.isEmpty(caseInfoUsers)) {
                    for (CaseInfoUser caseInfoUser : caseInfoUsers) {
                        set.add(caseInfoUser.getUserid());
                    }
                }
                List<UserRole> userRoles = userService.findManagerList();
                if (!CollectionUtils.isEmpty(userRoles)) {
                    for (UserRole userRole : userRoles) {
                        set.add(userRole.getUserid());
                    }
                }
                Iterator<String> it =  set.iterator();
                while (it.hasNext()) {
                    String userid = it.next();
                    postTempleteMsg(userid,caseid,caseInfo.getStatus());
                }
            }
        } catch (Exception e) {
            logger.error("postUpdateCaseTempleteMsg exception",e);
        }
    }
}
