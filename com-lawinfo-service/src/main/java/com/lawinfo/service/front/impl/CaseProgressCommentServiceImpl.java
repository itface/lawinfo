package com.lawinfo.service.front.impl;

import com.lawinfo.dao.front.CaseProgressCommentDao;
import com.lawinfo.domain.front.CaseProgressComment;
import com.lawinfo.domain.front.query.CaseProgressCommentQuery;
import com.lawinfo.service.front.CaseProgressCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangrongtao on 15/11/12.
 */
@Service
public class CaseProgressCommentServiceImpl implements CaseProgressCommentService {
    private static Logger logger = LoggerFactory.getLogger(CaseProgressCommentServiceImpl.class);

    @Resource
    private CaseProgressCommentDao caseProgressCommentDao;
    @Override
    public List<CaseProgressComment> findAllByCaseinfoid(long caseinfoid) throws Exception {
        try {
            CaseProgressCommentQuery caseProgressCommentQuery = new CaseProgressCommentQuery();
            caseProgressCommentQuery.setCaseinfoid(caseinfoid);
            return caseProgressCommentDao.findList(caseProgressCommentQuery);
        } catch (Exception e) {
            logger.error("findAllByCaseinfoid exception", e);
            throw e;
        }
    }

    @Override
    public int save(CaseProgressComment caseProgressComment) throws Exception {
        try {
            if (caseProgressComment!=null) {
                caseProgressComment.initBaseDomain();
                return caseProgressCommentDao.save(caseProgressComment);
            }
        } catch (Exception e) {
            logger.error("save exception", e);
            throw e;
        }
        return 0;
    }

    @Override
    public int deleteById(long id) throws Exception {
        try {
            return caseProgressCommentDao.deleteById(id);
        } catch (Exception e) {
            logger.error("deleteById exception", e);
            throw e;
        }
    }

    @Override
    public List<CaseProgressComment> find(CaseProgressCommentQuery caseProgressCommentQuery) throws Exception {
        try {
            if (caseProgressCommentQuery!=null) {
                return caseProgressCommentDao.findList(caseProgressCommentQuery);
            }
        } catch (Exception e) {
            logger.error("find exception", e);
            throw e;
        }
        return null;
    }

    @Override
    public List<CaseProgressComment> findByPage(CaseProgressCommentQuery caseProgressCommentQuery) throws Exception {
        try {
            if (caseProgressCommentQuery!=null) {
                return caseProgressCommentDao.findListByPage(caseProgressCommentQuery);
            }
        } catch (Exception e) {
            logger.error("findByPage exception", e);
            throw e;
        }
        return null;
    }
}
