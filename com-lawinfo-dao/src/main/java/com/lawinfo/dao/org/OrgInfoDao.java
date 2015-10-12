package com.lawinfo.dao.org;

import com.lawinfo.domain.org.OrgInfo;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/12.
 */
public interface OrgInfoDao {

    public List<OrgInfo> findAll();

    /*public boolean insert(OrgInfo orgInfo);

    public OrgInfo findById(long id);

    public List<OrgInfo> findList(OrgInfo orgInfo);

    public List<OrgInfo> findListByPage(OrgInfo orgInfo);

    public int count(OrgInfo orgInfo);

    public boolean deleteById(long id);*/


}
