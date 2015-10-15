package com.lawinfo.service.org;

import com.lawinfo.domain.org.CaseNode;
import com.lawinfo.domain.org.query.CaseNodeQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/14.
 */
public interface CaseNodeService {

    public List<CaseNode> findAll()throws Exception;

    public int save(CaseNode caseNode)throws Exception;

    public CaseNode findById(long id)throws Exception;

    public List<CaseNode> findList(CaseNodeQuery caseNodeQuery)throws Exception;

    public List<CaseNode> findListByPage(CaseNodeQuery caseNodeQuery)throws Exception;

    public int count(CaseNodeQuery caseNodeQuery)throws Exception;

    public int deleteById(long id)throws Exception;
}
