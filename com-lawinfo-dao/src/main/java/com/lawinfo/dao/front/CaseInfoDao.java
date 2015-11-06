package com.lawinfo.dao.front;


import com.lawinfo.domain.front.CaseInfo;
import com.lawinfo.domain.front.query.CaseInfoQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/11/3.
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
