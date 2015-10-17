package com.lawinfo.dao.org;

import com.lawinfo.domain.org.OrgInfo;
import com.lawinfo.domain.org.query.OrgInfoQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/12.
 */
public interface OrgInfoDao {

    public List<OrgInfo> findAll();

    public int save(OrgInfo orgInfo);

    public OrgInfo findById(long id);

    public List<OrgInfo> findList(OrgInfoQuery orgInfoQuery);

    public List<OrgInfo> findListByPage(OrgInfoQuery orgInfoQuery);

    public int count(OrgInfoQuery orgInfoQuery);

    public int deleteById(long id);


}
