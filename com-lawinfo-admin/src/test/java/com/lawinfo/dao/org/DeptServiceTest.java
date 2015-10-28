package com.lawinfo.dao.org;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangrongtao on 15/10/13.
 */

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback=false)*/
public class DeptServiceTest {
    /*@Resource
    private DeptService deptService;


    @Test
//    @Rollback(true)
    public void testSave() throws Exception{
        int effectRows =0;
        Date date = new Date();
        long now = date.getTime();
        String nowstr = com.lawinfo.service.util.DateUtils.formatDatetime(date);
        Dept dept = new Dept();
        dept.setName("诉讼部");
        dept.setOrgid(2);
        dept.setCreatetime(now);
        dept.setCreatetimestr(nowstr);
        dept.setModifiedtime(now);
        dept.setModifiedtimestr(nowstr);
        dept.setOptuserid("admin");
        effectRows = deptService.save(dept);
        Dept dept2 = new Dept();
        dept2.setName("执行部");
        dept2.setOrgid(2);
        dept2.setCreatetime(now);
        dept2.setCreatetimestr(nowstr);
        dept2.setModifiedtime(now);
        dept2.setModifiedtimestr(nowstr);
        dept2.setOptuserid("admin");
        effectRows += deptService.save(dept2);
        Assert.assertTrue(effectRows==2);
    }

    @Test
    public void tsetFind() throws Exception{
        DeptQuery deptQuery = new DeptQuery();
        deptQuery.setOrgid(2);
        deptQuery.setId(3);
        List<Dept> list = deptService.findList(deptQuery);
        System.out.println(list.size());
    }*/

}
