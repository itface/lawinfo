package com.lawinfo.service.front;

import com.lawinfo.domain.front.CaseInfo;
import com.lawinfo.domain.front.query.CaseInfoQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/11/3.
 */
public interface CaseInfoService {
    public List<CaseInfo> findAll()throws Exception;

    public int save(CaseInfo caseInfo)throws Exception;
    public int updateStatus(long caseinfoid,int status)throws Exception;
    public int updateStatusFinish(long caseinfoid)throws Exception;
    public int updateYstj(long caseinfoid,int ystj)throws Exception;
    public int updateEstj(long caseinfoid,int estj)throws Exception;
    public int updatePrePrice(long caseinfoid,double prePrice)throws Exception;
    public int updateSufPrice(long caseinfoid,double sufPrice)throws Exception;
    public int updateExeLawyers(long caseinfoid,String exeajbh,String exeLawyers,String exeLawyerids)throws Exception;

    public CaseInfo findById(long id)throws Exception;

    public List<CaseInfo> findList(CaseInfoQuery caseInfoQuery)throws Exception;
    public List<CaseInfo> findList(CaseInfoQuery caseInfoQuery,String userid)throws Exception;

    public List<CaseInfo> findListByPage(CaseInfoQuery caseInfoQuery)throws Exception;

    public int count(CaseInfoQuery caseInfoQuery)throws Exception;

    public int deleteById(long id)throws Exception;
    public boolean ifAllowd(String userid,long caseinfoid)throws Exception;

}
