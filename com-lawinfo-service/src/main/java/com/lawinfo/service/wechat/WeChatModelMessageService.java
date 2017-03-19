package com.lawinfo.service.wechat;

import com.lawinfo.domain.front.CaseProgressComment;
import com.lawinfo.domain.wechat.templetemsg.TempleteMsg;

import java.util.concurrent.ExecutionException;

/**
 * Created by wangrongtao on 2017/3/18.
 */
public interface WeChatModelMessageService {
    public String postModelMessage(TempleteMsg templeteMsg);
    public void postCreateCaseTempleteMsg(String  contactids,String sslawyerids,long caseId);
    public void postUpdateCaseTempleteMsg(CaseProgressComment caseProgressComment);
}
