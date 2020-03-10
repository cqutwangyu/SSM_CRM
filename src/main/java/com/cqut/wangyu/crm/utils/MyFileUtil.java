package com.cqut.wangyu.crm.utils;

/**
 * @ClassName MyFileUtil
 * @Description 文件工具类
 * @Author ChongqingWangYu
 * @DateTime 2019/6/20 11:32
 * @GitHub https://github.com/ChongqingWangYu
 */
public class MyFileUtil {
    public final static String LOCAL_HOST_SERVER_ADDRESS = "http://127.0.0.1:8088/";
//    public final static String LOCAL_HOST_SERVER_ADDRESS = "http://39.108.252.228/SSM_CRM/";

    public final static String IMG_REQUEST = "file/getImage?path=";

    public final static String IMG_PATH = "UploadingResources/Images/";
    public final static String EXCEL_PATH = "UploadingResources/Excel/";

    public static String getContentType(String path) {
        String type = path.substring(path.indexOf(".") + 1);
        String contentType = null;
        switch (type) {
            case "png":
                contentType = "image/png";
                break;
            case "jpeg":
                contentType = "image/jpeg";
                break;
            case "jpg":
                contentType = "image/jpg";
                break;
            case "gif":
                contentType = "image/gif";
                break;
        }
        return contentType;
    }
}
