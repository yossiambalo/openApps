package com.odysii;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class AutomaticCreationTest {
    @Test
    public void _001_valid_automatic_creation(){

        String res = SqlManager.executeQuery("SELECT * FROM openapp_qa.RETAILERS;","openapp-db-qa","Letmein!","openapp-db.qa.aws.gilbarco.com");
        Assert.assertTrue(res.contains(getGMTTime()));
    }
    private String getGMTTime(){
        final Date gmtTime = new Date();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d hh:");
        // Give it to me in GMT time.
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        sdf.getCalendar().get(Calendar.MONTH);
        return sdf.format(gmtTime);
    }
}
