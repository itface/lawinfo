package com.lawinfo.service.front.impl;

import com.lawinfo.service.front.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by wangrongtao on 15/11/24.
 */
@Service
public class DeleteCaseServiceImpl implements DeleteCaseService {
    private static Logger logger = LoggerFactory.getLogger(DeleteCaseServiceImpl.class);

    @Resource
    private CaseInfoService caseInfoService;
    @Resource
    private CaseInfoUserService caseInfoUserService;
    @Resource
    private CaseProgressCommentService caseProgressCommentService;
    @Override
    public boolean deleteByCaseinfoid(long caseinfoid)throws Exception{
        try {
            caseInfoUserService.deleteByCaseinfoid(caseinfoid);
            caseProgressCommentService.deleteByCaseinfoid(caseinfoid);
            caseInfoService.deleteCaseinfoOnlyById(caseinfoid);
            return true;
        } catch (Exception e) {
            logger.error("deleteByCaseinfoid caseinfoid:"+caseinfoid,e);
            throw e;
        }
    }
}
