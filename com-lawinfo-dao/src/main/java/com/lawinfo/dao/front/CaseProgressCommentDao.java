package com.lawinfo.dao.front;

import com.lawinfo.domain.front.CaseProgress;
import com.lawinfo.domain.front.CaseProgressComment;
import com.lawinfo.domain.front.query.CaseProgressCommentQuery;
import com.lawinfo.domain.front.query.CaseProgressQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/11/3.
 */
public interface CaseProgressCommentDao {
    public List<CaseProgressComment> findAll();

    public int save(CaseProgressComment caseProgressComment);

    public CaseProgressComment findById(long id);

    public List<CaseProgressComment> findList(CaseProgressCommentQuery caseProgressCommentQuery);

    public List<CaseProgressComment> findListByPage(CaseProgressCommentQuery caseProgressCommentQuery);

    public int count(CaseProgressCommentQuery caseProgressCommentQuery);

    public int deleteById(long id);
}
