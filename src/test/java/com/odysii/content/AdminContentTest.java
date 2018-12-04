package com.odysii.content;


import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.admin.AdminPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class AdminContentTest extends TestBase {
    AdminPage adminPage;
    User user;
    final String CATEGORYTEST = "Admin Content Tests";
    @BeforeClass
    public void login(){
        user = new User(driver);
        adminPage =(AdminPage) user.login("admin", "admin",UserType.ADMIN);

    }
    @Test
    public void _001_valid_header_support_tickets(){
        logger = extent.startTest("_001_valid_header_support_tickets").assignCategory(CATEGORYTEST);
        String expected = "Support Tickets";
        adminPage.getSupportTickets();
        String actualTxt = driver.findElement(By.className("h2")).getText();
        Assert.assertEquals(expected,actualTxt);
        String expectedSdbr = "SUPPORT TICKETS";
        String actualSdbr = driver.findElement(By.id("navItem0")).getText().toUpperCase().trim();
        Assert.assertEquals(actualSdbr,expectedSdbr);


    }
    @Test
    public void _002_valid_TICKET_id(){
        logger = extent.startTest("_002_valid_TICKET_id").assignCategory(CATEGORYTEST);
        String expected = "ID";
        String actauTxt = driver.findElement(By.className("col-1")).getText();
        Assert.assertEquals(expected,actauTxt);

    }
    @Test
    public void _003_valid_ticket_application_name(){
        logger = extent.startTest("_003_valid_ticket_application_name").assignCategory(CATEGORYTEST);
        String expected = "Application name";
        String actualTxt = driver.findElement(By.xpath("//div[contains(text(), 'Application name')]")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _004_valid_ticket_version_column_name(){
        logger = extent.startTest("_004_valid_ticket_version_column_name").assignCategory(CATEGORYTEST);
        String expected = "Version #";
        String actualTxt = driver.findElement(By.xpath("//div[contains(text(), 'Version #')]")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _005_valid_ticket_subject_column_name(){
        logger = extent.startTest("_005_valid_ticket_subject_column_name").assignCategory(CATEGORYTEST);
        String expected = "Subject";
        String actualTxt = driver.findElement(By.xpath("//div[contains(text(), 'Subject')]")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _006_valid_ticket_created_column_name(){
        logger = extent.startTest("_006_valid_ticket_created_column_name").assignCategory(CATEGORYTEST);
        String expected = "Created";
        String actualTxt = driver.findElement(By.xpath("//div[contains(text(), 'Created')]")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _007_valid_last_activity_column_name(){
        logger = extent.startTest("_007_valid_last_activity_column_name").assignCategory(CATEGORYTEST);
        String expected = "Last activity";
        String actualTxt = driver.findElement(By.xpath("//div[contains(text(), 'Last activity')]")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _008_valid_status_column_name(){
        logger = extent.startTest("_008_valid_status_column_name").assignCategory(CATEGORYTEST);
        String expected = "Status";
        String actaulTxt = driver.findElement(By.xpath("//div[contains(text(), 'Status')]")).getText();
        Assert.assertEquals(expected,actaulTxt);

    }
    @Test
    public void _009_valid_support_ticket_left_menu(){
        logger = extent.startTest("_009_valid_support_ticket_left_menu").assignCategory(CATEGORYTEST);
        String expected = "SUPPORT TICKETS";
        String actualTxt = driver.findElement(By.xpath("//span[contains(text(), 'Support Tickets')]")).getText();
        Assert.assertEquals(expected.toLowerCase(),actualTxt.toLowerCase());

    }
    @Test
    public void _010_valid_button_text_back_to_support_tickets(){
        logger = extent.startTest("_010_valid_button_text_back_to_support_tickets").assignCategory(CATEGORYTEST);
        adminPage.getSupportTickets();
        adminPage.getTicket(2);
        String expected = "Back to Support Tickets";
        String actualTxt = driver.findElement(By.xpath("//span[contains(text(), 'Back to Support Tickets')]")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _011_valid_button_text_preview(){
        logger = extent.startTest("_011_valid_button_text_preview").assignCategory(CATEGORYTEST);
        String expected = "Preview";
        String actualTxt = driver.findElement(By.id("adminTicketPreview")).getText();
        Assert.assertEquals(expected.trim(),actualTxt.trim());

    }
    @Test
    public void _012_valid_button_text_preview(){
        logger = extent.startTest("_012_valid_button_text_preview").assignCategory(CATEGORYTEST);
        String expected = "Download";
        String actualTxt = driver.findElement(By.id("adminTicketDownload")).getText();
        Assert.assertEquals(expected.trim(),actualTxt.trim());

    }
    @Test
    public void _013_valid_text_status(){
        logger = extent.startTest("_013_valid_text_status").assignCategory(CATEGORYTEST);
        String expected = "Status";
        String actualTxt = driver.findElement(By.xpath("//h6[contains(text(), 'Status')]")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _014_valid_text_comments(){
        logger = extent.startTest("_014_valid_text_comments").assignCategory(CATEGORYTEST);
        String expected = "Comments";
        String actualTxt = driver.findElement(By.xpath("//h6[contains(text(), 'Comments')]")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _015_valid_text_status_approved(){
        logger = extent.startTest("_015_valid_text_status_approved").assignCategory(CATEGORYTEST);
        String expected = "Approved";
        String actualTxt = driver.findElement(By.id("adminTicketApproved")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _016_valid_text_status_reject_no_fee(){
        logger = extent.startTest("_016_valid_text_status_reject_no_fee").assignCategory(CATEGORYTEST);
        String expected = "Reject - no fee";
        String actualTxt = driver.findElement(By.id("adminTicketReject")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _017_valid_text_status_reject_with_fee(){
        logger = extent.startTest("_017_valid_text_status_reject_with_fee").assignCategory(CATEGORYTEST);
        String expected = "Reject with fee";
        String actualTxt = driver.findElement(By.id("adminTicketRejectFee")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _018_valid_text_status_open(){
        logger = extent.startTest("_018_valid_text_status_open").assignCategory(CATEGORYTEST);
        String expected = "Open";
        String actualTxt = driver.findElement(By.id("adminTicketOpen")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _019_valid_button_text_update(){
        logger = extent.startTest("_019_valid_button_text_update").assignCategory(CATEGORYTEST);
        String expected = "UPDATE";
        String actualTxt = driver.findElement(By.id("finishButtonAdminTicket")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _020_valid_header_inside_ticket_name(){
        logger = extent.startTest("_020_valid_header_inside_ticket_name").assignCategory(CATEGORYTEST);
        String expected = "Name";
        String actualTxt = driver.findElement(By.xpath("//label[contains(text(), 'Name')]")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _021_valid_header_inside_ticket_subtitle(){
        logger = extent.startTest("_021_valid_header_inside_ticket_subtitle").assignCategory(CATEGORYTEST);
        String expected = "Subtitle";
        String actualTxt = driver.findElement(By.xpath("//label[contains(text(), 'Subtitle')]")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _022_valid_header_inside_ticket_category(){
        logger = extent.startTest("_022_valid_header_inside_ticket_category").assignCategory(CATEGORYTEST);
        String expected = "Category";
        String actualTxt = driver.findElement(By.xpath("//label[contains(text(), 'Category')]")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _023_valid_header_inside_ticket_language(){
        logger = extent.startTest("_023_valid_header_inside_ticket_language").assignCategory(CATEGORYTEST);
        String expected = "Language";
        String actualTxt = driver.findElement(By.xpath("//label[contains(text(), 'Language')]")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _024_valid_header_inside_ticket_availability(){
        logger = extent.startTest("_024_valid_header_inside_ticket_language").assignCategory(CATEGORYTEST);
        String expected = "Availability";
        String actualTxt = driver.findElement(By.xpath("//label[contains(text(), 'Availability')]")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _025_valid_header_inside_ticket_retailers(){
        logger = extent.startTest("_025_valid_header_inside_ticket_retailers").assignCategory(CATEGORYTEST);
        String expected = "Retailers";
        String actualTxt = driver.findElement(By.xpath("//label[contains(text(), 'Retailers')]")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _026_valid_header_inside_ticket_promotional_text(){
        logger = extent.startTest("_026_valid_header_inside_ticket_promotional_text").assignCategory(CATEGORYTEST);
        String expected = "Promotional Text";
        String actualTxt = driver.findElement(By.xpath("//label[contains(text(), 'Promotional Text')]")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _027_valid_header_inside_ticket_price(){
        logger = extent.startTest("_027_valid_header_inside_ticket_price").assignCategory(CATEGORYTEST);
        String expected = "Price";
        String actualTxt = driver.findElement(By.xpath("//label[contains(text(), 'Price')]")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _028_valid_header_inside_ticket_price_type(){
        logger = extent.startTest("_028_valid_header_inside_ticket_price_type").assignCategory(CATEGORYTEST);
        String expected = "Price Type";
        String actualTxt = driver.findElement(By.xpath("//label[contains(text(), 'Price Type')]")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _029_valid_header_inside_ticket_app_icon(){
        logger = extent.startTest("_029_valid_header_inside_ticket_app_icon").assignCategory(CATEGORYTEST);
        String expected = "App Icon";
        String actualTxt = driver.findElement(By.xpath("//label[contains(text(), 'App Icon')]")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _030_valid_header_inside_ticket_apppreview_plus_screenshots(){
        logger = extent.startTest("_030_valid_header_inside_ticket_apppreview_plus_screenshots").assignCategory(CATEGORYTEST);
        String expected = "App Preview + Screenshots";
        String actualTxt = driver.findElement(By.xpath("//label[contains(text(), 'App Preview + Screenshots')]")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
}
