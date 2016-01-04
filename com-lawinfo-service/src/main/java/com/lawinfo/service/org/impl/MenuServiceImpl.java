package com.lawinfo.service.org.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.lawinfo.dao.org.MenuDao;
import com.lawinfo.dao.org.RoleMenuDao;
import com.lawinfo.domain.common.EasyuiTree;
import com.lawinfo.domain.org.Menu;
import com.lawinfo.domain.org.RoleMenu;
import com.lawinfo.domain.org.User;
import com.lawinfo.domain.org.query.MenuQuery;
import com.lawinfo.domain.org.vo.MenuVo;
import com.lawinfo.service.org.MenuService;
import com.lawinfo.service.org.RoleMenuService;
import com.lawinfo.service.org.utils.MenuUtils;
import com.lawinfo.service.org.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by wangrongtao on 15/10/12.
 */
@Service
public class MenuServiceImpl implements MenuService {
    private static Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Resource
    private MenuDao menuDao;
    @Resource
    private RoleMenuService roleMenuService;

    @Override
    public void initCache() throws Exception {
        try {
            MenuUtils.init();
            List<Menu> list= this.findAllFromDb();
            if (!CollectionUtils.isEmpty(list)) {
                for (Menu menu : list) {
                    MenuUtils.add(menu);
                }
            }
        } catch (Exception e) {
            logger.error("initcache exception",e);
            throw e;
        }
    }

    /*public static void main(String[] args) {
        Org org = new Org();
        org.setId(1l);
        org.setName("明礼事务所");
        org.setParentorgid(0l);
        Org org2 = new Org();
        org2.setId(2l);
        org2.setName("诉讼部");
        org2.setParentorgid(1l);
        Org org3 = new Org();
        org3.setId(3l);
        org3.setName("执行部");
        org3.setParentorgid(1l);
        Org org4 = new Org();
        org4.setId(4l);
        org4.setName("执行主部律师");
        org4.setParentorgid(3l);
        Org org5 = new Org();
        org5.setId(5l);
        org5.setName("赣州银行");
        org5.setParentorgid(0l);
        Org org6 = new Org();
        org6.setId(6l);
        org6.setName("赣县支行");
        org6.setParentorgid(5l);
        Multimap<Long, Org> orgMultimap = ArrayListMultimap.create();
        orgMultimap.put(0l, org);
        orgMultimap.put(0l, org5);
        orgMultimap.put(1l, org2);
        orgMultimap.put(1l, org3);
        orgMultimap.put(3l, org4);
        orgMultimap.put(5l, org6);
        OrgVo vo = new OrgVo();
        vo.setId(0l);
        vo.setParentorgid(-1l);
        vo.setText("机构");
        OrgVo orgVo = buildOrgTree(orgMultimap,vo);
        System.out.println(JSON.toJSONString(orgVo));
    }*/
    private  void buildMenuTree(Multimap<Long, Menu> menuMultimap,MenuVo menuVo) {
        Collection<Menu> menus = menuMultimap.get(menuVo.getId());
        if (!CollectionUtils.isEmpty(menus)) {
            List<MenuVo> menuVoList = new ArrayList<MenuVo>();
            for (Menu menu : menus) {
                MenuVo menuVo1 = new MenuVo();
                menuVo1.setId(menu.getId());
                menuVo1.setText(menu.getName());
                menuVo1.setParentmenuid(menu.getId());
                buildMenuTree(menuMultimap, menuVo1);
                menuVoList.add(menuVo1);
            }
            Collections.sort(menuVoList);
            menuVo.setNodes(menuVoList);
        }
    }
    @Override
    public List<MenuVo> findMenuTree() throws Exception {
        List<Menu> list = findAll();
        if (!CollectionUtils.isEmpty(list)) {
            List<MenuVo> MenuVoList = new ArrayList<MenuVo>();
            Multimap<Long, Menu> menuMultimap = ArrayListMultimap.create();
            for (Menu menu : list) {
                menuMultimap.put(menu.getParentmenuid(), menu);
            }
            MenuVo root = new MenuVo();
            root.setId(0l);
            root.setParentmenuid(-1l);
            root.setText("菜单管理");
            Collection<Menu> Menus = menuMultimap.get(root.getId());
            List<MenuVo> MenuVoList2 = new ArrayList<MenuVo>();
            for (Menu Menu : Menus) {
                buildMenuTree(menuMultimap, root);
            }
            MenuVoList.add(root);
            return MenuVoList;
        }
        return null;
    }

