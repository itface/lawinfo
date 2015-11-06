package com.lawinfo.service.front;

import com.lawinfo.domain.front.CaseProgress;
import com.lawinfo.domain.front.query.CaseProgressQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/11/3.
 */
public interface CaseProgressService {
    public String findCaseProgressWithNodeInfo(String caseid);
    public List<CaseProgress> findAllCaseProgress()throws Exception;
    public int save(CaseProgress caseProgress)throws Exception;
    public int deleteById(long id)throws Exception;
    public List<CaseProgress> find(CaseProgressQuery caseProgressQuery)throws Exception;
    public List<CaseProgress> findByPage(CaseProgressQuery caseProgressQuery)throws Exception;
}
