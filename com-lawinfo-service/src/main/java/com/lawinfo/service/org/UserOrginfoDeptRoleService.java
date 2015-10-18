package com.lawinfo.service.org;

import com.lawinfo.domain.org.vo.UserVo;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/18.
 */
public interface UserOrginfoDeptRoleService {
    public List<UserVo> find()throws Exception;
}
