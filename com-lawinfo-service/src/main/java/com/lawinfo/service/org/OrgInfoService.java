package com.lawinfo.service.org;

import com.lawinfo.domain.org.OrgInfo;
import com.lawinfo.domain.org.OrgInfo;
import com.lawinfo.domain.org.User;
import com.lawinfo.domain.org.query.OrgInfoQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/12.
 */
public interface OrgInfoService {
    public void initCache()throws Exception;

    public List<OrgInfo> findAll()throws Exception;
    public List<OrgInfo> findAllFromDb() throws Exception;

    public int save(OrgInfo orgInfo)throws Exception;

    public OrgInfo findById(long id)throws Exception;

    public List<OrgInfo> findList(OrgInfoQuery orgInfoQuery)throws Exception;

    public List<OrgInfo> findListByPage(OrgInfoQuery orgInfoQuery)throws Exception;

    public int count(OrgInfoQuery orgInfoQuery)throws Exception;

    public int deleteById(long id)throws Exception;

}
