package com.lawinfo.admin.controller;

import com.lawinfo.domain.front.CaseInfo;
import com.lawinfo.domain.front.CaseProgress;
import com.lawinfo.service.front.CaseProgressService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangrongtao on 15/11/5.
 */
@Controller
@RequestMapping("/lawinfo/front/caseprogress")
public class CaseProgressController {
    @Resource
    private CaseProgressService caseProgressService;
    @RequestMapping("/add")
    public int index(HttpServletRequest request,CaseProgress caseProgress,BindingResult result)throws Exception{
        return caseProgressService.save(caseProgress);
    }
}
