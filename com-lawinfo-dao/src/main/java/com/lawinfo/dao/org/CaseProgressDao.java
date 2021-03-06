package com.lawinfo.dao.org;

import com.lawinfo.domain.org.CaseProgress;
import com.lawinfo.domain.org.query.CaseProgressQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/12.
 */
public interface CaseProgressDao {

    public List<CaseProgress> findAll();

    public int save(CaseProgress caseProgress);

    public CaseProgress findById(long id);

    public List<CaseProgress> findList(CaseProgressQuery caseProgressQuery);

    public List<CaseProgress> findListByPage(CaseProgressQuery caseProgressQuery);

    public int count(CaseProgressQuery caseProgressQuery);

    public int deleteById(long id);


}
