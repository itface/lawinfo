package com.lawinfo.service.org;

import com.lawinfo.domain.org.CaseInfo;
import com.lawinfo.domain.org.CaseProgress;
import com.lawinfo.domain.org.query.CaseInfoQuery;
import com.lawinfo.domain.org.query.CaseProgressQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/14.
 */
public interface CaseProgressService {

    /**
     * 取所有案件进度信息（包含所有信息节点）
     * @param
     * @return
     */
    public String findCaseProgressWithNodeInfo(String caseid);
    public List<CaseProgress> findAllCaseProgress()throws Exception;
    public int save(CaseProgress caseProgress)throws Exception;
    public int deleteById(long id)throws Exception;
    public List<CaseProgress> find(CaseProgressQuery caseProgressQuery)throws Exception;
    public List<CaseProgress> findByPage(CaseProgressQuery caseProgressQuery)throws Exception;
}
