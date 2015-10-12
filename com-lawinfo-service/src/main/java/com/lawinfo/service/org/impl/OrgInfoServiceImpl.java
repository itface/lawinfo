package com.lawinfo.service.org.impl;

import com.lawinfo.dao.org.OrgInfoDao;
import com.lawinfo.domain.org.OrgInfo;
import com.lawinfo.service.org.OrgInfoService;
import org.springframework.stereotype.Service;
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
        try {
            list = orgInfoDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder("begin:");
        if (!CollectionUtils.isEmpty(list)) {
            for (OrgInfo orgInfo : list) {
                sb.append(orgInfo.toString());
            }
        }
        return sb.toString();
    }
}
