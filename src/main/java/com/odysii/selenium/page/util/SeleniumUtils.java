package com.odysii.selenium.page.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SeleniumUtils {
    public static void dragAndDrop(WebDriver webDriver,WebElement source, WebElement target){
        try {
            String script = readFile();
            script += "simulateHTML5DragAndDrop(arguments[0], arguments[1])";
            ((JavascriptExecutor) webDriver).executeScript(script, source, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String readFile() throws IOException {
        String file = "src/main/resources/js/draganddrop.js";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }
}
