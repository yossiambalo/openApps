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
    @BeforeClass
    public void login(){
        user = new User(driver);
        adminPage =(AdminPage) user.login("yossi.ambalo.odysii@gmail.com", "Aa123456",UserType.ADMIN);
        category = "Admin Content";

    }
    @Test
    public void _001_valid_header_support_tickets(){
        String expected = "Support Tickets";
        adminPage.getSupportTickets();
        String actualTxt = driver.findElement(By.className("h2")).getText();
        Assert.assertEquals(actualTxt,expected);
        String expectedSdbr = "ADMIN - TICKETS";
        String actualSdbr = driver.findElement(By.id("navItem0")).getText().toUpperCase().trim();
        Assert.assertEquals(actualSdbr,expectedSdbr);


    }
    @Test
    public void _002_valid_TICKET_id(){
        String expected = "ID";
        String actauTxt = driver.findElement(By.className("col-1")).getText();
        Assert.assertEquals(expected,actauTxt);

    }
    @Test
    public void _003_valid_ticket_application_name(){
        String expected = "Application name";
        String actualTxt = driver.findElement(By.xpath("//div[contains(text(), 'Application name')]")).getText();
        Assert.assertEquals(actualTxt,expected);

    }
    @Test
    public void _004_valid_ticket_version_column_name(){
        String expected = "Version #";
        String actualTxt = driver.findElement(By.xpath("//div[contains(text(), 'Version #')]")).getText();
        Assert.assertEquals(actualTxt,expected);

    }
    @Test
    public void _005_valid_ticket_subject_column_name(){
        String expected = "Subject";
        String actualTxt = driver.findElement(By.xpath("//div[contains(text(), 'Subject')]")).getText();
        Assert.assertEquals(actualTxt,expected);

    }
    @Test
    public void _006_valid_ticket_created_column_name(){
        String expected = "Created";
        String actualTxt = driver.findElement(By.xpath("//div[contains(text(), 'Created')]")).getText();
        Assert.assertEquals(actualTxt,expected);

    }
    @Test
    public void _007_valid_last_activity_column_name(){
        String expected = "Last activity";
        String actualTxt = driver.findElement(By.xpath("//div[contains(text(), 'Last activity')]")).getText();
        Assert.assertEquals(actualTxt,expected);

    }
    @Test
    public void _008_valid_status_column_name(){
        String expected = "Status";
        String actaulTxt = driver.findElement(By.xpath("//div[contains(text(), 'Status')]")).getText();
        Assert.assertEquals(expected,actaulTxt);

    }
    @Test
    public void _009_valid_button_text_back_to_support_tickets(){
        adminPage.getSupportTickets();
        adminPage.getTicket(2);
        String expected = "Back to Admin - Tickets";
        String actualTxt = driver.findElement(By.id("BackNavigationButton")).getText();
        Assert.assertEquals(actualTxt,expected);

    }
    @Test
    public void _010_valid_button_text_preview(){
        String expected = "Preview";
        String actualTxt = driver.findElement(By.id("adminTicketPreview")).getText();
        Assert.assertEquals(expected.trim(),actualTxt.trim());

    }
    @Test
    public void _011_valid_button_text_preview(){
        String expected = "Download";
        String actualTxt = driver.findElement(By.id("adminTicketDownload")).getText();
        Assert.assertEquals(expected.trim(),actualTxt.trim());

    }
    @Test
    public void _012_valid_text_status(){
        String expected = "Status";
        String actualTxt = driver.findElement(By.xpath("//h6[contains(text(), 'Status')]")).getText();
        Assert.assertEquals(actualTxt,expected);

    }
    @Test
    public void _013_valid_text_comments(){
        String expected = "Comments";
        String actualTxt = driver.findElement(By.xpath("//h6[contains(text(), 'Comments')]")).getText();
        Assert.assertEquals(actualTxt,expected);

    }
    @Test
    public void _014_valid_text_status_approved(){
        String expected = "Approved";
        String actualTxt = driver.findElement(By.id("adminTicketApproved")).getText();
        Assert.assertEquals(actualTxt,expected);

    }
    @Test
    public void _015_valid_text_status_reject_no_fee(){
        String expected = "Reject - no fee";
        String actualTxt = driver.findElement(By.id("adminTicketReject")).getText();
        Assert.assertEquals(actualTxt,expected);

    }
    @Test
    public void _016_valid_text_status_reject_with_fee(){
        String expected = "Reject with fee";
        String actualTxt = driver.findElement(By.id("adminTicketRejectFee")).getText();
        Assert.assertEquals(actualTxt,expected);

    }
    @Test
    public void _017_valid_text_status_open(){
        String expected = "Open";
        String actualTxt = driver.findElement(By.id("adminTicketOpen")).getText();
        Assert.assertEquals(actualTxt,expected);

    }
    @Test
    public void _018_valid_button_text_update(){
        String expected = "UPDATE";
        String actualTxt = driver.findElement(By.id("finishButtonAdminTicket")).getText();
        Assert.assertEquals(actualTxt,expected);

    }
    @Test
    public void _019_valid_header_inside_ticket_name(){
        String expected = "Developer";
        String actualTxt = driver.findElement(By.xpath("//label[contains(text(), 'Developer')]")).getText();
        Assert.assertEquals(actualTxt,expected);

    }
    @Test
    public void _020_valid_header_inside_ticket_subtitle(){
        String expected = "Subtitle";
        String actualTxt = driver.findElement(By.xpath("//label[contains(text(), 'Subtitle')]")).getText();
        Assert.assertEquals(actualTxt,expected);

    }
    @Test
    public void _021_valid_header_inside_ticket_category(){
        String expected = "Category";
        String actualTxt = driver.findElement(By.xpath("//label[contains(text(), 'Category')]")).getText();
        Assert.assertEquals(actualTxt,expected);

    }
    @Test
    public void _022_valid_header_inside_ticket_language(){
        String expected = "Language";
        String actualTxt = driver.findElement(By.xpath("//label[contains(text(), 'Language')]")).getText();
        Assert.assertEquals(actualTxt,expected);

    }
    @Test
    public void _023_valid_header_inside_ticket_availability(){
        String expected = "Availability";
        String actualTxt = driver.findElement(By.xpath("//label[contains(text(), 'Availability')]")).getText();
        Assert.assertEquals(actualTxt,expected);

    }
    @Test
    public void _024_valid_header_inside_ticket_retailers(){
        String expected = "Retailers";
        String actualTxt = driver.findElement(By.xpath("//label[contains(text(), 'Retailers')]")).getText();
        Assert.assertEquals(actualTxt,expected);

    }
    @Test
    public void _025_valid_header_inside_ticket_promotional_text(){
        String expected = "Promotional Text";
        String actualTxt = driver.findElement(By.xpath("//label[contains(text(), 'Promotional Text')]")).getText();
        Assert.assertEquals(actualTxt,expected);

    }
    @Test
    public void _026_valid_header_inside_ticket_price(){
        String expected = "Price";
        String actualTxt = driver.findElement(By.xpath("//label[contains(text(), 'Price')]")).getText();
        Assert.assertEquals(actualTxt,expected);

    }
    @Test
    public void _027_valid_header_inside_ticket_price_type(){
        String expected = "Price Type";
        String actualTxt = driver.findElement(By.xpath("//label[contains(text(), 'Price Type')]")).getText();
        Assert.assertEquals(actualTxt,expected);

    }
    @Test
    public void _028_valid_header_inside_ticket_app_icon(){
        String expected = "App Icon";
        String actualTxt = driver.findElement(By.xpath("//label[contains(text(), 'App Icon')]")).getText();
        Assert.assertEquals(actualTxt,expected);

    }
    @Test
    public void _029_valid_header_inside_ticket_apppreview_plus_screenshots(){
        String expected = "App Preview + Screenshots";
        String actualTxt = driver.findElement(By.xpath("//label[contains(text(), 'App Preview + Screenshots')]")).getText();
        Assert.assertEquals(actualTxt,expected);

    }
}
