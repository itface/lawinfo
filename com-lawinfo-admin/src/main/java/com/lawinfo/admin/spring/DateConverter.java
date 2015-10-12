package com.lawinfo.admin.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangrongtao on 15-2-11.
 */
public class DateConverter implements Converter<String, Date> {
    private static final Log log = LogFactory.getLog(DateConverter.class);
    @Override
    public Date convert(String s) {
        if (!StringUtils.isEmpty(s)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            try {
                return dateFormat.parse(s);
            } catch (ParseException e) {
                log.error("日期格式转换错误:"+s,e);
            }
        }
        return null;
    }
}
