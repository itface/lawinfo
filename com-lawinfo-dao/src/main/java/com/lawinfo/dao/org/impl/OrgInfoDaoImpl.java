package com.lawinfo.dao.org.impl;

import com.lawinfo.dao.org.OrgInfoDao;
import com.lawinfo.domain.org.OrgInfo;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/12.
 */
public class OrgInfoDaoImpl implements OrgInfoDao {
    @Override
    public boolean insert(OrgInfo orgInfo) {
        return false;
    }

    @Override
    public OrgInfo findById(long id) {
        return null;
    }

    @Override
    public List<OrgInfo> findList(OrgInfo orgInfo) {
        return null;
    }

    @Override
    public List<OrgInfo> findListByPage(OrgInfo orgInfo) {
        return null;
    }

    @Override
    public int count(OrgInfo orgInfo) {
        return 0;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }
}
