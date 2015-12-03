package com.lawinfo.service.org.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.lawinfo.dao.org.OrgDao;
import com.lawinfo.domain.org.Org;
import com.lawinfo.domain.org.query.OrgQuery;
import com.lawinfo.domain.org.vo.OrgVo;
import com.lawinfo.service.constant.SysConstants;
import com.lawinfo.service.org.OrgService;
import com.lawinfo.service.org.utils.OrgUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by wangrongtao on 15/10/12.
 */
@Service
public class OrgServiceImpl implements OrgService{
    private static Logger logger = LoggerFactory.getLogger(OrgServiceImpl.class);

    @Resource
    private OrgDao orgDao;

    @Override
    public void initCache() throws Exception {
        try {
            OrgUtils.init();
            List<Org> orgList = this.findAllFromDb();
            if (!CollectionUtils.isEmpty(orgList)) {
                for (Org org : orgList) {
                    OrgUtils.add(org);
                }
            }
        } catch (Exception e) {
            logger.error("initcache exception",e);
            throw e;
        }
    }

    /*public static void main(String[] args) {
        Org org = new Org();
        org.setId(1l);
        org.setName("明礼事务所");
        org.setParentorgid(0l);
        Org org2 = new Org();
        org2.setId(2l);
        org2.setName("诉讼部");
        org2.setParentorgid(1l);
        Org org3 = new Org();
        org3.setId(3l);
        org3.setName("执行部");
        org3.setParentorgid(1l);
        Org org4 = new Org();
        org4.setId(4l);
        org4.setName("执行主部律师");
        org4.setParentorgid(3l);
        Org org5 = new Org();
        org5.setId(5l);
        org5.setName("赣州银行");
        org5.setParentorgid(0l);
        Org org6 = new Org();
        org6.setId(6l);
        org6.setName("赣县支行");
        org6.setParentorgid(5l);
        Multimap<Long, Org> orgMultimap = ArrayListMultimap.create();
        orgMultimap.put(0l, org);
        orgMultimap.put(0l, org5);
        orgMultimap.put(1l, org2);
        orgMultimap.put(1l, org3);
        orgMultimap.put(3l, org4);
        orgMultimap.put(5l, org6);
        OrgVo vo = new OrgVo();
        vo.setId(0l);
        vo.setParentorgid(-1l);
        vo.setText("机构");
        OrgVo orgVo = buildOrgTree(orgMultimap,vo);
        System.out.println(JSON.toJSONString(orgVo));
    }*/
    private  void buildOrgTree(Multimap<Long, Org> orgMultimap,OrgVo orgVo) {
        Collection<Org> orgs = orgMultimap.get(orgVo.getId());
        if (!CollectionUtils.isEmpty(orgs)) {
            List<OrgVo> orgVoList = new ArrayList<OrgVo>();
            for (Org org : orgs) {
                OrgVo orgVo1 = new OrgVo();
                orgVo1.setId(org.getId());
                orgVo1.setText(org.getName());
                orgVo1.setParentorgid(orgVo.getId());
                buildOrgTree(orgMultimap, orgVo1);
                orgVoList.add(orgVo1);
            }
            Collections.sort(orgVoList);
            orgVo.setNodes(orgVoList);
        }
    }
    public List<OrgVo> findSubOrgTree(long orgid) throws Exception {
        List<OrgVo> orgVoList = new ArrayList<OrgVo>();
        try {
            List<Org> list = null;
            list = OrgUtils.findSubOrg(orgid);
            if (CollectionUtils.isEmpty(list)) {
                OrgQuery orgQuery = new OrgQuery();
                orgQuery.setParentorgid(orgid);
                list = findList(orgQuery);
            }
            if (!CollectionUtils.isEmpty(list)) {
                Multimap<Long, Org> multimap = findAllOrg();
                if (multimap != null) {
                    for (Org org : list) {
                        OrgVo orgVo = new OrgVo();
                        orgVo.setId(org.getId());
                        orgVo.setText(org.getName());
                        orgVo.setParentorgid(org.getParentorgid());
                        buildOrgTree(multimap, orgVo);
                        orgVoList.add(orgVo);
                    }
                }
                Collections.sort(orgVoList);
            }
        } catch (Exception e) {
            logger.error("findSubOrgTree exception",e);
            throw e;
        }
        return orgVoList;
    }
    private Multimap<Long,Org> findAllOrg()throws Exception{
        Multimap<Long, Org> orgMultimap = ArrayListMultimap.create();
        List<Org> list = findAll();
        if (!CollectionUtils.isEmpty(list)) {
            for (Org org : list) {
                orgMultimap.put(org.getParentorgid(), org);
            }
        }
        return orgMultimap;
    }
    @Override
    public List<OrgVo> findOrgTree() throws Exception {
        try {
            List<Org> list = findAll();
            if (!CollectionUtils.isEmpty(list)) {
                List<OrgVo> orgVoList = new ArrayList<OrgVo>();
                Multimap<Long, Org> orgMultimap = ArrayListMultimap.create();
                for (Org org : list) {
                    orgMultimap.put(org.getParentorgid(), org);
                }
                OrgVo root = new OrgVo();
                root.setId(0l);
                root.setParentorgid(-1l);
                root.setText("机构");
                /*Collection<Org> orgs = orgMultimap.get(root.getId());
                List<OrgVo> orgVoList2 = new ArrayList<OrgVo>();
                for (Org org : orgs) {
                    buildOrgTree(orgMultimap,root);
                }*/
                buildOrgTree(orgMultimap,root);
                orgVoList.add(root);
                return orgVoList;
            }
        } catch (Exception e) {
            logger.error("findOrgTree exception", e);
            throw e;
        }
        return null;
    }

