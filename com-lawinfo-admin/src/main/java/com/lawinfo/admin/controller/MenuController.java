package com.lawinfo.admin.controller;

import com.lawinfo.domain.org.Menu;
import com.lawinfo.domain.org.Org;
import com.lawinfo.domain.org.vo.MenuVo;
import com.lawinfo.domain.org.vo.OrgVo;
import com.lawinfo.service.org.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/18.
 */
@Controller
@RequestMapping("/lawinfo/admin/menu")
public class MenuController {


    @Resource
    private MenuService menuService;

    @ResponseBody
    @RequestMapping("/add")
    public int save(HttpServletRequest request,Menu menu,BindingResult result)throws Exception{
        if (menu != null) {
            int rows = menuService.save(menu);
            return rows;
        }
        return 0;
    }
    @ResponseBody
    @RequestMapping("/findtree")
    public List<MenuVo> findtree()throws Exception{
        List<MenuVo> list = menuService.findMenuTree();
        return list;
    }
    @ResponseBody
    @RequestMapping("/remove")
    public int remove(long id)throws Exception{
        int rows = menuService.deleteById(id);
        return rows;
    }
}
