package com.lawinfo.common.sourcegenerate;


import com.lawinfo.domain.front.CaseInfo;
import com.lawinfo.domain.front.query.CaseInfoQuery;

/**
 * Created by wangrongtao on 16/4/21.
 */
public class GenAll {

    public static void main(String[] args) {
        GenMyBatisXmlUtil myBatisXmlUtil = new GenMyBatisXmlUtil();
        GenVmSourceUtil genVmSourceUtil = new GenVmSourceUtil();
        GenDaoSourceUtil genDaoSourceUtil = new GenDaoSourceUtil();
        GenServiceInterfaceUtil genServiceInterfaceUtil = new GenServiceInterfaceUtil();
        GenServiceImplUtil genServiceImplUtil = new GenServiceImplUtil();
        GenControllerUtil controllerGenerator = new GenControllerUtil();
        Class claxx = CaseInfo.class;
        Class queryClass = CaseInfoQuery.class;
        String baseurl = "/lawinfo/front/case";
        String caption = "案件管理";
        myBatisXmlUtil.gen(claxx, queryClass);
        genVmSourceUtil.gen(claxx,baseurl,caption);
        genDaoSourceUtil.gen(claxx,queryClass);
        genServiceInterfaceUtil.gen(claxx,queryClass);
        genServiceImplUtil.gen(claxx,queryClass);
        controllerGenerator.gen(claxx,queryClass,baseurl,"/front");
    }
}
