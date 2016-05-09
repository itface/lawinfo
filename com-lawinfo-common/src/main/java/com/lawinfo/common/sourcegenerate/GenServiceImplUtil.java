package com.lawinfo.common.sourcegenerate;



import java.io.File;

/**
 * Created by wangrongtao on 16/4/21.
 */
public class GenServiceImplUtil {
    private final String BREAK_ROW = "\n";
    private final String TAB_1 = "\t";
    private final String TAB_2 = TAB_1 + "\t";

    private String getDomainname(Class clazz){
        return clazz.getSimpleName();
    }
    private String getDomainValname(Class clazz){
        String domainname = getDomainname(clazz);
        String domainvalname = domainname.substring(0,1).toLowerCase()+domainname.substring(1);
        return domainvalname;
    }
    private String getServiceName(Class clazz){
        return clazz.getSimpleName()+"Service";
    }
    private String getServiceImplName(Class clazz){
        return clazz.getSimpleName()+"ServiceImpl";
    }
    private String getDaoName(Class clazz){
        return clazz.getSimpleName() + "Dao";
    }
    private String getDaoValName(Class clazz){
        String daoname = getDaoName(clazz);
        String daovalname = daoname.substring(0,1).toLowerCase()+daoname.substring(1);
        return daovalname;
    }
    private String getQueryObjName(Class clazz){
        return clazz.getSimpleName()+"Query";
    }
    private String getQueryObjValName(Class clazz){
        String queryObjName = getQueryObjName(clazz);
        return queryObjName.substring(0,1).toLowerCase()+queryObjName.substring(1);
    }
    private String getPackage(Class clazz){
        StringBuilder sb = new StringBuilder();
        String packagename = clazz.getPackage().getName();
        String daoPackagename = packagename.replace("domain", "service")+".impl";
        sb.append("package ").append(daoPackagename).append(";").append(BREAK_ROW).append(BREAK_ROW);
        return sb.toString();
    }

