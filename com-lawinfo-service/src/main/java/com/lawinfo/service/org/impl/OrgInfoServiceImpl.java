package com.lawinfo.service.org.impl;

import com.lawinfo.dao.org.OrgInfoDao;
import com.lawinfo.domain.org.OrgInfo;
import com.lawinfo.domain.org.query.OrgInfoQuery;
import com.lawinfo.service.org.OrgInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/12.
 */
@Service
public class OrgInfoServiceImpl implements OrgInfoService {

    @Resource
    private OrgInfoDao orgInfoDao;

    @Override
    public String findAll() {
        List<OrgInfo> list = null;
        List<OrgInfo> list2 = null;
        try {
            list = orgInfoDao.findAll();
            OrgInfoQuery orgInfoQuery = new OrgInfoQuery();
            orgInfoQuery.setStartRow(3);
            orgInfoQuery.setPageSize(2);
            list2=orgInfoDao.findListByPage(orgInfoQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder("begin:");
        if (!CollectionUtils.isEmpty(list)) {
            for (OrgInfo orgInfo : list) {
                sb.append(orgInfo.toString());
            }
        }
        if (!CollectionUtils.isEmpty(list2)) {
            sb.append("\n");
            for (OrgInfo orgInfo : list2) {
                sb.append(orgInfo.toString());
            }
        }
        return sb.toString();
    }

    @Override
    @Transactional
    public boolean batchSave() {
        OrgInfo orgInfo = new OrgInfo();
        orgInfo.setName("yahoo5");
        orgInfo.setOrgtype(2);
        int effectRows = orgInfoDao.save(orgInfo);

        OrgInfo orgInfo2 = new OrgInfo();
        orgInfo2.setName("yahoo6");
        orgInfo2.setOrgtype(2);
        int effectRows2 = orgInfoDao.save(orgInfo2);
        return true;
    }
}
