package com.lawinfo.service.front;

import com.lawinfo.domain.front.CaseProgress;
import com.lawinfo.domain.front.query.CaseProgressQuery;
import com.lawinfo.domain.front.vo.CaseProgressTreeVo;
import com.lawinfo.domain.front.vo.CaseProgressViewVo;

import java.util.List;

/**
 * Created by wangrongtao on 15/11/3.
 */
public interface CaseProgressService {
    public CaseProgressViewVo findCaseProgressCommentVo(String userid,long caseinfoid)throws Exception;
}
