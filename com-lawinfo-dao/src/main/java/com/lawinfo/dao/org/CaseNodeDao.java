package com.lawinfo.dao.org;

import com.lawinfo.domain.org.CaseNode;
import com.lawinfo.domain.org.query.CaseNodeQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/12.
 */
public interface CaseNodeDao {

    public List<CaseNode> findAll();

    public int save(CaseNode caseNode);

    public CaseNode findById(long id);

    public List<CaseNode> findList(CaseNodeQuery caseNodeQuery);

    public List<CaseNode> findListByPage(CaseNodeQuery caseNodeQuery);

    public int count(CaseNodeQuery caseNodeQuery);

    public int deleteById(long id);


}