    @Override
    public List<OrgVo> findCustomTree() throws Exception {
        try {
            List<Org> list = findAll();
            if (!CollectionUtils.isEmpty(list)) {
                List<OrgVo> orgVoList = new ArrayList<OrgVo>();
                Multimap<Long, Org> orgMultimap = ArrayListMultimap.create();
                for (Org org : list) {
                    /**
                     * 不显示律所节点
                     */
                    if (SysConstants.ORG_TREE_NODE_NAME_OF_LAW_OFFICE.equals(org.getName())) {
                        continue;
                    }
                    orgMultimap.put(org.getParentorgid(), org);
                }
                OrgVo root = new OrgVo();
                root.setId(0l);
                root.setParentorgid(-1l);
                root.setText("所有");
                /*Collection<Org> orgs = orgMultimap.get(root.getId());
                List<OrgVo> orgVoList2 = new ArrayList<OrgVo>();
                for (Org org : orgs) {
                    buildOrgTree(orgMultimap,root);
                }*/
                buildOrgTree(orgMultimap,root);
                orgVoList.add(root);
                return orgVoList;
            }
        } catch (Exception e) {
            logger.error("findCustomTree exception", e);
            throw e;
        }
        return null;
    }

    @Override
    public List<OrgVo> findLawyerTree() throws Exception {
        try {
            OrgQuery orgQuery = new OrgQuery();
            orgQuery.setName(SysConstants.ORG_TREE_NODE_NAME_OF_LAW_OFFICE);
            List<Org> list = findList(orgQuery);
            if (!CollectionUtils.isEmpty(list)) {
                List<OrgVo> orgList = new ArrayList<OrgVo>();
                Org org = list.get(0);
                List<OrgVo> orgTree = findSubOrgTree(org.getId());
                OrgVo root = new OrgVo();
                root.setId(org.getId());
                root.setParentorgid(org.getParentorgid());
                root.setText(org.getName());
                root.setNodes(orgTree);
                orgList.add(root);
                return orgList;
            }
        } catch (Exception e) {
            logger.error("findLawyerTree exception", e);
            throw e;
        }
        return null;
    }