    private String getImport(Class clazz,Class queryClass){
        StringBuilder sb = new StringBuilder();
        String servicepackagename = clazz.getName().replace("domain","service")+"Service";
        String daopackagename = clazz.getName().replace("domain","dao")+"Dao";
        sb.append("import com.infosource.domain.common.PageVo;").append(BREAK_ROW);
        sb.append("import javax.annotation.Resource;").append(BREAK_ROW);
        sb.append("import org.springframework.stereotype.Service;").append(BREAK_ROW);
        sb.append("import java.util.List;").append(BREAK_ROW);
        sb.append("import ").append(clazz.getName()).append(";").append(BREAK_ROW);
        sb.append("import ").append(queryClass.getName()).append(";").append(BREAK_ROW);
        sb.append("import ").append(servicepackagename).append(";").append(BREAK_ROW);
        sb.append("import ").append(daopackagename).append(";").append(BREAK_ROW);
        sb.append(BREAK_ROW);
        sb.append(BREAK_ROW);
        sb.append(BREAK_ROW);
        return sb.toString();
    }
    private String getHeader(Class clazz){
        StringBuilder sb = new StringBuilder();
        sb.append("@Service");
        sb.append("public class ").append(getServiceImplName(clazz)).append(" implements ").append(getServiceName(clazz)).append("{").append(BREAK_ROW).append(BREAK_ROW);
        return sb.toString();
    }
    private String getDependency(String firstTab, Class clazz){
        StringBuilder sb = new StringBuilder();
        String domainname = clazz.getSimpleName();
        sb.append(firstTab).append("@Resource").append(BREAK_ROW);
        sb.append(firstTab).append("private ").append(getDaoName(clazz)).append(" ").append(getDaoValName(clazz)).append(";").append(BREAK_ROW).append(BREAK_ROW);
        return sb.toString();
    }
    private String getFooter(){
        StringBuilder sb = new StringBuilder();
        sb.append("}");
        return sb.toString();
    }
    private String getSave(String firstTab, Class clazz){
        StringBuilder sb = new StringBuilder();
        String secondTab = firstTab+TAB_1;
        sb.append(firstTab).append("@Override").append(BREAK_ROW);
        sb.append(firstTab).append("public int save(").append(getDomainname(clazz)).append(" ").append(getDomainValname(clazz)).append("){").append(BREAK_ROW);
            sb.append(secondTab).append("return ").append(getDaoValName(clazz)).append(".save(").append(getDomainValname(clazz)).append(");").append(BREAK_ROW);
        sb.append(firstTab).append("}").append(BREAK_ROW).append(BREAK_ROW);
        return sb.toString();
    }
    private String getFindAll(String firstTab, Class clazz){
        StringBuilder sb = new StringBuilder();
        String secondTab = firstTab+TAB_1;
        sb.append(firstTab).append("@Override").append(BREAK_ROW);
        sb.append(firstTab).append("public List< ").append(getDomainname(clazz)).append("> findAll(){").append(BREAK_ROW);
            sb.append(secondTab).append("return ").append(getDaoValName(clazz)).append(".findAll();").append(BREAK_ROW);
        sb.append(firstTab).append("}").append(BREAK_ROW).append(BREAK_ROW);
        return sb.toString();
    }
    private String getFindById(String firstTab, Class clazz){
        StringBuilder sb = new StringBuilder();
        String secondTab = firstTab+TAB_1;
        sb.append(firstTab).append("@Override").append(BREAK_ROW);
        sb.append(firstTab).append("public ").append(getDomainname(clazz)).append(" findById(long id){").append(BREAK_ROW);
            sb.append(secondTab).append("return ").append(getDaoValName(clazz)).append(".findById(id);").append(BREAK_ROW);
        sb.append(firstTab).append("}").append(BREAK_ROW).append(BREAK_ROW);
        return sb.toString();
    }
    private String getDeleteById(String firstTab, Class clazz){
        StringBuilder sb = new StringBuilder();
        String secondTab = firstTab+TAB_1;
        sb.append(firstTab).append("@Override").append(BREAK_ROW);
        sb.append(firstTab).append("public int deleteById(long id){").append(BREAK_ROW);
            sb.append(secondTab).append("return ").append(getDaoValName(clazz)).append(".deleteById(id);").append(BREAK_ROW);
        sb.append(firstTab).append("}").append(BREAK_ROW).append(BREAK_ROW);
        return sb.toString();
    }
    private String getFindList(String firstTab, Class clazz){
        StringBuilder sb = new StringBuilder();
        String secondTab = firstTab+TAB_1;
        sb.append(firstTab).append("@Override").append(BREAK_ROW);
        sb.append(firstTab).append("public List< ").append(getDomainname(clazz)).append("> findList(").append(getQueryObjName(clazz)).append(" ").append(getQueryObjValName(clazz)).append("){").append(BREAK_ROW);
            sb.append(secondTab).append("return ").append(getDaoValName(clazz)).append(".findList(").append(getQueryObjValName(clazz)).append(");").append(BREAK_ROW);
        sb.append(firstTab).append("}").append(BREAK_ROW).append(BREAK_ROW);
        return sb.toString();
    }
    private String getFindListByPage(String firstTab, Class clazz){
        StringBuilder sb = new StringBuilder();
        String secondTab = firstTab+TAB_1;
        String thirdTab = secondTab+TAB_1;
        sb.append(firstTab).append("@Override").append(BREAK_ROW);
        sb.append(firstTab).append("public PageVo<").append(getDomainname(clazz)).append("> findListByPage(").append(getQueryObjName(clazz)).append(" ").append(getQueryObjValName(clazz)).append("){").append(BREAK_ROW);
            sb.append(secondTab).append("int count = this.count(").append(getQueryObjValName(clazz)).append(");").append(BREAK_ROW);
            sb.append(secondTab).append("if(count > 0){").append(BREAK_ROW);
                sb.append(thirdTab).append("List<").append(getDomainname(clazz)).append("> list = ").append(getDaoValName(clazz)).append(".findListByPage(").append(getQueryObjValName(clazz)).append(");").append(BREAK_ROW);
                sb.append(thirdTab).append("PageVo<").append(getDomainname(clazz)).append("> pageVo = new PageVo<").append(getDomainname(clazz)).append(">();").append(BREAK_ROW);
                sb.append(thirdTab).append("pageVo.setList(list);").append(BREAK_ROW);
                sb.append(thirdTab).append("pageVo.setPage(").append(getQueryObjValName(clazz)).append(".getPage());").append(BREAK_ROW);
                sb.append(thirdTab).append("pageVo.setPagesize(").append(getQueryObjValName(clazz)).append(".getPageSize());").append(BREAK_ROW);
                sb.append(thirdTab).append("pageVo.setTotal(count);").append(BREAK_ROW);
                sb.append(thirdTab).append("return pageVo;").append(BREAK_ROW);
            sb.append(secondTab).append("}").append(BREAK_ROW);
            sb.append(secondTab).append("return null;").append(BREAK_ROW);
        sb.append(firstTab).append("}").append(BREAK_ROW).append(BREAK_ROW);
        return sb.toString();
    }
    private String getCount(String firstTab, Class clazz){
        StringBuilder sb = new StringBuilder();
        String secondTab = firstTab+TAB_1;
        sb.append(firstTab).append("@Override").append(BREAK_ROW);
        sb.append(firstTab).append("public int count(").append(getQueryObjName(clazz)).append(" ").append(getQueryObjValName(clazz)).append("){").append(BREAK_ROW);
            sb.append(secondTab).append("return ").append(getDaoValName(clazz)).append(".count(").append(getQueryObjValName(clazz)).append(");").append(BREAK_ROW);
        sb.append(firstTab).append("}").append(BREAK_ROW).append(BREAK_ROW);
        return sb.toString();
    }
    private String getUpdate(String firstTab, Class clazz){
        StringBuilder sb = new StringBuilder();
        String secondTab = firstTab+TAB_1;
        sb.append(firstTab).append("@Override").append(BREAK_ROW);
        sb.append(firstTab).append("public int update(").append(getDomainname(clazz)).append(" ").append(getDomainValname(clazz)).append("){").append(BREAK_ROW);
            sb.append(secondTab).append("return ").append(getDaoValName(clazz)).append(".update(").append(getDomainValname(clazz)).append(");").append(BREAK_ROW);
        sb.append(firstTab).append("}").append(BREAK_ROW).append(BREAK_ROW);
        return sb.toString();
    }

    public String genStr(Class clazz,Class queryClass){
        StringBuilder sb = new StringBuilder();
        sb.append(getPackage(clazz));
        sb.append(getImport(clazz,queryClass));
        sb.append(getHeader(clazz));
        sb.append(getDependency(TAB_1, clazz));
        sb.append(getSave(TAB_1,clazz));
        sb.append(getFindAll(TAB_1,clazz));
        sb.append(getFindById(TAB_1,clazz));
        sb.append(getFindList(TAB_1,clazz));
        sb.append(getFindListByPage(TAB_1,clazz));
        sb.append(getCount(TAB_1,clazz));
        sb.append(getUpdate(TAB_1,clazz));
        sb.append(getDeleteById(TAB_1,clazz));
        sb.append(getFooter());
        return sb.toString();
    }
    public void gen(Class clazz,Class queryClass){
        try {
            String domainname = clazz.getSimpleName();
            String daoname = domainname+"ServiceImpl.java";
            String path = GenFileUtils.getFilePath(domainname);
            String s = genStr(clazz,queryClass);
            String filename = path+ File.separator+daoname;
            GenFileUtils.writeFile(filename,s);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
