package com.odysii.selenium.page.util;

import java.io.File;

public class FileHandler {
    public String getFile(String fileName){

        File file = new File(getClass().getClassLoader().getResource(fileName).getFile());

        return file.toString();
    }
}
