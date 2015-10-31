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

    public RoleAction findById(long id);
    public List<RoleAction> findByRoleid(long roleid);
    public List<RoleAction> findByActionid(long actionid);

    public List<RoleAction> findList(RoleActionQuery roleAction);

    public List<RoleAction> findListByPage(RoleActionQuery roleAction);

    public int count(RoleActionQuery roleActionQuery);

    public int deleteById(long id);
    public int deleteByActionid(long actionid);
    public int deleteByRoleid(long roleid);

}
