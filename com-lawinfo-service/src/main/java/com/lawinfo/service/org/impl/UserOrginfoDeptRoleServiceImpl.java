package com.lawinfo.service.org.impl;

import com.lawinfo.domain.org.Dept;
import com.lawinfo.domain.org.OrgInfo;
import com.lawinfo.domain.org.Role;
import com.lawinfo.domain.org.User;
import com.lawinfo.domain.org.vo.UserVo;
import com.lawinfo.service.org.*;
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
                List<OrgInfo> orgInfos = orgInfoService.findAll();
                Map<Long, String> orginfoMap = new HashMap<Long, String>();
                if (!CollectionUtils.isEmpty(orgInfos)) {
                    for (OrgInfo orgInfo : orgInfos) {
                        orginfoMap.put(orgInfo.getId(), orgInfo.getName());
                    }
                }
                List<Dept> depts = deptService.findAll();
                Map<Long, String> deptMap = new HashMap<Long, String>();
                if (!CollectionUtils.isEmpty(depts)) {
                    for (Dept dept : depts) {
                        deptMap.put(dept.getId(), dept.getName());
                    }
                }
                List<Role> roles = roleService.findAll();
                Map<String, String> roleMap = new HashMap<String, String>();
                if (!CollectionUtils.isEmpty(roles)) {
                    for (Role role : roles) {
                        roleMap.put(role.getRoleid()+"", role.getName());
                    }
                }
                for (User user : list) {
                    UserVo userVo = new UserVo();
                    userVo.setId(user.getId());
                    userVo.setName(user.getName());
                    userVo.setOrgid(user.getOrgid());
                    userVo.setRoleids(user.getRoleids());
                    userVo.setDeptid(user.getDeptid());
                    userVo.setUserid(user.getUserid());
                    userVo.setDeptname(deptMap.get(user.getDeptid()));
                    userVo.setOrgname(orginfoMap.get(user.getOrgid()));
                    userVo.setRolename(roleMap.get(user.getRoleids()));
                    userVos.add(userVo);
                }
            }
        } catch (Exception e) {
            logger.error("find exception", e);
        }
        return userVos;
    }
}
