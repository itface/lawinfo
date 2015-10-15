package com.lawinfo.dao.org;

import com.lawinfo.domain.org.CaseProgress;
import com.lawinfo.domain.org.query.CaseProgressQuery;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/13.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback=false)
public class CaseProgressDaoTest {
    @Resource
    private CaseProgressDao caseProgressDao;


    @Test
//    @Rollback(true)
    public void testSave() {
        int effectRows =0;
        CaseProgress caseProgress = new CaseProgress();
        caseProgress.setCaseid("cs001");
        caseProgress.setCasenodeid(1);
        caseProgress.setComment("提交案卷");
        caseProgress.setNexttask("审查案卷");
        Date date = new Date();
        long now = date.getTime();
        String nowstr = com.lawinfo.service.util.DateUtils.formatDatetime(date);
        caseProgress.setCreatetime(now);
        caseProgress.setCreatetimestr(nowstr);
        caseProgress.setModifiedtime(now);
        caseProgress.setModifiedtimestr(nowstr);
        caseProgress.setOptuserid("admin");
        effectRows = caseProgressDao.save(caseProgress);
        CaseProgress caseProgress2 = new CaseProgress();
        caseProgress2.setCaseid("cs001");
        caseProgress2.setCasenodeid(1);
        caseProgress2.setComment("更新提交案卷");
        caseProgress2.setNexttask("重复审查案卷");
        Date date2 = new Date();
        long now2 = date.getTime();
        String nowstr2 = com.lawinfo.service.util.DateUtils.formatDatetime(date2);
        caseProgress2.setCreatetime(now2);
        caseProgress2.setCreatetimestr(nowstr2);
        caseProgress2.setModifiedtime(now2);
        caseProgress2.setModifiedtimestr(nowstr2);
        caseProgress2.setOptuserid("admin");
        effectRows+= caseProgressDao.save(caseProgress2);
        CaseProgress caseProgress3 = new CaseProgress();
        caseProgress3.setCaseid("cs001");
        caseProgress3.setCasenodeid(2);
        caseProgress3.setComment("准备开庭");
        caseProgress3.setNexttask("审判");
        Date date3 = new Date();
        long now3 = date.getTime();
        String nowstr3 = com.lawinfo.service.util.DateUtils.formatDatetime(date3);
        caseProgress3.setCreatetime(now3);
        caseProgress3.setCreatetimestr(nowstr3);
        caseProgress3.setModifiedtime(now3);
        caseProgress3.setModifiedtimestr(nowstr3);
        caseProgress3.setOptuserid("admin");
        effectRows+= caseProgressDao.save(caseProgress3);
        Assert.assertTrue(effectRows==3);
    }
}
