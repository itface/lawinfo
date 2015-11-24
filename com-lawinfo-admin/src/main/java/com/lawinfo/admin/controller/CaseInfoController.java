package com.lawinfo.admin.controller;

import com.lawinfo.admin.system.login.LoginInfo;
import com.lawinfo.domain.front.CaseInfo;
import com.lawinfo.domain.front.query.CaseInfoQuery;
import com.lawinfo.domain.org.Action;
import com.lawinfo.domain.org.User;
import com.lawinfo.domain.org.vo.ActionTreeVo;
import com.lawinfo.domain.org.vo.OrgVo;
import com.lawinfo.service.front.CaseInfoService;
import com.lawinfo.service.front.DeleteCaseService;
import com.lawinfo.service.org.ActionService;
import com.lawinfo.service.org.OrgService;
import com.lawinfo.service.org.UserService;
import com.lawinfo.service.org.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
    @Resource
    private OrgService orgService;
    @Resource
    private UserService userService;
    @Resource
    private DeleteCaseService deleteCaseService;


    @ResponseBody
    @RequestMapping("/add")
    public int save(HttpServletRequest request,CaseInfo caseInfo,BindingResult result)throws Exception{
        if (caseInfo != null) {
            String userid = LoginInfo.getUseridFromSession(request.getSession());
            User user = UserUtils.findByUserid(userid);
            if (user!=null) {
                String orpuserid = user.getName()+"["+userid+"]";
                caseInfo.setOptuserid(orpuserid);
            }
            int rows = caseInfoService.save(caseInfo);
            return rows;
        }
        return 0;
    }
    @ResponseBody
    @RequestMapping("/exelawyer/add")
    public int addExelawyer(HttpServletRequest request,long id,String exeajbh,String exelawyers,String exelawyerids)throws Exception{
        int rows = caseInfoService.updateExeLawyers(id,exeajbh,exelawyers,exelawyerids);
        return rows;
    }
    @ResponseBody
    @RequestMapping("/find")
    public List<CaseInfo> find(HttpServletRequest request,String userid,Integer currenttabtype)throws Exception {
        if (!StringUtils.isEmpty(userid)) {
            CaseInfoQuery caseInfoQuery = new CaseInfoQuery();
            caseInfoQuery.setCurrenttabtype(currenttabtype);
            List<CaseInfo> list = caseInfoService.findList(caseInfoQuery,userid);
            return list;
        }
        return null;
    }
    @ResponseBody
    @RequestMapping("/remove")
    public boolean remove(long caseinfoid)throws Exception{
        boolean flag = deleteCaseService.deleteByCaseinfoid(caseinfoid);
        return flag;
    }
    @ResponseBody
    @RequestMapping("/org/findcustomorgtree")
    public List<OrgVo> findcustomtree()throws Exception{
        List<OrgVo> list = orgService.findCustomTree();
        return list;
    }
    @ResponseBody
    @RequestMapping("/user/findcustomertree")
    public List<OrgVo> findcustomertree()throws Exception{
        List<OrgVo> list = userService.findCustomerTree();
        return list;
    }
    @ResponseBody
    @RequestMapping("/user/findlawyertree")
    public List<OrgVo> findlawyertree()throws Exception{
        List<OrgVo> list = userService.findLawyerTree();
        return list;
    }
}
