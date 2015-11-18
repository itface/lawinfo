package com.lawinfo.service.org.impl;

import com.lawinfo.dao.org.RoleDao;
import com.lawinfo.domain.org.*;
import com.lawinfo.domain.org.query.RoleQuery;
import com.lawinfo.domain.org.vo.ActionTreeVo;
import com.lawinfo.domain.org.vo.RoleTreeVo;
import com.lawinfo.domain.org.vo.RoleVo;
import com.lawinfo.service.org.*;
import com.lawinfo.service.org.utils.RoleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/14.
 */
@Service
public class RoleServiceImpl implements RoleService {
    private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Resource
    private RoleDao roleDao;
    @Resource
    private RoleMenuService roleMenuService;
    @Resource
    private MenuService menuService;
    @Resource
    private ActionService actionService;
    @Resource
    private RoleActionService roleActionService;
    @Resource
    private UserRoleService userRoleService;

    @Override
    public void initCache() throws Exception {
        try {
            RoleUtils.init();
            List<Role> roleList= this.findAllFromDb();
            if (!CollectionUtils.isEmpty(roleList)) {
                for (Role role : roleList) {
                    RoleUtils.add(role);
                }
            }
        } catch (Exception e) {
            logger.error("initcache exception",e);
            throw e;
        }
    }

    @Override
    public List<RoleVo> findAllVo() throws Exception {
        try {
            List<Role> list = this.findAll();
            if (!CollectionUtils.isEmpty(list)) {
                List<RoleVo> roleVos = new ArrayList<RoleVo>();
                for (Role role : list) {
                    RoleVo roleVo = new RoleVo();
                    roleVo.setId(role.getId());
                    roleVo.setName(role.getName());
                    List<RoleMenu> roleMenuList = roleMenuService.findByRoleid(role.getId());
                    if (!CollectionUtils.isEmpty(roleMenuList)) {
                        Long[] menuids = new Long[roleMenuList.size()];
                        int index = 0;
                        for (RoleMenu roleMenu : roleMenuList) {
                            menuids[index++] = roleMenu.getMenuid();
                        }
                        List<Menu> menus = menuService.findByIds(menuids);
                        StringBuilder sb = new StringBuilder();
                        if (!CollectionUtils.isEmpty(menus)) {
                            for (Menu menu : menus) {
                                sb.append(menu.getName()).append(",");
                            }
                        }
                        roleVo.setMenuname(sb.substring(0, sb.length()-1));
                    }
                    List<RoleAction> roleActionList = roleActionService.findByRoleid(role.getId());
                    if (!CollectionUtils.isEmpty(roleActionList)) {
                        Long[] actionids = new Long[roleActionList.size()];
                        int index = 0;
                        for (RoleAction roleAction : roleActionList) {
                            actionids[index++] = roleAction.getActionid();
                        }
                        List<Action> actions = actionService.findByIds(actionids);
                        StringBuilder sb = new StringBuilder();
                        if (!CollectionUtils.isEmpty(actions)) {
                            for (Action action : actions) {
                                sb.append(action.getName()).append(",");
                            }
                        }
                        roleVo.setActionname(sb.substring(0, sb.length() - 1));
                    }
                    roleVos.add(roleVo);
                }
                return roleVos;
            }
        } catch (Exception e) {
            logger.error("findAllVo error",e);
            throw e;
        }
        return null;
    }

