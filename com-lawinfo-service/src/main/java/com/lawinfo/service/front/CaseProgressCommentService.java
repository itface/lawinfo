package com.lawinfo.service.front;

import com.lawinfo.domain.front.CaseProgress;
import com.lawinfo.domain.front.CaseProgressComment;
import com.lawinfo.domain.front.query.CaseProgressCommentQuery;
import com.lawinfo.domain.front.query.CaseProgressQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/11/3.
 */
public interface CaseProgressCommentService {
    public List<CaseProgressComment> findAllByCaseinfoid(long caseinfoid)throws Exception;
    public int save(CaseProgressComment caseProgressComment)throws Exception;
    public int deleteById(long id)throws Exception;
    public List<CaseProgressComment> find(CaseProgressCommentQuery caseProgressCommentQuery)throws Exception;
    public List<CaseProgressComment> findByPage(CaseProgressCommentQuery caseProgressCommentQuery)throws Exception;
}
