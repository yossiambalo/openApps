package com.odysii.functional;
import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.admin.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddUserTest extends TestBase {
    public final static String ADMIN_USER_NAME_ROI = "roi.avital.odysii@gmail.com";
    public final static String ADMIN_USER_PASS_ROI = "Aa123456789";

    @FindBy(id = "newUserButton")
    WebElement addNewUserBtn;
    AddUser addUser;


    @Test
    public void _001_add_new_user() {
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME_ROI, ADMIN_USER_PASS_ROI, UserType.ADMIN);
        UsersPage usersPage = adminPage.getUsersPage();
//      adminPage.getUsersPage();
        AddUser addUser = usersPage.getAddNewUserPage();
        addUser.addNewUser("roi", "545@2321241sws5so2", UseRole.PARTY_DEVELOPER_MARKETING_ALL,UserDelegations.OPENAPPS_ADMIN);
        wait(WAIT);
        boolean actualValue = isElementExist(By.id("newUserButton"));
        Assert.assertTrue(actualValue);

    }

    @Test
    public void _002_cant_add_duplicate_email_address(){
        addNewUserBtn.click();
        addUser.addNewUser("QA for test 2", "aa@qa",UseRole.RETAILER_DEVELOPER_PASSPORT,UserDelegations.ODYSII);
        String actualValue = driver.findElement(By.id("editUserSuccessErrorMessage")).getText();
        String expectedValue = "An error occurred while trying to add new user. reason: email is already taken.";
        Assert.assertNotEquals(actualValue,expectedValue);

    }

}
