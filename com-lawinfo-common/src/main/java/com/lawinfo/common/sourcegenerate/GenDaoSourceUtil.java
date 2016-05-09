package com.lawinfo.common.sourcegenerate;


import java.io.File;

/**
 * Created by wangrongtao on 16/4/21.
 */
public class GenDaoSourceUtil {
    private final String BREAK_ROW = "\n";
    private final String TAB_1 = "\t";

    private String getPackage(Class clazz){
        StringBuilder sb = new StringBuilder();
        String packagename = clazz.getPackage().getName();
        String daoPackagename = packagename.replace("domain", "dao");
        sb.append("package ").append(daoPackagename).append(";").append(BREAK_ROW).append(BREAK_ROW);
        return sb.toString();
    }
    private String getImport(Class clazz,Class queryClass){
        StringBuilder sb = new StringBuilder();
        sb.append("import java.util.List;").append(BREAK_ROW);
        sb.append("import ").append(clazz.getName()).append(";").append(BREAK_ROW);
        sb.append("import ").append(queryClass.getName()).append(";").append(BREAK_ROW);
        sb.append(BREAK_ROW);
        sb.append(BREAK_ROW);
        sb.append(BREAK_ROW);
        return sb.toString();
    }
    private String getHeader(Class clazz){
        StringBuilder sb = new StringBuilder();
        String daoname = clazz.getSimpleName()+"Dao";
        sb.append("public interface ").append(daoname).append(" {").append(BREAK_ROW);
        return sb.toString();
    }
    private String getContent(Class clazz){
        StringBuilder sb = new StringBuilder();
        String domainname = clazz.getSimpleName();
        String domainObjValName = domainname.substring(0,1).toLowerCase()+domainname.substring(1);
        String queryObjName = domainname + "Query";
        String queryObjValName = domainObjValName + "Query";
        sb.append(TAB_1).append("public ").append(domainname).append(" findById(long id);").append(BREAK_ROW);
        sb.append(TAB_1).append("public int deleteById(long id);").append(BREAK_ROW);
        sb.append(TAB_1).append("public List< ").append(domainname).append("> findAll();").append(BREAK_ROW);
        sb.append(TAB_1).append("public List< ").append(domainname).append("> findList(").append(queryObjName).append(" ").append(queryObjValName).append(");").append(BREAK_ROW);
        sb.append(TAB_1).append("public List< ").append(domainname).append("> findListByPage(").append(queryObjName).append(" ").append(queryObjValName).append(");").append(BREAK_ROW);
        sb.append(TAB_1).append("public int count(").append(queryObjName).append(" ").append(queryObjValName).append(");").append(BREAK_ROW);
        sb.append(TAB_1).append("public int save(").append(domainname).append(" ").append(domainObjValName).append(");").append(BREAK_ROW);
        sb.append(TAB_1).append("public int update(").append(domainname).append(" ").append(domainObjValName).append(");").append(BREAK_ROW);
        return sb.toString();
    }
    private String getFooter(){
        StringBuilder sb = new StringBuilder();
        sb.append("}");
        return sb.toString();
    }
    public String genStr(Class clazz,Class queryClass){
        StringBuilder sb = new StringBuilder();
        sb.append(getPackage(clazz));
        sb.append(getImport(clazz,queryClass));
        sb.append(getHeader(clazz));
        sb.append(getContent(clazz));
        sb.append(getFooter());
        return sb.toString();
    }
    public void gen(Class clazz,Class queryClass){
        try {
            String domainname = clazz.getSimpleName();
            String daoname = clazz.getSimpleName()+"Dao.java";
            String path = GenFileUtils.getFilePath(domainname);
            String s = genStr(clazz,queryClass);
            String filename = path+ File.separator+daoname;
            GenFileUtils.writeFile(filename,s);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
