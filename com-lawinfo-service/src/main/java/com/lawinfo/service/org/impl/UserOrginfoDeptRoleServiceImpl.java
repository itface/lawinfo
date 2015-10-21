package com.lawinfo.service.org.impl;

import com.lawinfo.domain.org.Dept;
import com.lawinfo.domain.org.OrgInfo;
import com.lawinfo.domain.org.Role;
import com.lawinfo.domain.org.User;
import com.lawinfo.domain.org.vo.UserVo;
import com.lawinfo.service.org.*;
import com.lawinfo.service.org.utils.DeptUtils;
import com.lawinfo.service.org.utils.OrgInfoUtils;
import com.lawinfo.service.org.utils.RoleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangrongtao on 15/10/18.
 */
@Service
public class UserOrginfoDeptRoleServiceImpl implements UserOrginfoDeptRoleService{

    private static Logger logger = LoggerFactory.getLogger(UserOrginfoDeptRoleServiceImpl.class);

    @Resource
    private UserService userService;
    @Resource
    private DeptService deptService;
    @Resource
    private OrgInfoService orgInfoService;
    @Resource
    private RoleService roleService;

    @Override
    public List<UserVo> find() throws Exception {
        List<UserVo> userVos = new ArrayList<UserVo>();
        try {
            List<User> list = userService.findAll();
            if (!CollectionUtils.isEmpty(list)) {
                for (User user : list) {
                    UserVo userVo = new UserVo();
                    userVo.setId(user.getId());
                    userVo.setName(user.getName());
                    userVo.setOrgid(user.getOrgid());
                    userVo.setRoleids(user.getRoleids());
                    userVo.setDeptid(user.getDeptid());
                    userVo.setUserid(user.getUserid());
                    String deptname = DeptUtils.findById(user.getDeptid())==null?null:DeptUtils.findById(user.getDeptid()).getName();
                    userVo.setDeptname(deptname);
                    String orgname = OrgInfoUtils.findById(user.getOrgid())==null?null:DeptUtils.findById(user.getOrgid()).getName();
                    userVo.setOrgname(orgname);
                    String rolename = RoleUtils.findByRoleid(Integer.parseInt(user.getRoleids()))==null?null:RoleUtils.findByRoleid(Integer.parseInt(user.getRoleids())).getName();
                    userVo.setRolename(rolename);
                    userVos.add(userVo);
                }
            }
        } catch (Exception e) {
            logger.error("find exception", e);
        }
        return userVos;
    }
}
