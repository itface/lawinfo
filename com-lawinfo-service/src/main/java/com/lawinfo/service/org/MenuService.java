package com.lawinfo.service.org;

import com.lawinfo.domain.common.EasyuiTree;
import com.lawinfo.domain.org.Action;
import com.lawinfo.domain.org.Menu;
import com.lawinfo.domain.org.Org;
import com.lawinfo.domain.org.query.MenuQuery;
import com.lawinfo.domain.org.query.OrgQuery;
import com.lawinfo.domain.org.vo.MenuVo;
import com.lawinfo.domain.org.vo.OrgVo;

import java.util.List;
import java.util.Set;

/**
 * Created by wangrongtao on 15/10/12.
 */
public interface MenuService {
    public void initCache()throws Exception;

    public List<MenuVo> findMenuTree()throws Exception;
    public List<EasyuiTree> findMenuEuTree()throws Exception;
    public List<Menu> findAll()throws Exception;
    public List<Menu> findByParentid(long parentid)throws Exception;
    public List<Menu> findAllFromDb() throws Exception;

    public int save(Menu menu)throws Exception;

    public Menu findById(long id)throws Exception;

    public List<Menu> findList(MenuQuery menuQuery)throws Exception;

    public List<Menu> findListByPage(MenuQuery menuQuery)throws Exception;

    public int count(MenuQuery menuQuery)throws Exception;

    public int deleteById(long id)throws Exception;
    public void deleteByParentmenuid(long parentmenuid)throws Exception;
    public List<Menu> findByIds(Long[] ids);

}
