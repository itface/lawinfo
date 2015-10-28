package com.lawinfo.service.org.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.lawinfo.dao.org.OrgDao;
import com.lawinfo.domain.org.Org;
import com.lawinfo.domain.org.query.OrgQuery;
import com.lawinfo.domain.org.vo.OrgVo;
import com.lawinfo.service.org.OrgService;
import com.lawinfo.service.org.utils.OrgInfoUtils;
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
    private OrgDao orgInfoDao;

    @Override
    public void initCache() throws Exception {
        try {
            List<Org> orgList = this.findAllFromDb();
            if (!CollectionUtils.isEmpty(orgList)) {
                OrgInfoUtils.init();
                for (Org org : orgList) {
                    OrgInfoUtils.add(org);
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
    @Override
    public List<OrgVo> findOrgTree() throws Exception {
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
            Collection<Org> orgs = orgMultimap.get(root.getId());
            List<OrgVo> orgVoList2 = new ArrayList<OrgVo>();
            for (Org org : orgs) {
                buildOrgTree(orgMultimap,root);
            }
            orgVoList.add(root);
            return orgVoList;
        }
        return null;
    }

    @Override
    public List<Org> findAll() throws Exception{
        List<Org> list = null;
        try {
            list = OrgInfoUtils.findAll();
            list = null;
            if (CollectionUtils.isEmpty(list)) {
                list = orgInfoDao.findAll();
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
            list = orgInfoDao.findAll();
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
                effectrows = orgInfoDao.save(org);
                logger.info("save success,effectrows:"+effectrows+","+ org.getName());
            }
        } catch (Exception e) {
            logger.error("findAll error,"+ org ==null?"null": org.getName(),e);
            throw e;
        }
        return effectrows;
    }

    @Override
    public Org findById(long id)throws Exception {
        logger.info("findById begin,id:"+id);
        Org org = null;
        try {
            org =orgInfoDao.findById(id);
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
            list = orgInfoDao.findList(orgInfoQuery);
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
            list = orgInfoDao.findListByPage(orgInfoQuery);
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
            effectrows = orgInfoDao.count(orgInfoQuery);
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
            effectrows = orgInfoDao.deleteById(id);
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
            List<Org> orgs = orgInfoDao.findByParentorgid(parentorgid);
            if (!CollectionUtils.isEmpty(orgs)) {
                for (Org org : orgs) {
                    deleteByParentorgid(org.getId());
                }
            }
            orgInfoDao.deleteByParentorgid(parentorgid);
        } catch (Exception e) {
            logger.error("deleteByParentorgid error,parentorgid=" + parentorgid, e);
        }
    }
}
