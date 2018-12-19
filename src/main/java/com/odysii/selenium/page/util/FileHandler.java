package com.odysii.selenium.page.util;

import java.io.File;

public class FileHandler {
    public String getFile(String fileName){

        File file = new File(getClass().getClassLoader().getResource(fileName).getFile());

        return file.toString();
    }
    public boolean isFileExist(String filePath){
        File f = new File(filePath);
        return (f.exists() && !f.isDirectory());
    }
    public boolean deleteFile(String filePath){
        File f = new File(filePath);
        return f.delete();
    }
}
