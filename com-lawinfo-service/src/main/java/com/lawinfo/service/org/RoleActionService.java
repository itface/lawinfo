package com.lawinfo.service.org;

import com.lawinfo.domain.org.RoleAction;
import com.lawinfo.domain.org.RoleMenu;
import com.lawinfo.domain.org.query.RoleActionQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/14.
 */
public interface RoleActionService {

    public List<RoleAction> findAll()throws Exception;

    public int save(RoleAction roleAction)throws Exception;

    public RoleAction findById(long id)throws Exception;
    public List<RoleAction> findByRoleid(long roleid)throws Exception;
    public List<RoleAction> findByActionid(long actionid)throws Exception;

    public List<RoleAction> findList(RoleActionQuery roleActionQuery)throws Exception;

    public List<RoleAction> findListByPage(RoleActionQuery roleActionQuery)throws Exception;

    public int count(RoleActionQuery roleActionQuery)throws Exception;

    public int deleteById(long id)throws Exception;
    public int deleteByActionid(long actionid)throws Exception;
    public int deleteByRoleid(long roleid)throws Exception;
}
