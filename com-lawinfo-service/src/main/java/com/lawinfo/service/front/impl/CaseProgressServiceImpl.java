package com.lawinfo.service.front.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.lawinfo.dao.front.CaseProgressCommentDao;
import com.lawinfo.domain.front.CaseInfo;
import com.lawinfo.domain.front.CaseInfoUser;
import com.lawinfo.domain.front.CaseProgress;
import com.lawinfo.domain.front.CaseProgressComment;
import com.lawinfo.domain.front.enumtype.CaseProgressEnum;
import com.lawinfo.domain.front.query.CaseInfoUserQuery;
import com.lawinfo.domain.front.query.CaseProgressQuery;
import com.lawinfo.domain.front.vo.CaseProgressTreeVo;
import com.lawinfo.service.front.CaseInfoService;
import com.lawinfo.service.front.CaseInfoUserService;
import com.lawinfo.service.front.CaseProgressCommentService;
import com.lawinfo.service.front.CaseProgressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.*;

/**
 * Created by wangrongtao on 15/11/3.
 */
@Service
public class CaseProgressServiceImpl implements CaseProgressService{
    private static Logger logger = LoggerFactory.getLogger(CaseProgressServiceImpl.class);

    @Resource
    private CaseInfoUserService caseInfoUserService;
    @Resource
    private  CaseInfoService caseInfoService;

    @Resource
    private CaseProgressCommentService caseProgressCommentService;

    private boolean ifSs(String userid,long caseinfoid) throws Exception{
        try {
            CaseInfoUserQuery caseInfoUserQuery = new CaseInfoUserQuery();
            caseInfoUserQuery.setCaseinfoid(caseinfoid);
            List<CaseInfoUser> caseInfoUsers = caseInfoUserService.findList(caseInfoUserQuery);
            if (!CollectionUtils.isEmpty(caseInfoUsers)) {
                for (CaseInfoUser caseInfoUser : caseInfoUsers) {
                    int usertype = caseInfoUser.getUsertype();
                    String userid2 = caseInfoUser.getUserid();
                    if (usertype == 2&&userid.equals(userid2)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("ifSs exception"+caseinfoid+",userid:"+userid, e);
            throw e;
        }
        return false;
    }
    @Override
    public List<CaseProgressTreeVo> findTreeVo(String userid,long caseinfoid) throws Exception {
        try {
            if (caseinfoid>0) {
                boolean ifallowd = caseInfoService.ifAllowd(userid, caseinfoid);
                if (ifallowd) {
                    boolean ifss = ifSs(userid, caseinfoid);
                    List<CaseProgressTreeVo> list = CaseProgressEnum.initCaseProgressTree(ifss);
                    if (!CollectionUtils.isEmpty(list)) {
                        List<CaseProgressComment> caseProgressComments = caseProgressCommentService.findAllByCaseinfoid(caseinfoid);
                        if (!CollectionUtils.isEmpty(caseProgressComments)) {
                            Multimap<Integer, CaseProgressComment> caseProgressCommentMultimap = ArrayListMultimap.create();
                            for (CaseProgressComment caseProgressComment : caseProgressComments) {
                                caseProgressCommentMultimap.put(caseProgressComment.getProcessnodeid(), caseProgressComment);
                            }
                            for (CaseProgressTreeVo caseProgressTreeVo : list) {
                                //取根节点目录
                                List<CaseProgressTreeVo> caseProgressTreeVos = caseProgressTreeVo.getNodes();
                                caseProgressTreeVo.setCaseinfoid(caseinfoid);
                                boolean flag = false;
                                for (CaseProgressTreeVo caseProgressTreeVo1 : caseProgressTreeVos) {
                                    int nodeid = caseProgressTreeVo1.getId();
                                    caseProgressTreeVo1.setCaseinfoid(caseinfoid);
                                    Collection<CaseProgressComment> caseProgressCommentCollection = caseProgressCommentMultimap.get(nodeid);
                                    if (!CollectionUtils.isEmpty(caseProgressCommentCollection)) {
                                        List<CaseProgressComment> comments = (List) caseProgressCommentCollection;
                                        Collections.sort(comments);
                                        caseProgressTreeVo1.setBackColor("green");
                                        flag = true;
                                        caseProgressTreeVo1.setCaseProgressCommentList(comments);
                                    }
                                }
                                if (flag) {
                                    caseProgressTreeVo.setBackColor("green");
                                }
                            }
                        }
                    }
                    return list;
                } else {
                    logger.error("findTreeVo error,not allowed,userid:" + userid + ",caseinfoid:" + caseinfoid);
                }
            }
        } catch (Exception e) {
            logger.error("findTreeVo exception", e);
            throw e;
        }
        return null;
    }
}
