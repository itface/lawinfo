package com.lawinfo.common.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by wangrongtao on 2016/10/23.
 */
public class XmlUtils {

    public static <T> T xmlStr2Obj(String xmlStr,Class<T>[] tClasss) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(tClasss);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return  (T)unmarshaller.unmarshal(new StringReader(xmlStr));
    }

    public static String Obj2XmlStr(Object object) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// //编码格式
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);// 是否省略xm头声明信息
        StringWriter writer = new StringWriter();
        marshaller.marshal(object, writer);
        return writer.toString();
    }
}
