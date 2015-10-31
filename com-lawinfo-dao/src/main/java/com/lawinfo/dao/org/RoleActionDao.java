package com.lawinfo.dao.org;

import com.lawinfo.domain.org.RoleAction;
import com.lawinfo.domain.org.RoleMenu;
import com.lawinfo.domain.org.query.RoleActionQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/12.
 */
public interface RoleActionDao {

    public List<RoleAction> findAll();

    public int save(RoleAction roleAction);

    public RoleAction findById(Long id);
    public List<RoleAction> findByRoleid(Long roleid);
    public List<RoleAction> findByActionid(Long actionid);

    public List<RoleAction> findList(RoleActionQuery roleAction);

    public List<RoleAction> findListByPage(RoleActionQuery roleAction);

    public int count(RoleActionQuery roleActionQuery);

    public int deleteById(Long id);
    public int deleteByActionid(Long actionid);
    public int deleteByRoleid(Long roleid);

}
