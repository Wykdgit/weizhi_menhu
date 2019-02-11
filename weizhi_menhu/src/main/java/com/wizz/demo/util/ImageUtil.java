package com.wizz.demo.util;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class ImageUtil {
    public static void validateImage(MultipartFile image) throws  Exception{
        if(!image.getContentType().equals("image/jpeg")){
            throw new Exception("!");
        }
    }
    public static void saveImage(String fileName,MultipartFile image) throws Exception {
        File file=new File(fileName+".jpg");
        try{
            FileUtils.writeByteArrayToFile(file,image.getBytes());//通过将图片转化为二进制文件写进file中。
        } catch (IOException e) {
            throw  new Exception("file upload failed");
        }
    }
}
