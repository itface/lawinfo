package com.lawinfo.service.front.impl;

import com.lawinfo.dao.front.CaseInfoDao;
import com.lawinfo.domain.common.PageVo;
import com.lawinfo.domain.front.CaseInfo;
import com.lawinfo.domain.front.CaseInfoUser;
import com.lawinfo.domain.front.query.CaseInfoQuery;
import com.lawinfo.domain.front.query.CaseInfoUserQuery;
import com.lawinfo.domain.org.Role;
import com.lawinfo.domain.org.User;
import com.lawinfo.domain.org.UserRole;
import com.lawinfo.domain.org.enumtype.RoleTagEnum;
import com.lawinfo.service.constant.SysConstants;
import com.lawinfo.service.front.CaseInfoService;
import com.lawinfo.service.front.CaseInfoUserService;
import com.lawinfo.service.org.OrgService;
import com.lawinfo.service.org.RoleService;
import com.lawinfo.service.org.UserRoleService;
import com.lawinfo.service.org.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangrongtao on 15/11/3.
 */
@Service
public class CaseInfoServiceImpl implements CaseInfoService {
    private static Logger logger = LoggerFactory.getLogger(CaseInfoServiceImpl.class);

    @Resource
    private CaseInfoDao caseInfoDao;
    @Resource
    private CaseInfoUserService caseInfoUserService;
    @Resource
    private UserService userService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RoleService roleService;
    @Resource
    private OrgService orgService;

