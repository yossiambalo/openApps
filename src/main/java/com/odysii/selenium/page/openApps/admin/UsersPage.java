package com.odysii.selenium.page.openApps.admin;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Set;

public class UsersPage extends PageObject {

    @FindBy(className = "card-body")
    private List<WebElement> userList;
    @FindBy(id = "makeSearch")
    private WebElement makeSearchBtn;
    @FindBy(id = "editUser")
    WebElement editUserBtn;
    @FindBy(id = "newUserButton")
    WebElement addNewUser;
    @FindBy(id ="newUserButton")
    WebElement newUserButtonClick;


    public UsersPage(WebDriver driver) {
        super(driver);
    }
    public EditUser getUser(String developerEmail){
        boolean flag = false;
        int counter = 0;
        isElementPresent(makeSearchBtn);
        for (WebElement e : userList){
            if(e.findElement(By.className("text-truncate")).getText().trim().equals(developerEmail)){
                WebElement element = webDriver.findElement(By.id("editUser"+ counter));
                scrollDown(element);
                element.click();
                flag = true;
                break;
            }
            counter ++;
        }
        if (!flag){
            throw new WebDriverException("Developer with name: "+developerEmail+" not found, please make sure developer name exist!");
        }
         return new EditUser(webDriver);
    }

    public AddUser getAddNewUserPage(){
        newUserButtonClick.click();

        return new AddUser(webDriver);
    }

    public void ListItems (){
        List<WebElement> elements = webDriver.findElements(By.className("btn-primary"));
        java.util.Iterator<WebElement> i = elements.iterator();
        while(i.hasNext()) {
            WebElement element = i.next();
            if (element.isDisplayed()) {
                element.click();
                break;
            }

        }

    }

    public void searchField(String getFillEmailAddress3){
    WebElement searchField = webDriver.findElement(By.id("searchInput"));
        searchField.sendKeys(getFillEmailAddress3);

    }

    public String getOrgOfUser(){
        String y = "";

        List<WebElement> userRows = webDriver.findElements(By.className("card-body"));
        for(WebElement option : userRows){
            WebElement childElement = option.findElement(By.className("ml--2"));
            if (option.isDisplayed()){
                    y = childElement.getText();
            }
        }

        return y;

    }

}
