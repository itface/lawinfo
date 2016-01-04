package com.lawinfo.service.org;

import com.lawinfo.domain.common.EasyuiTree;
import com.lawinfo.domain.org.Role;
import com.lawinfo.domain.org.User;
import com.lawinfo.domain.org.query.RoleQuery;
import com.lawinfo.domain.org.vo.ActionTreeVo;
import com.lawinfo.domain.org.vo.RoleTreeVo;
import com.lawinfo.domain.org.vo.RoleVo;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/14.
 */
public interface RoleService {

    public void initCache() throws Exception;
    public List<Role> findAll()throws Exception;
    public List<RoleVo> findAllVo()throws Exception;
    public List<Role> findAllFromDb() throws Exception;
    public List<RoleTreeVo> findAllTree()throws Exception;
    public List<EasyuiTree> findAllEuTree()throws Exception;
    public int save(String name,String roletag,String menuids,String actionids)throws Exception;
    public int update(long id,String name,String roletag,String menuids,String actionids)throws Exception;

    public Role findById(long id)throws Exception;
    public RoleVo findVoById(long id)throws Exception;

    public List<Role> findList(RoleQuery roleQuery)throws Exception;

    public List<Role> findListByPage(RoleQuery roleQuery)throws Exception;

    public int count(RoleQuery roleQuery)throws Exception;

    public int deleteById(long id)throws Exception;
}