    @Override
    public List<Org> findAll() throws Exception{
        List<Org> list = null;
        try {
            list = OrgUtils.findAll();
            if (CollectionUtils.isEmpty(list)) {
                list = findAllFromDb();
            }
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return list;
    }

    @Override
    public List<Org> findAllFromDb() throws Exception {
        List<Org> list = null;
        try {
            list = orgDao.findAll();
        } catch (Exception e) {
            logger.error("findAllFromDb error",e);
            throw e;
        }
        return list;
    }

    @Override
    @Transactional
    public int save(Org org)throws Exception {
        int effectrows = 0;
        try {
            if (org !=null) {
                org.initBaseDomain();
                effectrows = orgDao.save(org);
                logger.info("save success,effectrows:"+effectrows+","+ org.getName());
            }
        } catch (Exception e) {
            logger.error("findAll error,",e);
            throw e;
        }
        return effectrows;
    }

    @Override
    public Org findById(long id)throws Exception {
        logger.info("findById begin,id:"+id);
        Org org = null;
        try {
            org = OrgUtils.findById(id);
            if (org==null) {
                org = orgDao.findById(id);
            }
        } catch (Exception e) {
            logger.error("findById error,id=" + id, e);
            throw e;
        }
        return org;
    }

    @Override
    public List<Org> findList(OrgQuery orgInfoQuery)throws Exception {
        logger.info("findList begin,OrgQuery=" + orgInfoQuery == null ? "null" : orgInfoQuery.toLogString());
        List<Org> list = null;
        try {
            list = orgDao.findList(orgInfoQuery);
        } catch (Exception e) {
            logger.error("findList error,OrgQuery=" + orgInfoQuery==null?"null":orgInfoQuery.toLogString(), e);
        }
        return list;
    }

    @Override
    public List<Org> findListByPage(OrgQuery orgInfoQuery)throws Exception {
        logger.info("findListByPage begin,OrgQuery=" + orgInfoQuery==null?"null":orgInfoQuery.toLogString());
        List<Org> list = null;
        try {
            list = orgDao.findListByPage(orgInfoQuery);
        } catch (Exception e) {
            logger.error("findListByPage error,OrgQuery=" + orgInfoQuery==null?"null":orgInfoQuery.toLogString(), e);
        }
        return list;
    }

    @Override
    public int count(OrgQuery orgInfoQuery)throws Exception {
        logger.info("count begin,OrgQuery=" + orgInfoQuery==null?"null":orgInfoQuery.toLogString());
        int effectrows = 0;
        try {
            effectrows = orgDao.count(orgInfoQuery);
        } catch (Exception e) {
            logger.error("count error,OrgQuery=" + orgInfoQuery==null?"null":orgInfoQuery.toLogString(), e);
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int deleteById(long id)throws Exception {
        logger.info("deleteById begin,id=" + id);
        int effectrows = 0;
        try {
            deleteByParentorgid(id);
            effectrows = orgDao.deleteById(id);
        } catch (Exception e) {
            logger.error("deleteById error,id=" + id, e);
        }
        return effectrows;
    }

    @Override
    @Transactional
    public void deleteByParentorgid(long parentorgid) throws Exception {
        logger.info("deleteByParentorgid begin,parentorgid=" + parentorgid);
        try {
            List<Org> orgs = OrgUtils.findSubOrg(parentorgid);
            if (CollectionUtils.isEmpty(orgs)) {
                orgs = orgDao.findByParentorgid(parentorgid);
            }
            if (!CollectionUtils.isEmpty(orgs)) {
                for (Org org : orgs) {
                    deleteByParentorgid(org.getId());
                }
            }
            orgDao.deleteByParentorgid(parentorgid);
        } catch (Exception e) {
            logger.error("deleteByParentorgid error,parentorgid=" + parentorgid, e);
        }
    }
}
