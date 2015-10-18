package com.lawinfo.service.org;

import com.lawinfo.domain.org.Dept;
import com.lawinfo.domain.org.query.DeptQuery;
import com.lawinfo.domain.org.vo.DeptVo;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/14.
 */
public interface DeptService {

    public List<Dept> findAll()throws Exception;

    public int save(Dept dept)throws Exception;

    public Dept findById(long id)throws Exception;

    public List<Dept> findList(DeptQuery deptQuery)throws Exception;

    public List<Dept> findListByPage(DeptQuery deptQuery)throws Exception;

    public int count(DeptQuery deptQuery)throws Exception;

    public int deleteById(long id)throws Exception;
    public int deleteByOrgid(long orgid)throws Exception;
}
