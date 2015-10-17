package com.lawinfo.service.org;

import com.lawinfo.domain.org.CaseInfo;
import com.lawinfo.domain.org.query.CaseInfoQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/14.
 */
public interface CaseInfoService {

    public List<CaseInfo> findAll()throws Exception;

    public int save(CaseInfo caseInfo)throws Exception;

    public CaseInfo findById(long id)throws Exception;

    public List<CaseInfo> findList(CaseInfoQuery caseInfoQuery)throws Exception;

    public List<CaseInfo> findListByPage(CaseInfoQuery caseInfoQuery)throws Exception;

    public int count(CaseInfoQuery caseInfoQuery)throws Exception;

    public int deleteById(long id)throws Exception;
}
