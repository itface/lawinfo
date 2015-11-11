package com.lawinfo.service.org;

import com.lawinfo.domain.org.Org;
import com.lawinfo.domain.org.query.OrgQuery;
import com.lawinfo.domain.org.vo.OrgVo;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/12.
 */
public interface OrgService {
    public void initCache()throws Exception;

    public List<OrgVo> findSubOrgTree(long orgid) throws Exception;
    public List<OrgVo> findOrgTree()throws Exception;
    public List<OrgVo> findCustomTree()throws Exception;
    public List<OrgVo> findLawyerTree()throws Exception;
    public List<Org> findAll()throws Exception;
    public List<Org> findAllFromDb() throws Exception;
//    public List<Org> findAllBank() throws Exception;

    public int save(Org org)throws Exception;

    public Org findById(long id)throws Exception;

    public List<Org> findList(OrgQuery orgQuery)throws Exception;

    public List<Org> findListByPage(OrgQuery orgQuery)throws Exception;

    public int count(OrgQuery orgQuery)throws Exception;

    public int deleteById(long id)throws Exception;
    public void deleteByParentorgid(long parentorgid)throws Exception;

}
