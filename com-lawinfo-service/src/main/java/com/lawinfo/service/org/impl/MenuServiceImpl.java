package com.lawinfo.service.org.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.lawinfo.dao.org.MenuDao;
import com.lawinfo.dao.org.OrgDao;
import com.lawinfo.domain.org.Menu;
import com.lawinfo.domain.org.Org;
import com.lawinfo.domain.org.query.MenuQuery;
import com.lawinfo.domain.org.query.OrgQuery;
import com.lawinfo.domain.org.vo.MenuVo;
import com.lawinfo.domain.org.vo.OrgVo;
import com.lawinfo.service.org.MenuService;
import com.lawinfo.service.org.OrgService;
import com.lawinfo.service.org.utils.OrgInfoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/12.
 */
@Service
public class MenuServiceImpl implements MenuService {
    private static Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Resource
    private MenuDao menuDao;

    @Override
    public void initCache() throws Exception {
        try {
//            List<Menu> orgList = this.findAllFromDb();

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

    @Override
    public List<Menu> findAll() throws Exception{
        List<Menu> list = null;
        try {
            list = menuDao.findAll();
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
        Menu Menu = null;
        try {
            Menu =menuDao.findById(id);
        } catch (Exception e) {
            logger.error("findById error,id=" + id, e);
            throw e;
        }
        return Menu;
    }

    @Override
    public List<Menu> findList(MenuQuery menuQuery)throws Exception {
        logger.info("findList begin,MenuQuery=" + menuQuery == null ? "null" : menuQuery.toLogString());
        List<Menu> list = null;
        try {
            list = menuDao.findList(menuQuery);
        } catch (Exception e) {
            logger.error("findList error,MenuQuery=" + menuQuery==null?"null":menuQuery.toLogString(), e);
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
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int deleteById(long id)throws Exception {
        logger.info("deleteById begin,id=" + id);
        int effectrows = 0;
        try {
            deleteByParentmenuid(id);
            effectrows = menuDao.deleteById(id);
        } catch (Exception e) {
            logger.error("deleteById error,id=" + id, e);
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
                    deleteByParentmenuid(menu.getId());
                }
            }
            menuDao.deleteByParentmenuid(parentmenuid);
        } catch (Exception e) {
            logger.error("deleteByParentMenuid error,parentMenuid=" + parentmenuid, e);
        }
    }
}
