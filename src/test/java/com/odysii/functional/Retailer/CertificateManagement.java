package com.odysii.functional.Retailer;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.admin.AdminPage;
import org.testng.annotations.BeforeClass;

public class CertificateManagement extends TestBase {
    @BeforeClass
    public void prepare(){
        user = new User(driver);
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS,UserType.ADMIN);
    }

}
