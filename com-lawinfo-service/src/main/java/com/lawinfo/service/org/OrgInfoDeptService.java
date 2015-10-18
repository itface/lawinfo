package com.lawinfo.service.org;

import com.lawinfo.domain.org.vo.DeptVo;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/18.
 */
public interface OrgInfoDeptService {
    public List<DeptVo> findDeptVo()throws Exception;
}
