package com.lawinfo.dao.org;

import com.lawinfo.domain.org.OrgInfo;
import com.lawinfo.domain.org.query.OrgInfoQuery;
import com.lawinfo.service.org.OrgInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/13.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback=false)
public class OrgInfoDaoTest {
    @Resource
    private OrgInfoDao orgInfoDao;


    @Test
//    @Rollback(true)
    public void testSave() {
        int effectRows =0;
        OrgInfo orgInfo = new OrgInfo();
        orgInfo.setName("yahoo");
        orgInfo.setOrgtype(4);
        effectRows = orgInfoDao.save(orgInfo);
        OrgInfo orgInfo2 = new OrgInfo();
        orgInfo2.setName("ms");
        orgInfo2.setOrgtype(4);
        effectRows+= orgInfoDao.save(orgInfo2);
        OrgInfo orgInfo3 = new OrgInfo();
        orgInfo3.setName("google");
        orgInfo3.setOrgtype(5);
        effectRows+= orgInfoDao.save(orgInfo3);
        Assert.assertTrue(effectRows==3);
    }
    @Test
    public void testFindList() {
        OrgInfoQuery orgInfoQuery = new OrgInfoQuery();
        orgInfoQuery.setOrgtype(4);
        List<OrgInfo> list = orgInfoDao.findList(orgInfoQuery);
        Assert.assertTrue(list!=null&&list.size()==2);
    }
    @Test
    public void testCount() {
        OrgInfoQuery orgInfoQuery = new OrgInfoQuery();
        int count = orgInfoDao.count(orgInfoQuery);
        orgInfoQuery.setOrgtype(1);
        int count2 = orgInfoDao.count(orgInfoQuery);
        System.out.println(count+":"+count2);
    }
    @Test
    public void testFindListByPage() {
        OrgInfoQuery orgInfoQuery = new OrgInfoQuery();
        orgInfoQuery.setOrgtype(1);
        orgInfoQuery.setPageSize(3);
        orgInfoQuery.setStartRow(3);
        List<OrgInfo> list = orgInfoDao.findListByPage(orgInfoQuery);
        System.out.println(list.size());
    }
    @Test
    public void testFindById() {
        OrgInfo orgInfo = orgInfoDao.findById(2);
        Assert.assertTrue(orgInfo.getId()==2);
    }
    @Test
    public void testDelete() {
        int count = orgInfoDao.deleteById(3);
        Assert.assertTrue(count==1);
    }
}
