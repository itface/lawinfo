package com.lawinfo.service.front;

import com.lawinfo.domain.front.CaseInfo;
import com.lawinfo.domain.front.CaseInfoUser;
import com.lawinfo.domain.front.query.CaseInfoQuery;
import com.lawinfo.domain.front.query.CaseInfoUserQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/11/3.
 */
public interface CaseInfoUserService {
    public List<CaseInfoUser> findAll()throws Exception;

    public int save(CaseInfoUser caseInfoUser)throws Exception;

    public CaseInfoUser findById(long id)throws Exception;

    public List<CaseInfoUser> findList(CaseInfoUserQuery caseInfoUserQuery)throws Exception;
    public List<Long> findAllCaseinfoid(CaseInfoUserQuery caseInfoUserQuery)throws Exception;

    public List<CaseInfoUser> findListByPage(CaseInfoUserQuery caseInfoUserQuery)throws Exception;

    public int deleteById(long id)throws Exception;
    public int deleteByCaseinfoid(long caseinfoid)throws Exception;

}
