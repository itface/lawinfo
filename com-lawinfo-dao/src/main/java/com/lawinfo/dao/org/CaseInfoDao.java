package com.lawinfo.dao.org;

import com.lawinfo.domain.org.CaseInfo;
import com.lawinfo.domain.org.query.CaseInfoQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/12.
 */
public interface CaseInfoDao {

    public List<CaseInfo> findAll();

    public int save(CaseInfo caseInfo);

    public CaseInfo findById(long id);

    public List<CaseInfo> findList(CaseInfoQuery caseInfoQuery);

    public List<CaseInfo> findListByPage(CaseInfoQuery caseInfoQuery);

    public int count(CaseInfoQuery caseInfoQuery);

    public int deleteById(long id);


}
