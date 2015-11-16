package com.lawinfo.service.front;

import com.lawinfo.domain.front.CaseProgress;
import com.lawinfo.domain.front.query.CaseProgressQuery;
import com.lawinfo.domain.front.vo.CaseProgressTreeVo;

import java.util.List;

/**
 * Created by wangrongtao on 15/11/3.
 */
public interface CaseProgressService {
    public List<CaseProgressTreeVo> findTreeVo(String userid,long caseinfoid)throws Exception;
}