    private void buildEuTree(EasyuiTree parent,Multimap<Long, Menu> menuMultimap){
        if (parent != null&&menuMultimap!=null) {
            long id = parent.getId();
            Collection<Menu> menuCollections = menuMultimap.get(id);
            if (!CollectionUtils.isEmpty(menuCollections)) {
                List<EasyuiTree> sons = parent.getChildren();
                for (Menu menu : menuCollections) {
                    EasyuiTree easyuiTree = new EasyuiTree();
                    easyuiTree.setId(menu.getId());
                    easyuiTree.setText(menu.getName());
                    easyuiTree.setParentid(menu.getParentmenuid());
                    easyuiTree.setState("open");
                    buildEuTree(easyuiTree,menuMultimap);
                    if (sons == null) {
                        sons = new ArrayList<EasyuiTree>();
                    }
                    sons.add(easyuiTree);
                }
                parent.setChildren(sons);
            }
        }
    }
    @Override
    public List<EasyuiTree> findMenuEuTree() throws Exception {
        List<Menu> list = findAll();
        if (!CollectionUtils.isEmpty(list)) {
            List<EasyuiTree> easyuiTrees = new ArrayList<EasyuiTree>();
            Multimap<Long, Menu> menuMultimap = ArrayListMultimap.create();
            for (Menu menu : list) {
                menuMultimap.put(menu.getParentmenuid(),menu);
            }
            Collection<Menu> menuCollection = menuMultimap.get(0l);
            for (Menu menu : menuCollection) {
                EasyuiTree easyuiTree = new EasyuiTree();
                easyuiTree.setId(menu.getId());
                easyuiTree.setText(menu.getName());
                easyuiTree.setParentid(menu.getParentmenuid());
                easyuiTree.setState("open");
                easyuiTrees.add(easyuiTree);
                buildEuTree(easyuiTree,menuMultimap);
            }
            return easyuiTrees;
        }
        return null;
    }
    /*@Override
    public List<EasyuiTree> findMenuEuTree(long parentid) throws Exception {
        List<Menu> list = findByParentid(parentid);
        if (!CollectionUtils.isEmpty(list)) {
            List<EasyuiTree> easyuiTrees = new ArrayList<EasyuiTree>();
            for (Menu menu : list) {
                EasyuiTree easyuiTree = new EasyuiTree();
                easyuiTree.setId(menu.getId());
                easyuiTree.setText(menu.getName());
                easyuiTree.setParentid(menu.getParentmenuid());
                easyuiTree.setState("closed");
                easyuiTrees.add(easyuiTree);
            }
            return easyuiTrees;
        }
        return null;
    }*/

    @Override
    public List<Menu> findAll() throws Exception{
        List<Menu> list = null;
        try {
            list = MenuUtils.findAll();
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
    public List<Menu> findAllFromDb() throws Exception {
        List<Menu> list = null;
        try {
            list = menuDao.findAll();
        } catch (Exception e) {
            logger.error("findAllFromDb error",e);
            throw e;
        }
        return list;
    }

    @Override
    public List<Menu> findByParentid(long parentid) throws Exception {
        try {
            List<Menu> list = MenuUtils.findByParentid(parentid);
            if (CollectionUtils.isEmpty(list)) {
                list = menuDao.findByParentmenuid(parentid);
            }
            return list;
        } catch (Exception e) {
            logger.error("findByParentid error",e);
            throw e;
        }
    }

    @Override
    @Transactional
    public int save(Menu menu)throws Exception {
        int effectrows = 0;
        try {
            if (menu !=null) {
                menu.initBaseDomain();
                effectrows = menuDao.save(menu);
                logger.info("save success,effectrows:"+effectrows+","+ menu.getName());
            }
        } catch (Exception e) {
            logger.error("findAll error,"+ menu ==null?"null": menu.getName(),e);
            throw e;
        }
        return effectrows;
    }

    @Override
    public Menu findById(long id)throws Exception {
        logger.info("findById begin,id:"+id);
        Menu menu = null;
        try {
            menu = MenuUtils.findById(id);
            if (menu==null) {
                menu = menuDao.findById(id);
            }
        } catch (Exception e) {
            logger.error("findById error,id=" + id, e);
            throw e;
        }
        return menu;
    }

    @Override
    public List<Menu> findList(MenuQuery menuQuery)throws Exception {
        logger.info("findList begin,MenuQuery=" + menuQuery == null ? "null" : menuQuery.toLogString());
        List<Menu> list = null;
        try {
            list = menuDao.findList(menuQuery);
        } catch (Exception e) {
            logger.error("findList error,MenuQuery=" + menuQuery==null?"null":menuQuery.toLogString(), e);
            throw e;
        }
        return list;
    }

    @Override
    public List<Menu> findListByPage(MenuQuery menuQuery)throws Exception {
        logger.info("findListByPage begin,MenuQuery=" + menuQuery==null?"null":menuQuery.toLogString());
        List<Menu> list = null;
        try {
            list = menuDao.findListByPage(menuQuery);
        } catch (Exception e) {
            logger.error("findListByPage error,MenuQuery=" + menuQuery==null?"null":menuQuery.toLogString(), e);
            throw e;
        }
        return list;
    }

    @Override
    public int count(MenuQuery menuQuery)throws Exception {
        logger.info("count begin,MenuQuery=" + menuQuery==null?"null":menuQuery.toLogString());
        int effectrows = 0;
        try {
            effectrows = menuDao.count(menuQuery);
        } catch (Exception e) {
            logger.error("count error,MenuQuery=" + menuQuery==null?"null":menuQuery.toLogString(), e);
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
            roleMenuService.deleteByMenuid(id);
            deleteByParentmenuid(id);
            effectrows = menuDao.deleteById(id);
        } catch (Exception e) {
            logger.error("deleteById error,id=" + id, e);
            throw e;
        }
        return effectrows;
    }

    @Override
    @Transactional
    public void deleteByParentmenuid(long parentmenuid) throws Exception {
        logger.info("deleteByParentMenuid begin,parentMenuid=" + parentmenuid);
        try {
            List<Menu> Menus = menuDao.findByParentmenuid(parentmenuid);
            if (!CollectionUtils.isEmpty(Menus)) {
                for (Menu menu : Menus) {
                    roleMenuService.deleteByMenuid(menu.getId());
                    deleteByParentmenuid(menu.getId());
                }
            }
            menuDao.deleteByParentmenuid(parentmenuid);
        } catch (Exception e) {
            logger.error("deleteByParentMenuid error,parentMenuid=" + parentmenuid, e);
            throw e;
        }
    }

    @Override
    public List<Menu> findByIds(Long[] ids) {
        List<Menu> list = MenuUtils.findByIds(ids);
        if (CollectionUtils.isEmpty(list)) {
            list = menuDao.findByIds(ids);
        }
        return list;
    }
}
