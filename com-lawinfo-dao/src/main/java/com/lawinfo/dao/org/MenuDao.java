package com.lawinfo.dao.org;

import com.lawinfo.domain.org.Menu;
import com.lawinfo.domain.org.query.MenuQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/12.
 */
public interface MenuDao {

    public List<Menu> findAll();
    public List<Menu> findByParentmenuid(long parentmenuid);

    public int save(Menu menu);

    public Menu findById(long id);

    public List<Menu> findList(MenuQuery menuQuery);

    public List<Menu> findListByPage(MenuQuery menuQuery);

    public int count(MenuQuery menuQuery);

    public int deleteById(long id);
    public int deleteByParentmenuid(long parentmenuid);


}
