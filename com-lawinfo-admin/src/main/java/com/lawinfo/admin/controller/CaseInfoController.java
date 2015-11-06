package com.lawinfo.admin.controller;

import com.lawinfo.domain.front.CaseInfo;
import com.lawinfo.domain.front.query.CaseInfoQuery;
import com.lawinfo.domain.org.Action;
import com.lawinfo.domain.org.vo.ActionTreeVo;
import com.lawinfo.service.front.CaseInfoService;
import com.lawinfo.service.org.ActionService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/17.
 */
@Controller
@RequestMapping("/lawinfo/front/caseinfo")
public class CaseInfoController {

    @Resource
    private CaseInfoService caseInfoService;


    @ResponseBody
    @RequestMapping("/add")
    public int save(HttpServletRequest request,CaseInfo caseInfo,BindingResult result)throws Exception{
        if (caseInfo != null) {
            int rows = caseInfoService.save(caseInfo);
            return rows;
        }
        return 0;
    }
    @ResponseBody
    @RequestMapping("/find")
    public List<CaseInfo> find(CaseInfoQuery caseInfoQuery)throws Exception {
        List<CaseInfo> list = caseInfoService.findList(caseInfoQuery);
        return list;
    }
    @ResponseBody
    @RequestMapping("/remove")
    public int remove(long id)throws Exception{
        int rows = caseInfoService.deleteById(id);
        return rows;
    }
}
