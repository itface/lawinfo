package com.lawinfo.dao.org;

import com.lawinfo.domain.org.Action;
import com.lawinfo.domain.org.Menu;
import com.lawinfo.domain.org.query.MenuQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/12.
 */
public interface MenuDao {

    public List<Menu> findAll();
    public List<Menu> findByParentmenuid(Long parentmenuid);

    public int save(Menu menu);

    public Menu findById(Long id);

    public List<Menu> findList(MenuQuery menuQuery);

    public List<Menu> findListByPage(MenuQuery menuQuery);

    public int count(MenuQuery menuQuery);

    public int deleteById(Long id);
    public int deleteByParentmenuid(Long parentmenuid);

    public List<Menu> findByIds(Long[] ids);

}
