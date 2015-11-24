package com.lawinfo.service.front.impl;

import com.lawinfo.dao.front.CaseProgressCommentDao;
import com.lawinfo.domain.front.CaseProgressComment;
import com.lawinfo.domain.front.query.CaseProgressCommentQuery;
import com.lawinfo.service.front.CaseInfoService;
import com.lawinfo.service.front.CaseProgressCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Resource
    private CaseInfoService caseInfoService;
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
    @Transactional
    public int save(CaseProgressComment caseProgressComment) throws Exception {
        try {
            if (caseProgressComment!=null &&caseProgressComment.getCaseinfoid()>0&&caseProgressComment.getProcessnodeid()>0) {
                long caseinfoid = caseProgressComment.getCaseinfoid();
                int processnodeid = caseProgressComment.getProcessnodeid();
                caseProgressComment.initBaseDomain();
                if (caseProgressComment.getProcessnodeid()==400) {
                    double cqlsf = Double.parseDouble(caseProgressComment.getComment());
                    caseInfoService.updatePrePrice(caseinfoid, cqlsf);
                }else if (caseProgressComment.getProcessnodeid()==4400) {
                    double eqlsf = Double.parseDouble(caseProgressComment.getComment());
                    caseInfoService.updateSufPrice(caseinfoid, eqlsf);
                    //提交完律师费，案子完结
                    caseInfoService.updateStatusFinish(caseinfoid);
                }else if (caseProgressComment.getProcessnodeid()==1600) {
                    int ystj = Integer.parseInt(caseProgressComment.getComment());
                    caseInfoService.updateYstj(caseinfoid, ystj);
                }else if (caseProgressComment.getProcessnodeid()==3100) {
                    int estj = Integer.parseInt(caseProgressComment.getComment());
                    caseInfoService.updateEstj(caseinfoid, estj);
                }else if (caseProgressComment.getProcessnodeid()==2100) {
                    int sfss = Integer.parseInt(caseProgressComment.getComment());
                    caseInfoService.updateSfss(caseinfoid, sfss);
                }
                return caseProgressCommentDao.save(caseProgressComment);
            }
        } catch (Exception e) {
            logger.error("save exception", e);
            throw e;
        }
        return 0;
    }

    @Override
    @Transactional
    public int deleteById(long id) throws Exception {
        try {
            return caseProgressCommentDao.deleteById(id);
        } catch (Exception e) {
            logger.error("deleteById exception", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public int deleteByCaseinfoid(long caseinfoid) throws Exception {
        try {
            return caseProgressCommentDao.deleteByCaseinfoid(caseinfoid);
        } catch (Exception e) {
            logger.error("deleteById exception,caseinfoid"+caseinfoid, e);
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