    @Override
    public List<CaseInfo> findAll() throws Exception{
        List<CaseInfo> list = null;
        try {
            list = caseInfoDao.findAll();
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return list;
    }

    @Override
    @Transactional
    public int save(CaseInfo caseInfo)throws Exception {
        int effectrows = 0;
        try {
            if (caseInfo!=null) {
                caseInfo.init();
                caseInfo.initBaseDomain();
                if (SysConstants.CASEINFO_TYPE_INIT!=caseInfo.getCasetype()) {
                    caseInfo.setStatus(SysConstants.CASEINFO_STATUS_ALL_FINISHED);
                }
                long caseorgid = caseInfo.getCaseorgid();
                String caseorgfullid = orgService.getFullPathId(caseorgid);
                caseInfo.setCasefullorgid(caseorgfullid);
                effectrows = caseInfoDao.save(caseInfo);
                if (effectrows > 0) {
                    long caseinfoid = caseInfo.getId();
                     String contactids = caseInfo.getContactids();
                     String sslawyerids = caseInfo.getSslawyerids();
                    if (!StringUtils.isEmpty(contactids)) {
                        String[] contactidArr = contactids.split(",");
                        for (int i = 0; i < contactidArr.length; i++) {
                            String userid = contactidArr[i];
                            CaseInfoUser caseInfoUser = new CaseInfoUser();
                            caseInfoUser.setUserid(userid);
                            caseInfoUser.setUsertype(SysConstants.CASEINFO_CONTACT_TYPE);
                            caseInfoUser.setCaseinfoid(caseinfoid);
                            caseInfoUserService.save(caseInfoUser);
                        }
                    }
                    if (!StringUtils.isEmpty(sslawyerids)) {
                        String[] sslaweridArr = sslawyerids.split(",");
                        for (int i = 0; i < sslaweridArr.length; i++) {
                            String sslawyerid = sslaweridArr[i];
                            CaseInfoUser caseInfoUser = new CaseInfoUser();
                            caseInfoUser.setUserid(sslawyerid);
                            caseInfoUser.setUsertype(SysConstants.CASEINFO_SSLAWYER_TYPE);
                            caseInfoUser.setCaseinfoid(caseinfoid);
                            caseInfoUserService.save(caseInfoUser);
                        }
                    }
                }
                logger.info("save success,effectrows:"+effectrows+",caseid:"+caseInfo.getId());
            }
        } catch (Exception e) {
            logger.error("findAll error,caseid:"+caseInfo.getId(),e);
            throw e;
        }
        return effectrows;
    }

    @Override
    public CaseInfo findById(long id)throws Exception {
        logger.info("findById begin,id:"+id);
        CaseInfo caseInfo = null;
        try {
            caseInfo=caseInfoDao.findById(id);
        } catch (Exception e) {
            logger.error("findById error,id=" + id, e);
            throw e;
        }
        return caseInfo;
    }

    @Override
    public CaseInfo findByIdWithPrivilege(long id,String userid) throws Exception {
        try {
            if (!StringUtils.isEmpty(userid)) {
                CaseInfoQuery caseInfoQuery = new CaseInfoQuery();
                caseInfoQuery.setId(id);
                List<CaseInfo> list = findList(caseInfoQuery, userid);
                if (!CollectionUtils.isEmpty(list)) {
                    return list.get(0);
                }
            }
        } catch (Exception e) {
            logger.error("findByIdWithPrivilege exception,userid:{},id:{}",userid,id,e);
            throw e;
        }
        return null;
    }

    @Override
    public List<CaseInfo> findList(CaseInfoQuery caseInfoQuery) throws Exception {
        List<CaseInfo> list = null;
        try {
            list = caseInfoDao.findList(caseInfoQuery);
        } catch (Exception e) {
            logger.error("findList error,CaseInfoQuery=" + caseInfoQuery==null?"null":caseInfoQuery.toLogString(), e);
            throw e;
        }
        return list;
    }

    @Override
    public List<CaseInfo> findList(CaseInfoQuery caseInfoQuery,String userid)throws Exception {
        logger.info("findList begin,CaseInfoQuery=" + caseInfoQuery == null ? "null" : caseInfoQuery.toLogString());
        List<CaseInfo> list = null;
        try {
            if (caseInfoQuery!=null&&!StringUtils.isEmpty(userid)) {
                List<UserRole> userRoles = userRoleService.findByUserid(userid);
                if (!CollectionUtils.isEmpty(userRoles)) {
                    boolean queryflag = false;
                    for (UserRole userRole : userRoles) {
                        long roleid = userRole.getRoleid();
                        Role role = roleService.findById(roleid);
                        if (role != null) {
                            String roletag = role.getRoletag();
                            //部门负责人和诉讼负责人和团队负责人,都可以查询所有案件,主办律师,只能查询诉讼律师或执行律师是自已的.
                            //客户的负责人能查看他们单位的所有案件,客户经理只能查询自已经办的案件
                            if (RoleTagEnum.LAWYER.getRoletag().equals(roletag)) {
                                caseInfoQuery.setLawyer(userid);
                                queryflag=true;
                                break;
                            }else if (RoleTagEnum.LAWYER_SS_DEPT_MANAGER.getRoletag().equals(roletag)||RoleTagEnum.LAWYER_EXECUTE_DEPT_MANAGER.getRoletag().equals(roletag)||RoleTagEnum.LAWYER_COMPANY_MANAGER.getRoletag().equals(roletag)) {
                                queryflag=true;
                                break;
                            }else if (RoleTagEnum.CUSTOM.getRoletag().equals(roletag)) {
                                caseInfoQuery.setContact(userid);
                                queryflag=true;
                                break;
                            }else if (RoleTagEnum.CUSTOM_COMPANY_MANAGER.getRoletag().equals(roletag)||RoleTagEnum.CUSTOM_DEPT_MANAGER.getRoletag().equals(roletag)) {
                                User user = userService.findByUserid(userid);
                                caseInfoQuery.setCaseorgid(user.getOrgid());
                                queryflag=true;
                                break;
                            }
                        }
                    }
                    if (queryflag) {
                        list = findList(caseInfoQuery);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("findList error,CaseInfoQuery=" + caseInfoQuery==null?"null":caseInfoQuery.toLogString(), e);
            throw e;
        }
        return list;
    }

    @Override
    public List<CaseInfo> findComputeFieldList(CaseInfoQuery caseInfoQuery, String userid) throws Exception {
        List<CaseInfo> list = null;
        try {
            if (caseInfoQuery!=null&&!StringUtils.isEmpty(userid)) {
                List<UserRole> userRoles = userRoleService.findByUserid(userid);
                if (!CollectionUtils.isEmpty(userRoles)) {
                    boolean queryflag = false;
                    for (UserRole userRole : userRoles) {
                        long roleid = userRole.getRoleid();
                        Role role = roleService.findById(roleid);
                        if (role != null) {
                            String roletag = role.getRoletag();
                            //部门负责人和诉讼负责人和团队负责人,都可以查询所有案件,主办律师,只能查询诉讼律师或执行律师是自已的.
                            //客户的负责人能查看他们单位的所有案件,客户经理只能查询自已经办的案件
                            if (RoleTagEnum.LAWYER.getRoletag().equals(roletag)) {
                                caseInfoQuery.setLawyer(userid);
                                queryflag=true;
                                break;
                            }else if (RoleTagEnum.LAWYER_SS_DEPT_MANAGER.getRoletag().equals(roletag)||RoleTagEnum.LAWYER_EXECUTE_DEPT_MANAGER.getRoletag().equals(roletag)||RoleTagEnum.LAWYER_COMPANY_MANAGER.getRoletag().equals(roletag)) {
                                queryflag=true;
                                break;
                            }else if (RoleTagEnum.CUSTOM.getRoletag().equals(roletag)) {
                                caseInfoQuery.setContact(userid);
                                queryflag=true;
                                break;
                            }else if (RoleTagEnum.CUSTOM_COMPANY_MANAGER.getRoletag().equals(roletag)||RoleTagEnum.CUSTOM_DEPT_MANAGER.getRoletag().equals(roletag)) {
                                User user = userService.findByUserid(userid);
                                caseInfoQuery.setCaseorgid(user.getOrgid());
                                queryflag=true;
                                break;
                            }
                        }
                    }
                    if (queryflag) {
                        list = caseInfoDao.findComputeFieldList(caseInfoQuery);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("findList error,CaseInfoQuery=" + caseInfoQuery==null?"null":caseInfoQuery.toLogString(), e);
            throw e;
        }
        return list;
    }

    @Override
    public PageVo<CaseInfo> findListByPage(CaseInfoQuery caseInfoQuery,String userid)throws Exception {
        logger.info("findListByPage begin,caseInfoQuery=" + caseInfoQuery==null?"null":caseInfoQuery.toLogString());
        PageVo<CaseInfo> pageVo = new PageVo<CaseInfo>();
        try {
            if (caseInfoQuery != null&&!StringUtils.isEmpty(userid)) {
                int page = caseInfoQuery.getPage();
                if (page < 1) {
                    page = 1;
                }
                int pagesize = caseInfoQuery.getPageSize();
                if (pagesize < 1) {
                    pagesize = 20;
                }
                int startRow = (page - 1) * pagesize;
                List<UserRole> userRoles = userRoleService.findByUserid(userid);
                if (!CollectionUtils.isEmpty(userRoles)) {
                    boolean queryflag = false;
                    for (UserRole userRole : userRoles) {
                        long roleid = userRole.getRoleid();
                        Role role = roleService.findById(roleid);
                        if (role != null) {
                            String roletag = role.getRoletag();
                            //部门负责人和诉讼负责人和团队负责人,都可以查询所有案件,主办律师,只能查询诉讼律师或执行律师是自已的.
                            //客户的负责人能查看他们单位的所有案件,客户经理只能查询自已经办的案件
                            if (RoleTagEnum.LAWYER.getRoletag().equals(roletag)) {
                                caseInfoQuery.setLawyer(userid);
                                queryflag=true;
                                break;
                            }else if (RoleTagEnum.LAWYER_SS_DEPT_MANAGER.getRoletag().equals(roletag)||RoleTagEnum.LAWYER_EXECUTE_DEPT_MANAGER.getRoletag().equals(roletag)||RoleTagEnum.LAWYER_COMPANY_MANAGER.getRoletag().equals(roletag)) {
                                queryflag=true;
                                break;
                            }else if (RoleTagEnum.CUSTOM.getRoletag().equals(roletag)) {
                                caseInfoQuery.setContact(userid);
                                queryflag=true;
                                break;
                            }else if (RoleTagEnum.CUSTOM_COMPANY_MANAGER.getRoletag().equals(roletag)||RoleTagEnum.CUSTOM_DEPT_MANAGER.getRoletag().equals(roletag)) {
                                User user = userService.findByUserid(userid);
                                caseInfoQuery.setCaseorgid(user.getOrgid());
                                queryflag=true;
                                break;
                            }
                        }
                    }
                    if (queryflag) {
                        caseInfoQuery.setPageSize(pagesize);
                        caseInfoQuery.setStartRow(startRow);
                        int total = this.count(caseInfoQuery);
                        if (total>0) {
                            List<CaseInfo> list = caseInfoDao.findListByPage(caseInfoQuery);
                            pageVo.setList(list);
                            pageVo.setPage(page);
                            pageVo.setPagesize(pagesize);
                            pageVo.setTotal(total);
                            if (total > (page * pagesize)) {
                                pageVo.setHavenext(true);
                            }else{
                                pageVo.setHavenext(false);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("findListByPage error,caseInfoQuery=" + caseInfoQuery==null?"null":caseInfoQuery.toLogString(), e);
            throw e;
        }
        return pageVo;
    }

    @Override
    public int count(CaseInfoQuery caseInfoQuery)throws Exception {
        logger.info("count begin,CaseInfoQuery=" + caseInfoQuery==null?"null":caseInfoQuery.toLogString());
        int effectrows = 0;
        try {
            effectrows = caseInfoDao.count(caseInfoQuery);
        } catch (Exception e) {
            logger.error("count error,CaseInfoQuery=" + caseInfoQuery==null?"null":caseInfoQuery.toLogString(), e);
            throw e;
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int deleteCaseinfoOnlyById(long id)throws Exception {
        logger.info("deleteById begin,id=" + id);
        int effectrows = 0;
        try {
            effectrows = caseInfoDao.deleteById(id);
        } catch (Exception e) {
            logger.error("deleteById error,id=" + id, e);
            throw e;
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int updateStatus(long caseinfoid, int status) throws Exception {
        int effectrows = 0;
        try {
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setId(caseinfoid);
            caseInfo.setStatus(status);
            effectrows = caseInfoDao.updateStatus(caseInfo);
        } catch (Exception e) {
            logger.error("updateStatus error,id="+caseinfoid+",status:"+status, e);
            throw e;
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int updateStatusFinish(long caseinfoid) throws Exception {
        int effectrows = 0;
        try {
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setId(caseinfoid);
            caseInfo.setStatus(SysConstants.CASEINFO_STATUS_ALL_FINISHED);
            effectrows = caseInfoDao.updateStatus(caseInfo);
        } catch (Exception e) {
            logger.error("updateStatusFinish error,id="+caseinfoid, e);
            throw e;
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int updateYstj(long caseinfoid, int ystj) throws Exception {
        int effectrows = 0;
        try {
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setId(caseinfoid);
            caseInfo.setYstj(ystj);
            effectrows = caseInfoDao.updateYstj(caseInfo);
        } catch (Exception e) {
            logger.error("updateYstj error,id="+caseinfoid+",ystj:"+ystj, e);
            throw e;
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int updateEstj(long caseinfoid, int estj) throws Exception {
        int effectrows = 0;
        try {
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setId(caseinfoid);
            caseInfo.setEstj(estj);
            effectrows = caseInfoDao.updateEstj(caseInfo);
        } catch (Exception e) {
            logger.error("updateEstj error,id="+caseinfoid+",estj:"+estj, e);
            throw e;
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int updateSfss(long caseinfoid, int sfss) throws Exception {
        int effectrows = 0;
        try {
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setId(caseinfoid);
            caseInfo.setSfss(sfss);
            effectrows = caseInfoDao.updateSfss(caseInfo);
        } catch (Exception e) {
            logger.error("updateEstj error,id="+caseinfoid+",sfss:"+sfss, e);
            throw e;
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int updateSsajbh(long caseinfoid, String ssajbh) throws Exception {
        int effectrows = 0;
        try {
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setId(caseinfoid);
            caseInfo.setSsajbh(ssajbh);
            effectrows = caseInfoDao.updateSsajbh(caseInfo);
        } catch (Exception e) {
            logger.error("updateSsajbh error,caseinfoid="+caseinfoid+",ssajbh:"+ssajbh, e);
            throw e;
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int updatePrePrice(long caseinfoid, double prePrice) throws Exception {
        int effectrows = 0;
        try {
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setId(caseinfoid);
            caseInfo.setPreprice(prePrice);
            effectrows = caseInfoDao.updatePrePrice(caseInfo);
        } catch (Exception e) {
            logger.error("updatePrePrice error,id="+caseinfoid+",prePrice:"+prePrice, e);
            throw e;
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int updateSufPrice(long caseinfoid, double sufPrice) throws Exception {
        int effectrows = 0;
        try {
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setId(caseinfoid);
            caseInfo.setSufprice(sufPrice);
            effectrows = caseInfoDao.updateSufPrice(caseInfo);
        } catch (Exception e) {
            logger.error("updateSufPrice error,id="+caseinfoid+",sufPrice:"+sufPrice, e);
            throw e;
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int updateExeLawyers(long caseinfoid,String exeajbh, String exeLawyers, String exeLawyerids) throws Exception {
        int effectrows = 0;
        try {
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setId(caseinfoid);
            caseInfo.setExelawyers(exeLawyers);
            caseInfo.setExeajbh(exeajbh);
            effectrows = caseInfoDao.updateExeLawyers(caseInfo);
            if (!StringUtils.isEmpty(exeLawyerids)) {
                String[] exeLawyerIdArr = exeLawyerids.split(",");
                for (int i = 0; i < exeLawyerIdArr.length; i++) {
                    CaseInfoUser caseInfoUser = new CaseInfoUser();
                    caseInfoUser.setCaseinfoid(caseinfoid);
                    caseInfoUser.setUserid(exeLawyerIdArr[i]);
                    caseInfoUser.setUsertype(SysConstants.CASEINFO_EXELAWYER_TYPE);
                    caseInfoUserService.save(caseInfoUser);
                }
            }
        } catch (Exception e) {
            logger.error("updateExeLawyers error,id="+caseinfoid+",exeLawyerids:"+exeLawyerids, e);
            throw e;
        }
        return effectrows;
    }

    @Override
    public boolean ifAllowd(String userid, long caseinfoid)throws Exception {
        try {
            List<UserRole> userRoles = userRoleService.findByUserid(userid);
            if (!CollectionUtils.isEmpty(userRoles)) {
                boolean queryflag = false;
                CaseInfoQuery caseInfoQuery = new CaseInfoQuery();
                for (UserRole userRole : userRoles) {
                    long roleid = userRole.getRoleid();
                    Role role = roleService.findById(roleid);
                    if (role != null) {
                        String roletag = role.getRoletag();
                        //部门负责人和诉讼负责人和团队负责人,都可以查询所有案件,主办律师,只能查询诉讼律师或执行律师是自已的.
                        //客户的负责人能查看他们单位的所有案件,客户经理只能查询自已经办的案件
                        if (RoleTagEnum.LAWYER.getRoletag().equals(roletag)) {
                            caseInfoQuery.setLawyer(userid);
                            queryflag=true;
                            break;
                        }else if (RoleTagEnum.LAWYER_SS_DEPT_MANAGER.getRoletag().equals(roletag)||RoleTagEnum.LAWYER_EXECUTE_DEPT_MANAGER.getRoletag().equals(roletag)||RoleTagEnum.LAWYER_COMPANY_MANAGER.getRoletag().equals(roletag)) {
                            queryflag=true;
                            break;
                        }else if (RoleTagEnum.CUSTOM.getRoletag().equals(roletag)) {
                            caseInfoQuery.setContact(userid);
                            queryflag=true;
                            break;
                        }else if (RoleTagEnum.CUSTOM_COMPANY_MANAGER.getRoletag().equals(roletag)||RoleTagEnum.CUSTOM_DEPT_MANAGER.getRoletag().equals(roletag)) {
                            User user = userService.findByUserid(userid);
                            caseInfoQuery.setCaseorgid(user.getOrgid());
                            queryflag=true;
                            break;
                        }
                    }
                }
                if (queryflag) {
                    caseInfoQuery.setId(caseinfoid);
                    List<CaseInfo> list = caseInfoDao.findComputeFieldList(caseInfoQuery);
                    if (!CollectionUtils.isEmpty(list)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("ifAllowd error,userid=" + caseinfoid + ",caseinfoid:" + caseinfoid, e);
            throw e;
        }
        return false;
    }
}
