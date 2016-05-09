package com.lawinfo.common.sourcegenerate;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Created by wangrongtao on 16/4/20.
 */
public class GenFileUtils {

    public static  String getFilePath(String domainname){
        String path = null;
        try {
            Class cls = GenFileUtils.class;
            URL base = cls.getResource("");
            int start=0,end=0;
            Package pack=cls.getPackage();
            String packName=pack.getName();
            end = packName.indexOf(".");
            StringBuilder sb = new StringBuilder();
            if (end!=-1) {
                sb.append("../");
            }
            while(end!=-1){
                sb.append("../");
                start=end+1;
                end=packName.indexOf(".",start);
            }
            sb.append("../");
            File file = new File(base.getFile(),sb.toString()+"sourcegen/"+domainname);
            if (!file.exists()) {
                file.mkdirs();
            }
            path = file.getCanonicalPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }
    public static void writeFile(String filePath, String content){
        BufferedOutputStream fos = null;
        try {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            fos = new BufferedOutputStream(new FileOutputStream(filePath));
            fos.write(content.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos!=null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
