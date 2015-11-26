package com.lawinfo.admin.controller;

import com.lawinfo.admin.system.login.LoginInfo;
import com.lawinfo.domain.front.CaseInfo;
import com.lawinfo.domain.front.CaseProgress;
import com.lawinfo.domain.front.CaseProgressComment;
import com.lawinfo.domain.front.vo.CaseProgressTreeVo;
import com.lawinfo.domain.front.vo.CaseProgressViewVo;
import com.lawinfo.domain.org.vo.OrgVo;
import com.lawinfo.service.front.CaseProgressCommentService;
import com.lawinfo.service.front.CaseProgressService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wangrongtao on 15/11/5.
 */
@Controller
@RequestMapping("/lawinfo/front/caseprogress")
public class CaseProgressController {
    @Resource
    private CaseProgressService caseProgressService;
    @Resource
    private CaseProgressCommentService caseProgressCommentService;
    @ResponseBody
    @RequestMapping("/save")
    public int index(HttpServletRequest request,CaseProgressComment caseProgressComment,BindingResult result)throws Exception{
        if (caseProgressComment!=null) {
            String userid = LoginInfo.getUseridFromSession(request.getSession());
            caseProgressComment.setOptuserid(userid);
            return caseProgressCommentService.save(caseProgressComment);
        }
        return 0;
    }
    @ResponseBody
    @RequestMapping("/findprogresstree")
    public CaseProgressViewVo findprogresstree(HttpServletRequest request,long caseinfoid)throws Exception{
        String userid = LoginInfo.getUseridFromSession(request.getSession());
        CaseProgressViewVo caseProgressViewVo = caseProgressService.findCaseProgressCommentVo(userid,caseinfoid);
        return caseProgressViewVo;
    }
}
