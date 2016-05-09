package com.lawinfo.common.sourcegenerate;



import java.io.File;
import java.lang.reflect.Field;

/**
 * 根据定义的bean（BasePersistenceBean的子类） 自动生成ibatis的xml工具(可以main方法打印)
 * Created by wangdi on 15-7-17.
 */
public class GenMyBatisXmlUtil {
    private final String BREAK_ROW = "\n";
    private final String TAB_1 = "\t";

    public String makeXml(Class domainClass, Class queryClass) {
        String tablename = domainClass.getSimpleName().toLowerCase();
        StringBuilder sb = new StringBuilder();
        sb.append(getHeader(domainClass));
        sb.append(getResultMap(TAB_1,domainClass));
        sb.append(getAllQueryFields(TAB_1,domainClass));
        sb.append(getPage(TAB_1,domainClass));
        sb.append(getDynamicWhereCloumn(TAB_1,queryClass));
        sb.append(getFindAll(TAB_1,queryClass,tablename));
        sb.append(getFindById(TAB_1,queryClass,tablename));
        sb.append(getFindList(TAB_1,queryClass,tablename));
        sb.append(getFindListByPage(TAB_1,queryClass,tablename));
        sb.append(getCount(TAB_1,queryClass,tablename));
        sb.append(getSave(TAB_1,domainClass,tablename));
        sb.append(getDeleteById(TAB_1,tablename));
        sb.append(getUpdate(TAB_1,domainClass,tablename));
        sb.append(getFoot());
        return sb.toString();
    }


