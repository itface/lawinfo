package com.lawinfo.common.sourcegenerate;

import com.lawinfo.common.utils.ReflectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

/**
 * Created by wangrongtao on 16/3/7.
 */
public class GenMysqlTableSqlUtil {

    public static <T> String makeSql(Class<T> domainClass) {
        return makeSql(domainClass, null);
    }

    public static <T> String makeSql(Class<T> domainClass, String tablePrefix) {
        StringBuilder sb = new StringBuilder();
        sb.append(getCreateTableHead(domainClass, tablePrefix));
        sb.append(getColumnSql(domainClass));
        sb.append(getPrimaryKey());
        sb.append(getCreateTableEnd());
        return sb.toString();
    }
    /**
     * 获取sql表命名 如orderData 转换成order_data
     *
     * @param domainName
     * @return
     */
    public static String getSQLTableName(String domainName, String tablePrefix) {
        StringBuilder sqlFieldName = new StringBuilder();
        if (!StringUtils.isEmpty(tablePrefix)) {
            sqlFieldName.append(tablePrefix);
        }
        for (char c : domainName.toCharArray()) {
            if (Character.isLowerCase(c)) {
                sqlFieldName.append(c);
            } else {
                sqlFieldName.append("_" + Character.toLowerCase(c));
            }
        }
        if (sqlFieldName.toString().startsWith("_")) {
            sqlFieldName.deleteCharAt(0);
        }
        return sqlFieldName.toString();
    }

    private static <T> String getCreateTableHead(Class<T> domainClass, String tablePrefix) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");
        sb.append(getSQLTableName(domainClass.getSimpleName(), tablePrefix));
        sb.append(" (\n");
        return sb.toString();
    }

    private static <T> String getCreateTableEnd() {
        StringBuilder sb = new StringBuilder();
        sb.append(" ) ENGINE=INNODB DEFAULT CHARSET=utf8 \n");
        return sb.toString();
    }
    /**
     * 获取sql字段命名 如appName 转换成APP_NAME
     *
     * @param fieldName
     * @return
     */
    public static String getSQLFieldName(String fieldName) {
        return fieldName.toLowerCase();
        /*StringBuilder sqlFieldName = new StringBuilder();
        for (char c : fieldName.toCharArray()) {
            if (Character.isLowerCase(c)) {
                sqlFieldName.append(Character.toUpperCase(c));
            } else {
                sqlFieldName.append("_" + c);
            }
        }
        return sqlFieldName.toString();*/
    }
    private static <T> String getColumnSql(Class<T> domainClass) {
        StringBuilder sb = new StringBuilder();
        Field[] fields = domainClass.getDeclaredFields();
        for (Field field : fields) {
            sb.append("\t\t ");
            sb.append(getSQLFieldName(field.getName()));
            sb.append(" ");
            sb.append(getDefaultDbTypeByJavaType(field.getType()));
            sb.append("\n");
        }
////        干掉最后的逗号(倒数第一个字符是换行)
//        sb.deleteCharAt(sb.length() - 2);
        return sb.toString();
    }

    private static String getPrimaryKey() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t ");
        sb.append("PRIMARY KEY (ID)");
        sb.append("\n");
        return sb.toString();
    }

    private static String getDefaultDbTypeByJavaType(Class<?> clz) {
        String dbType = "";
        if (ReflectUtils.isInteger(clz)) {
            dbType = " INT(10) DEFAULT NULL COMMENT '' ,";
        } else if (ReflectUtils.isLong(clz)) {
            dbType = " BIGINT(20) DEFAULT NULL COMMENT '' ,";
        } else if (ReflectUtils.isDouble(clz)) {
            dbType = " DOUBLE DEFAULT NULL COMMENT '' ,";
        } else if (ReflectUtils.isString(clz)) {
            dbType = " VARCHAR(200) DEFAULT NULL COMMENT '' ,";
        } else if (ReflectUtils.isDate(clz)) {
            dbType = " DATETIME DEFAULT NULL COMMENT '' ,";
        } else {
            throw new RuntimeException("建表对象包含未知类型 [" + clz + "]");
        }
        return dbType;
    }
}
