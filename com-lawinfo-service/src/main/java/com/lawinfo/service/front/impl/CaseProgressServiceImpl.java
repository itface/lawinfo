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
import com.lawinfo.domain.front.vo.CaseProgressViewVo;
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

    /**
     * 是否只是诉讼律师,默认是诉讼律师,因为能查看案件的就是诉讼律师,执行律师,部门负责人和团队负责人.
     * 能看到案件的只有讨讼律师不能看到执行结点
     * 是诉讼律师,并且不是执行律师,才不能看到执行节点
     * @param userid
     * @param caseinfoid
     * @return
     * @throws Exception
     */
    private boolean ifSslawyerOnly(String userid,long caseinfoid) throws Exception{
        boolean ifss = false;
        boolean ifzx = false;
        try {
            CaseInfoUserQuery caseInfoUserQuery = new CaseInfoUserQuery();
            caseInfoUserQuery.setCaseinfoid(caseinfoid);
            List<CaseInfoUser> caseInfoUsers = caseInfoUserService.findList(caseInfoUserQuery);
            if (!CollectionUtils.isEmpty(caseInfoUsers)) {
                for (CaseInfoUser caseInfoUser : caseInfoUsers) {
                    int usertype = caseInfoUser.getUsertype();
                    String userid2 = caseInfoUser.getUserid();
                    if (usertype == 2&&userid.equals(userid2)) {
                        ifss = true;
                    }else if (usertype != 2&&userid.equals(userid2)) {
                        ifss = false;
                        ifzx = true;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("ifSs exception"+caseinfoid+",userid:"+userid, e);
            throw e;
        }
        //是诉讼律师,并且不是执行律师,才不能看到执行节点
        return ifss&&!ifzx;
    }
    @Override
    public CaseProgressViewVo findCaseProgressCommentVo(String userid,long caseinfoid) throws Exception {
        try {
            if (caseinfoid>0) {
                CaseInfo caseInfo = caseInfoService.findByIdWithPrivilege(caseinfoid, userid);
                if (caseInfo != null) {
                    CaseProgressViewVo caseProgressViewVo = new CaseProgressViewVo();
                    caseProgressViewVo.setCaseInfo(caseInfo);
                    boolean ifSslawyerOnly = ifSslawyerOnly(userid, caseinfoid);
                    caseProgressViewVo.setIfsslawyer(ifSslawyerOnly);
                    List<CaseProgressTreeVo> sslist = CaseProgressEnum.getSsCaseProgressTree(ifSslawyerOnly, (caseInfo.getSfss() == 1 ? false : true));
                    List<CaseProgressTreeVo> exelist = CaseProgressEnum.getExecuteCaseProgressTree(ifSslawyerOnly);
                    caseProgressViewVo.setSsCaseProgressTreeVoList(sslist);
                    caseProgressViewVo.setExecuteCaseProgressTreeVoList(exelist);
                    List<CaseProgressComment> caseProgressComments = caseProgressCommentService.findAllByCaseinfoid(caseinfoid);
                    if (!CollectionUtils.isEmpty(caseProgressComments)) {
                        Multimap<Integer, CaseProgressComment> caseProgressCommentMultimap = ArrayListMultimap.create();
                        for (CaseProgressComment caseProgressComment : caseProgressComments) {
                            caseProgressCommentMultimap.put(caseProgressComment.getProcessnodeid(), caseProgressComment);
                        }
                        List<CaseProgressComment> ssComments = new ArrayList<CaseProgressComment>();
                        List<CaseProgressComment> exeComments = new ArrayList<CaseProgressComment>();
                        for (CaseProgressTreeVo caseProgressTreeVo : sslist) {
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
                                    caseProgressTreeVo1.setBackColor("#95EA95");
                                    flag = true;
                                    caseProgressTreeVo1.setCaseProgressCommentList(comments);
                                    ssComments.addAll(caseProgressCommentCollection);
                                }
                            }
                            if (flag) {
                                caseProgressTreeVo.setBackColor("#95EA95");
                            }
                            Collections.sort(ssComments);
                            caseProgressViewVo.setSsCaseProgressCommentList(ssComments);
                        }
                        for (CaseProgressTreeVo caseProgressTreeVo : exelist) {
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
                                    caseProgressTreeVo1.setBackColor("#95EA95");
                                    flag = true;
                                    caseProgressTreeVo1.setCaseProgressCommentList(comments);
                                    exeComments.addAll(caseProgressCommentCollection);
                                }
                            }
                            if (flag) {
                                caseProgressTreeVo.setBackColor("#95EA95");
                            }
                            Collections.sort(exeComments);
                            caseProgressViewVo.setExecuteCaseProgressCommentList(exeComments);
                        }
                    }
                    return caseProgressViewVo;
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
