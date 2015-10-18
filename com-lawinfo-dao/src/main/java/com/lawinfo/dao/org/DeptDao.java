package com.lawinfo.dao.org;

import com.lawinfo.domain.org.Dept;
import com.lawinfo.domain.org.OrgInfo;
import com.lawinfo.domain.org.query.DeptQuery;
import com.lawinfo.domain.org.query.OrgInfoQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/12.
 */
public interface DeptDao {

    public List<Dept> findAll();

    public int save(Dept dept);

    public Dept findById(long id);

    public List<Dept> findList(DeptQuery deptQuery);

    public List<Dept> findListByPage(DeptQuery deptQuery);

    public int count(DeptQuery deptQuery);

    public int deleteById(long id);
    public int deleteByOrgid(long orgid);


}
