package com.odysii.selenium.page.util;

import java.net.HttpURLConnection;
import java.net.URL;

public class RequestHelper {
    public boolean getRequest(String url) {
        boolean res = false;
        HttpURLConnection conn = null;
        try {
            URL myUrl = new URL(url);
            conn = (HttpURLConnection) myUrl.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            res = conn.getResponseCode() == HttpURLConnection.HTTP_OK;
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return res;
    }
}
