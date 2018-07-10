package com.odysii.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    public Properties loadPropFile(String file){
        Properties prop = new Properties();
        InputStream input = null;
        try {
            String propertyPath = "src/main/resources/value/";
            input = new FileInputStream(propertyPath +file);
            // load a properties file
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }
}
