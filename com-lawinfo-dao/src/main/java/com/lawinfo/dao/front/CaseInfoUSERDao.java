package com.lawinfo.dao.front;


import com.lawinfo.domain.front.CaseInfo;
import com.lawinfo.domain.front.CaseInfoUser;
import com.lawinfo.domain.front.query.CaseInfoQuery;
import com.lawinfo.domain.front.query.CaseInfoUserQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/11/3.
 */
public interface CaseInfoUserDao {
    public List<CaseInfoUser> findAll();

    public int save(CaseInfoUser caseInfoUser);

    public CaseInfoUser findById(long id);

    public List<CaseInfoUser> findList(CaseInfoUserQuery caseInfoUserQuery);

    public List<CaseInfoUser> findListByPage(CaseInfoUserQuery caseInfoUserQuery);

    public int deleteById(long id);
    public List<Long> findAllCaseinfoid(CaseInfoUserQuery caseInfoUserQuery);
}
