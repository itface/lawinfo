package com.lawinfo.common.sourcegenerate;



import java.io.File;

/**
 * Created by wangrongtao on 16/4/20.
 */
public class GenControllerUtil {
    private final String BREAK_ROW = "\n";
    private final String TAB_1 = "\t";

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
    private String getServiceObjName(Class clazz){
        String servicename = getServiceName(clazz);
        String servicevalname = servicename.substring(0,1).toLowerCase()+servicename.substring(1);
        return servicevalname;
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
        String daoPackagename = packagename.replace("domain", "controller");
        sb.append("package ").append(daoPackagename).append(";").append(BREAK_ROW).append(BREAK_ROW);
        return sb.toString();
    }
    private String getImport(Class clazz,Class queryClass){
        StringBuilder sb = new StringBuilder();
        String servicepackagename = clazz.getName().replace("domain","service")+"Service";
        sb.append("import com.infosource.domain.common.PageVo;").append(BREAK_ROW);
        sb.append("import org.springframework.stereotype.Controller;").append(BREAK_ROW);
        sb.append("import org.springframework.ui.ModelMap;").append(BREAK_ROW);
        sb.append("import org.springframework.validation.BindingResult;").append(BREAK_ROW);
        sb.append("import org.springframework.validation.ObjectError;").append(BREAK_ROW);
        sb.append("import org.springframework.web.bind.annotation.RequestMapping;").append(BREAK_ROW);
        sb.append("import javax.annotation.Resource;").append(BREAK_ROW);
        sb.append("import javax.validation.Valid;").append(BREAK_ROW);
        sb.append("import java.util.List;").append(BREAK_ROW);
        sb.append("import ").append(clazz.getName()).append(";").append(BREAK_ROW);
        sb.append("import ").append(queryClass.getName()).append(";").append(BREAK_ROW);
        sb.append("import ").append(servicepackagename).append(";").append(BREAK_ROW);
        sb.append(BREAK_ROW);
        sb.append(BREAK_ROW);
        sb.append(BREAK_ROW);
        return sb.toString();
    }
    private String getHeader(Class clazz,String baseurl){
        StringBuilder sb = new StringBuilder();
        String classname = clazz.getSimpleName()+"Controller";
        sb.append("@Controller").append(BREAK_ROW);
        sb.append("@RequestMapping(\"").append(baseurl).append("\")").append(BREAK_ROW);
        sb.append("public class ").append(classname).append(" {").append(BREAK_ROW);
        return sb.toString();
    }
    private String getDependency(String firstTab, Class clazz){
        StringBuilder sb = new StringBuilder();
        sb.append(firstTab).append("@Resource").append(BREAK_ROW);
        sb.append(firstTab).append("private ").append(getServiceName(clazz)).append(" ").append(getServiceObjName(clazz)).append(";").append(BREAK_ROW).append(BREAK_ROW);
        return sb.toString();
    }
    private String getView(String firstTab, Class clazz,String vmpath){
        StringBuilder sb = new StringBuilder();
        String secondTab = firstTab+TAB_1;
        String thirdTab = secondTab+TAB_1;
        sb.append(firstTab).append("@RequestMapping()").append(BREAK_ROW);
        sb.append(firstTab).append("public String view(").append(getQueryObjName(clazz)).append(" ").append(getQueryObjValName(clazz)).append(",ModelMap model){").append(BREAK_ROW);
            sb.append(secondTab).append("if(").append(getQueryObjValName(clazz)).append(".getPage()<1){").append(BREAK_ROW);
                sb.append(thirdTab).append(getQueryObjValName(clazz)).append(".setPage(1);").append(BREAK_ROW);
            sb.append(secondTab).append("}").append(BREAK_ROW);
            sb.append(secondTab).append(getQueryObjValName(clazz)).append(".setPageSize(20);").append(BREAK_ROW);
            sb.append(secondTab).append("PageVo<").append(clazz.getSimpleName()).append("> pageVo = ").append(getServiceObjName(clazz)).append(".findListByPage(").append(getQueryObjValName(clazz)).append(");").append(BREAK_ROW);
            sb.append(secondTab).append("model.put(\"pageVo\", pageVo);").append(BREAK_ROW);
            sb.append(secondTab).append("model.put(\"queryobj\",").append((getQueryObjValName(clazz))).append(");").append(BREAK_ROW);
        sb.append(secondTab).append("return \"").append(vmpath).append("/").append(clazz.getSimpleName().toLowerCase()).append("\";").append(BREAK_ROW);
        sb.append(firstTab).append("}").append(BREAK_ROW).append(BREAK_ROW);
        return sb.toString();
    }
    private String getFindbyid(String firstTab, Class clazz){
        StringBuilder sb = new StringBuilder();
        String secondTab = firstTab+TAB_1;
        sb.append(firstTab).append("@Resource").append(BREAK_ROW);
        sb.append(firstTab).append("@RequestMapping(\"/findbyid\")").append(BREAK_ROW);
        sb.append(firstTab).append("public ").append(clazz.getSimpleName()).append(" findbyid(long id){").append(BREAK_ROW);
            sb.append(secondTab).append("return ").append(getServiceObjName(clazz)).append(".findById(id);").append(BREAK_ROW);
        sb.append(firstTab).append("}").append(BREAK_ROW).append(BREAK_ROW);
        return sb.toString();
    }
    private String getRemove(String firstTab, Class clazz){
        StringBuilder sb = new StringBuilder();
        String secondTab = firstTab+TAB_1;
        String thirdTab = secondTab+TAB_1;
        sb.append(firstTab).append("@Resource").append(BREAK_ROW);
        sb.append(firstTab).append("@RequestMapping(\"/remove\")").append(BREAK_ROW);
        sb.append(firstTab).append("public int remove(long id){").append(BREAK_ROW);
            sb.append(secondTab).append("return ").append(getServiceObjName(clazz)).append(".deleteById(id);").append(BREAK_ROW);
        sb.append(firstTab).append("}").append(BREAK_ROW).append(BREAK_ROW);
        return sb.toString();
    }
    private String getEdit(String firstTab, Class clazz){
        StringBuilder sb = new StringBuilder();
        String secondTab = firstTab+TAB_1;
        String thirdTab = secondTab+TAB_1;
        String fouthTab = thirdTab+TAB_1;
        sb.append(firstTab).append("@Resource").append(BREAK_ROW);
        sb.append(firstTab).append("@RequestMapping(\"/edit\")").append(BREAK_ROW);
        sb.append(firstTab).append("public String save(@Valid ").append(clazz.getSimpleName()).append(" ").append(getDomainValname(clazz)).append(",BindingResult result){").append(BREAK_ROW);
            sb.append(secondTab).append("StringBuilder stringBuilder = new StringBuilder();").append(BREAK_ROW);
            sb.append(secondTab).append("if (result.hasErrors()) {").append(BREAK_ROW);
                sb.append(thirdTab).append("List<ObjectError> list = result.getAllErrors();").append(BREAK_ROW);
                sb.append(thirdTab).append("for (ObjectError objectError : list) {").append(BREAK_ROW);
                    sb.append(fouthTab).append("stringBuilder.append(objectError.getDefaultMessage()).append(\"<br>\");").append(BREAK_ROW);
                sb.append(thirdTab).append("}").append(BREAK_ROW);
            sb.append(secondTab).append("}else{").append(BREAK_ROW);
                sb.append(thirdTab).append("int effectrow = 0;").append(BREAK_ROW);
                sb.append(thirdTab).append("if(").append(getDomainValname(clazz)).append(".getId() < 1) {").append(BREAK_ROW);
                    sb.append(fouthTab).append("effectrow = ").append(getServiceObjName(clazz)).append(".save(").append(getDomainValname(clazz)).append(");").append(BREAK_ROW);
                sb.append(thirdTab).append("}else{").append(BREAK_ROW);
                    sb.append(fouthTab).append("effectrow = ").append(getServiceObjName(clazz)).append(".update(").append(getDomainValname(clazz)).append(");").append(BREAK_ROW);
                sb.append(thirdTab).append("}").append(BREAK_ROW);
                sb.append(thirdTab).append("if (effectrow<1) {").append(BREAK_ROW);
                    sb.append(fouthTab).append("stringBuilder.append(\"操作失败\") ;").append(BREAK_ROW);
                sb.append(thirdTab).append("}").append(BREAK_ROW);
            sb.append(secondTab).append("}").append(BREAK_ROW);
            sb.append(secondTab).append("return stringBuilder.toString();").append(BREAK_ROW);
        sb.append(firstTab).append("}").append(BREAK_ROW).append(BREAK_ROW);
        return sb.toString();
    }
    private String getFooter(){
        StringBuilder sb = new StringBuilder();
        sb.append("}");
        return sb.toString();
    }
    public String genStr(Class clazz,Class queryClass,String baseurl,String vmpath){
        StringBuilder sb = new StringBuilder();
        sb.append(getPackage(clazz));
        sb.append(getImport(clazz,queryClass));
        sb.append(getHeader(clazz,baseurl));
        sb.append(getDependency(TAB_1,clazz));
        sb.append(getView(TAB_1,clazz,vmpath));
        sb.append(getFindbyid(TAB_1,clazz));
        sb.append(getRemove(TAB_1,clazz));
        sb.append(getEdit(TAB_1,clazz));
        sb.append(getFooter());
        return sb.toString();
    }
    public void gen(Class clazz,Class queryClass,String baseurl,String vmpath){
        try {
            String domainname = clazz.getSimpleName();
            String daoname = clazz.getSimpleName()+"Controller.java";
            String path = GenFileUtils.getFilePath(domainname);
            String s = genStr(clazz,queryClass,baseurl,vmpath);
            String filename = path+ File.separator+daoname;
            GenFileUtils.writeFile(filename,s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