    @Override
    public List<Role> findAll() throws Exception{
        List<Role> list = null;
        try {
            list = RoleUtils.findAll();
            if (CollectionUtils.isEmpty(list)) {
                list = findAllFromDb();
            }
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return list;
    }

    @Override
    public List<Role> findAllFromDb() throws Exception {
        List<Role> list = null;
        try {
            list = roleDao.findAll();
        } catch (Exception e) {
            logger.error("findAllFromDb error",e);
            throw e;
        }
        return list;
    }

    @Override
    @Transactional
    public int save(String name,String menuids,String actionids)throws Exception {
        int effectrows = 0;
        try {
            if (!StringUtils.isEmpty(name)) {
                Role role = new Role();
                role.initBaseDomain();
                role.setName(name);
                effectrows = roleDao.save(role);
                if (effectrows==1) {
                    if (!StringUtils.isEmpty(menuids)) {
                        String[] menuArr = menuids.split(",");
                        for (int i = 0; i < menuArr.length; i++) {
                            RoleMenu roleMenu = new RoleMenu();
                            roleMenu.setMenuid(Long.parseLong(menuArr[i]));
                            roleMenu.setRoleid(role.getId());
                            roleMenuService.save(roleMenu);
                        }
                    }
                    if (!StringUtils.isEmpty(actionids)) {
                        String[] actionArr = actionids.split(",");
                        for (int i = 0; i < actionArr.length; i++) {
                            RoleAction roleAction = new RoleAction();
                            roleAction.setActionid(Long.parseLong(actionArr[i]));
                            roleAction.setRoleid(role.getId());
                            roleActionService.save(roleAction);
                        }
                    }
                }
                logger.info("save success,effectrows:"+effectrows+","+role.getName());
            }
        } catch (Exception e) {
            logger.error("save error,name:"+name,e);
            throw e;
        }
        return effectrows;
    }

    @Override
    public Role findById(long id)throws Exception {
        logger.info("findById begin,id:"+id);
        Role role = null;
        try {
            role = RoleUtils.findByRoleid(id);
            if (role==null) {
                role = roleDao.findById(id);
            }
        } catch (Exception e) {
            logger.error("findById error,id=" + id, e);
            throw e;
        }
        return role;
    }

    @Override
    public List<Role> findList(RoleQuery roleQuery)throws Exception {
        logger.info("findList begin,roleQuery=" + roleQuery == null ? "null" : roleQuery.toLogString());
        List<Role> list = null;
        try {
            list = roleDao.findList(roleQuery);
        } catch (Exception e) {
            logger.error("findList error,roleQuery=" + roleQuery==null?"null":roleQuery.toLogString(), e);
            throw e;
        }
        return list;
    }

    @Override
    public List<Role> findListByPage(RoleQuery roleQuery)throws Exception {
        logger.info("findListByPage begin,roleQuery=" + roleQuery==null?"null":roleQuery.toLogString());
        List<Role> list = null;
        try {
            list = roleDao.findListByPage(roleQuery);
        } catch (Exception e) {
            logger.error("findListByPage error,RoleQuery=" + roleQuery==null?"null":roleQuery.toLogString(), e);
            throw e;
        }
        return list;
    }

    @Override
    public int count(RoleQuery roleQuery)throws Exception {
        logger.info("count begin,roleQuery=" + roleQuery==null?"null":roleQuery.toLogString());
        int effectrows = 0;
        try {
            effectrows = roleDao.count(roleQuery);
        } catch (Exception e) {
            logger.error("count error,roleQuery=" + roleQuery==null?"null":roleQuery.toLogString(), e);
            throw e;
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int deleteById(long id)throws Exception {
        logger.info("deleteById begin,id=" + id);
        int effectrows = 0;
        try {
            if (id >0) {
                roleMenuService.deleteByRoleid(id);
                roleActionService.deleteByRoleid(id);
                userRoleService.deleteByRoleid(id);
                effectrows = roleDao.deleteById(id);
            }
        } catch (Exception e) {
            logger.error("deleteById error,id=" + id, e);
            throw e;
        }
        return effectrows;
    }

    @Override
    public List<RoleTreeVo> findAllTree() throws Exception {
        try {
            List<Role> list = findAll();
            if (!CollectionUtils.isEmpty(list)) {
                List<RoleTreeVo> roleTreeVos = new ArrayList<RoleTreeVo>();
                RoleTreeVo root = new RoleTreeVo();
                root.setId(0l);
                root.setText("角色");
                List<RoleTreeVo> roleVoList2 = new ArrayList<RoleTreeVo>();
                for (Role role : list) {
                    RoleTreeVo roleTreeVo = new RoleTreeVo();
                    roleTreeVo.setId(role.getId());
                    roleTreeVo.setText(role.getName());
                    roleVoList2.add(roleTreeVo);
                }
                root.setNodes(roleVoList2);
                roleTreeVos.add(root);
                return roleTreeVos;
            }
        } catch (Exception e) {
            logger.error("findAllTree error",e);
            throw e;
        }
        return null;
    }
}