    private String getHeader(Class domainClass) {
        StringBuilder sb = new StringBuilder();
        String daoName = domainClass.getName().replace("domain", "dao")+"Dao";
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>").append(BREAK_ROW);
        sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">").append(BREAK_ROW);
        sb.append("<mapper namespace=\"").append(daoName).append("\">").append(BREAK_ROW);
        return sb.toString();
    }
    private String getFoot() {
        StringBuilder sb = new StringBuilder();
        sb.append("</mapper>");
        return sb.toString();
    }

    private String getResultMap(String firstTab,Class domainClass) {
        StringBuilder sb = new StringBuilder();
        String secondTab = TAB_1+firstTab;
        sb.append(firstTab).append("<resultMap id=\"ResultMap\" type=\"").append(domainClass.getName()).append("\">").append(BREAK_ROW);
        Field[] fields = domainClass.getDeclaredFields();
        for (Field field : fields) {
            sb.append(secondTab).append("<result column=\"").append(field.getName()).append("\" property=\"").append(field.getName()).append("\"/>").append(BREAK_ROW);
        }
        sb.append(firstTab).append("</resultMap>").append(BREAK_ROW);
        return sb.toString();
    }
    private String getSelectFields(String firstTab,Class domainClass){
        StringBuilder sb = new StringBuilder();
        Field[] fields = domainClass.getDeclaredFields();
        for (Field field : fields) {
            sb.append(field.getName()).append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }
    private String getSelectFieldValues(String firstTab,Class domainClass){
        StringBuilder sb = new StringBuilder();
        Field[] fields = domainClass.getDeclaredFields();
        for (Field field : fields) {
            sb.append("#{").append(field.getName()).append("},");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }
    private String getAllQueryFields(String firstTab,Class domainClass) {
        StringBuilder sb = new StringBuilder();
        String secondTab = TAB_1+firstTab;
        sb.append(firstTab).append("<sql id=\"allColumn\">").append(BREAK_ROW);
            sb.append(secondTab).append(getSelectFields(secondTab,domainClass));
        sb.append(firstTab).append("</sql>").append(BREAK_ROW);
        return sb.toString();
    }
    private String getPage(String firstTab,Class domainClass) {
        StringBuilder sb = new StringBuilder();
        String secondTab = TAB_1+firstTab;
        sb.append(firstTab).append("<sql id=\"page\">").append(BREAK_ROW);
            sb.append(secondTab).append("limit #{startRow},#{pageSize}");
        sb.append(firstTab).append("</sql>").append(BREAK_ROW);
        return sb.toString();
    }
    private boolean isInteger(Class<?> clz) {
        return clz.isAssignableFrom(Integer.class) || clz == int.class;
    }
    private boolean isLong(Class<?> clz) {
        return clz.isAssignableFrom(Long.class)|| clz == long.class;
    }
    private String getDynamicWhereCloumn(String firstTab,Class queryClass) {
        StringBuilder sb = new StringBuilder();
        String secondTab = TAB_1+firstTab;
        String thirdTab = TAB_1+secondTab;
        String fourthTab = TAB_1+thirdTab;
        String fifthTab = TAB_1+fourthTab;
        sb.append(firstTab).append("<sql id=\"condition\">").append(BREAK_ROW);
            sb.append(thirdTab).append("<trim prefix=\"WHERE\" prefixOverrides=\"AND | OR\">").append(BREAK_ROW);
            Field[] fieldList = queryClass.getDeclaredFields();
            for (Field field : fieldList) {
                if (isInteger(field.getType()) || isLong(field.getType())) {
                    sb.append(fourthTab).append("<if test=\"").append(field.getName()).append(" != null and ").append(field.getName()).append(">0\">").append(BREAK_ROW);
                }else{
                    sb.append(fourthTab).append("<if test=\"").append(field.getName()).append(" != null and ").append(field.getName()).append(" != ''\">").append(BREAK_ROW);
                }
                    sb.append(fifthTab).append(" and ").append(field.getName()).append("=#{").append(field.getName()).append("}\n");
                sb.append(fourthTab).append("</if>").append(BREAK_ROW);
            }
            sb.append(thirdTab).append("</trim>").append(BREAK_ROW);
        sb.append(firstTab).append("</sql>").append(BREAK_ROW);
        return sb.toString();
    }


    private String getFindAll(String firstTab,Class queryClass,String tablename) {
        StringBuilder sb = new StringBuilder();
        String secondTab = TAB_1+firstTab;
        sb.append(firstTab).append("<select id=\"findAll\" parameterType=\"").append(queryClass.getName()).append("\"  resultMap=\"ResultMap\">").append(BREAK_ROW);
            sb.append(secondTab).append("select <include refid=\"allColumn\"/> from ").append(tablename).append(BREAK_ROW);
        sb.append(firstTab).append("</select>").append(BREAK_ROW);
        return sb.toString();
    }
    private String getFindById(String firstTab,Class queryClass,String tablename) {
        StringBuilder sb = new StringBuilder();
        String secondTab = TAB_1+firstTab;
        sb.append(firstTab).append("<select id=\"findById\" parameterType=\"long\"  resultMap=\"ResultMap\">").append(BREAK_ROW);
            sb.append(secondTab).append("select <include refid=\"allColumn\"/> from ").append(tablename).append(" where id = #{id}").append(BREAK_ROW);
        sb.append(firstTab).append("</select>").append(BREAK_ROW);
        return sb.toString();
    }

    private String getFindList(String firstTab,Class queryClass,String tablename) {
        StringBuilder sb = new StringBuilder();
        String secondTab = TAB_1+firstTab;
        sb.append(firstTab).append("<select id=\"findList\" parameterType=\"").append(queryClass.getName()).append("\"  resultMap=\"ResultMap\">").append(BREAK_ROW);
        sb.append(secondTab).append("select <include refid=\"allColumn\"/> from ").append(tablename).append(" <include refid=\"condition\"/>").append(BREAK_ROW);
        sb.append(firstTab).append("</select>").append(BREAK_ROW);
        return sb.toString();
    }
    private String getFindListByPage(String firstTab,Class queryClass,String tablename) {
        StringBuilder sb = new StringBuilder();
        String secondTab = TAB_1+firstTab;
        sb.append(firstTab).append("<select id=\"findListByPage\" parameterType=\"").append(queryClass.getName()).append("\"  resultMap=\"ResultMap\">").append(BREAK_ROW);
        sb.append(secondTab).append("select <include refid=\"allColumn\"/> from ").append(tablename).append(" <include refid=\"condition\"/>").append(" <include refid=\"page\"/>").append(BREAK_ROW);
        sb.append(firstTab).append("</select>").append(BREAK_ROW);
        return sb.toString();
    }
    private String getCount(String firstTab,Class queryClass,String tablename) {
        StringBuilder sb = new StringBuilder();
        String secondTab = TAB_1+firstTab;
        sb.append(firstTab).append("<select id=\"count\" parameterType=\"").append(queryClass.getName()).append("\"  resultType=\"int\">").append(BREAK_ROW);
        sb.append(secondTab).append("select count(1) from ").append(tablename).append(" <include refid=\"condition\"/>").append(BREAK_ROW);
        sb.append(firstTab).append("</select>").append(BREAK_ROW);
        return sb.toString();
    }
    private String getSave(String firstTab,Class domainClass,String tablename) {
        StringBuilder sb = new StringBuilder();
        String secondTab = TAB_1+firstTab;
        sb.append(firstTab).append("<insert id=\"save\" parameterType=\"").append(domainClass.getName()).append("\">").append(BREAK_ROW);
            sb.append(secondTab).append("insert into ").append(tablename).append("(").append(getSelectFields(secondTab,domainClass)).append(")")
            .append(" values(").append(getSelectFieldValues(secondTab,domainClass)).append(")").append(BREAK_ROW);
        sb.append(firstTab).append("</insert>").append(BREAK_ROW);
        return sb.toString();
    }
    private String getDeleteById(String firstTab,String tablename) {
        StringBuilder sb = new StringBuilder();
        String secondTab = TAB_1+firstTab;
        sb.append(firstTab).append("<delete id=\"deleteById\" parameterType=\"long\">").append(BREAK_ROW);
            sb.append(secondTab).append("delete from  ").append(tablename).append(" where id = #{id}").append(BREAK_ROW);
        sb.append(firstTab).append("</delete>").append(BREAK_ROW);
        return sb.toString();
    }
    private String getUpdate(String firstTab,Class domainClass,String tablename) {
        StringBuilder sb = new StringBuilder();
        String secondTab = TAB_1+firstTab;
        sb.append(firstTab).append("<update id=\"update\" parameterType=\"").append(domainClass).append("\">").append(BREAK_ROW);
            sb.append(secondTab).append("update  ").append(tablename).append(" set modified=now() where id = #{id}").append(BREAK_ROW);
        sb.append(firstTab).append("</update>").append(BREAK_ROW);
        return sb.toString();
    }
    public void gen(Class domainClass, Class queryClass){
        try {
            String domainname = domainClass.getSimpleName();
            String xmlname = domainname+"Dao.xml";
            String path = GenFileUtils.getFilePath(domainname);
            String s = makeXml(domainClass,queryClass);
            String filename = path+ File.separator+xmlname;
            GenFileUtils.writeFile(filename,s);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
