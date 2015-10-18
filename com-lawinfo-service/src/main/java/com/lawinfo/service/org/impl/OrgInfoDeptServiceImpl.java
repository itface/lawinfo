package com.lawinfo.service.org.impl;

import com.lawinfo.domain.org.Dept;
import com.lawinfo.domain.org.OrgInfo;
import com.lawinfo.domain.org.vo.DeptVo;
import com.lawinfo.service.org.DeptService;
import com.lawinfo.service.org.OrgInfoDeptService;
import com.lawinfo.service.org.OrgInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangrongtao on 15/10/18.
 */
@Service
public class OrgInfoDeptServiceImpl implements OrgInfoDeptService {
    private static Logger logger = LoggerFactory.getLogger(OrgInfoDeptServiceImpl.class);

    @Resource
    private DeptService deptService;
    @Resource
    private OrgInfoService orgInfoService;
    @Override
    public List<DeptVo> findDeptVo() throws Exception{
        try {
            List<Dept> depts = deptService.findAll();
            if (!CollectionUtils.isEmpty(depts)) {
                List<DeptVo> deptVos = new ArrayList<DeptVo>();
                List<OrgInfo> orgInfos = orgInfoService.findAll();
                Map<Long, String> orgIdNameMap = new HashMap<Long, String>();
                if (!CollectionUtils.isEmpty(orgInfos)) {
                    for (OrgInfo orgInfo : orgInfos) {
                        orgIdNameMap.put(orgInfo.getId(), orgInfo.getName());
                    }
                }
                for (Dept dept : depts) {
                    DeptVo deptVo = new DeptVo();
                    deptVo.setId(dept.getId());
                    deptVo.setOrgid(dept.getOrgid());
                    deptVo.setOrgname(orgIdNameMap.get(dept.getOrgid()));
                    deptVo.setName(dept.getName());
                    deptVo.setDescription(dept.getDescription());
                    deptVos.add(deptVo);
                }
                return deptVos;
            }
        } catch (Exception e) {
            logger.error("findDeptVo exception",e);
            throw e;
        }
        return null;
    }
}
