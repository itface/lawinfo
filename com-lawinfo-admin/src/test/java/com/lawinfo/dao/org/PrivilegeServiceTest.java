package com.lawinfo.dao.org;

import com.lawinfo.domain.org.Privilege;
import com.lawinfo.domain.org.query.PrivilegeQuery;
import com.lawinfo.service.org.PrivilegeService;
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
public class PrivilegeServiceTest {
    @Resource
    private PrivilegeService privilegeService;


    @Test
//    @Rollback(true)
    public void testSave() throws Exception{
        int effectRows =0;
        Date date = new Date();
        long now = date.getTime();
        String nowstr = com.lawinfo.service.util.DateUtils.formatDatetime(date);
        Privilege privilege = new Privilege();
        privilege.setName("诉讼");
        privilege.setPrivilegeid(1);
        privilege.setCreatetime(now);
        privilege.setCreatetimestr(nowstr);
        privilege.setModifiedtime(now);
        privilege.setModifiedtimestr(nowstr);
        privilege.setOptuserid("admin");
        effectRows = privilegeService.save(privilege);
        Privilege privilege2 = new Privilege();
        privilege2.setName("执行");
        privilege2.setPrivilegeid(2);
        privilege2.setCreatetime(now);
        privilege2.setCreatetimestr(nowstr);
        privilege2.setModifiedtime(now);
        privilege2.setModifiedtimestr(nowstr);
        privilege2.setOptuserid("admin");
        effectRows += privilegeService.save(privilege2);
        Privilege privilege3 = new Privilege();
        privilege3.setName("创建案件");
        privilege3.setPrivilegeid(3);
        privilege3.setCreatetime(now);
        privilege3.setCreatetimestr(nowstr);
        privilege3.setModifiedtime(now);
        privilege3.setModifiedtimestr(nowstr);
        privilege3.setOptuserid("admin");
        effectRows += privilegeService.save(privilege3);
        Assert.assertTrue(effectRows==3);
    }

    @Test
    public void tsetFind() throws Exception{
        List<Privilege> list = privilegeService.findAll();
        System.out.println(list.size());
    }

}
