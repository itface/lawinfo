package com.lawinfo.dao.org;

import com.lawinfo.domain.org.RoleMenu;
import com.lawinfo.domain.org.query.RoleMenuQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/12.
 */
public interface RoleMenuDao {

    public List<RoleMenu> findAll();

    public int save(RoleMenu roleMenu);

    public RoleMenu findById(Long id);
    public List<RoleMenu> findByRoleid(Long roleid);
    public List<RoleMenu> findByMenuid(Long menuid);
    public List<RoleMenu> findList(RoleMenuQuery roleMenu);

    public List<RoleMenu> findListByPage(RoleMenuQuery roleMenu);

    public int count(RoleMenuQuery roleMenuQuery);

    public int deleteById(Long id);
    public int deleteByMenuid(Long menuid);
    public int deleteByRoleid(Long roleid);

}
