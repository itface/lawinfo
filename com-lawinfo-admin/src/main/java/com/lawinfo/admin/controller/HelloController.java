package com.lawinfo.admin.controller;

/**
 * Created by wangrongtao on 15/10/12.
 */
//@Controller
public class HelloController {
    /*private String CASE_NODE = "[{\"id\":1,\"name\":\"银行委托恰谈\",\"index\":1,\"level\":1,\"level_name\":\"合约恰谈\",\"comments\":{1}},{\"id\":2,\"name\":\"合同已签订\",\"index\":2,\"level\":1,\"level_name\":\"合约恰谈\",\"comments\":{2}}]";
    @Resource
    private OrgService orgInfoService;
    @Resource
    private CaseProgressService caseProgressService;
    @RequestMapping("/admin/test")
    public String index() {
        return "index";
    }
    @RequestMapping("/error")
    public String error() {
        return "/error/error";
    }
    @RequestMapping("/find")
    public String find(ModelMap modelMap,OrgQuery orgInfoQuery, BindingResult result)throws Exception{
//        if (result.hasErrors())
        List<Org> list = orgInfoService.findList(orgInfoQuery);
        modelMap.put("list", list);
        return "index";
    }
    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request) {
        boolean flag = LoginInfo.addUseridToSession(request.getSession(),"itface");
        return flag?"success":"fail";
    }
    @RequestMapping("/query")
    @ResponseBody
    public String query() {
        return caseProgressService.findCaseProgressWithNodeInfo("cs001");
    }*/
}
