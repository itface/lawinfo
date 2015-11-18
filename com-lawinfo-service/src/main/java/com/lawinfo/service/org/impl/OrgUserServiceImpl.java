package com.lawinfo.service.org.impl;

import com.lawinfo.domain.org.vo.OrgVo;
import com.lawinfo.service.org.OrgService;
import com.lawinfo.service.org.OrgUserService;
import com.lawinfo.service.org.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangrongtao on 15/11/18.
 */
@Service
public class OrgUserServiceImpl implements OrgUserService {
    @Resource
    private UserService userService;
    @Resource
    private OrgService orgService;
    @Override
    @Transactional
    public int deleteOrgAndUserByOrgid(long orgid) throws Exception {
        List<OrgVo> orgVoList = orgService.findSubOrgTree(orgid);
        if (!CollectionUtils.isEmpty(orgVoList)) {
            for (OrgVo orgVo : orgVoList) {
                userService.deleteByOrgid(orgVo.getId());
            }
        }
        userService.deleteByOrgid(orgid);
        return orgService.deleteById(orgid);
    }
}
