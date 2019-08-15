package com.odysii.selenium.page.openApps.dev;

import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UploadCode extends PageObject{

    @FindBy(id = "codeFile")
    WebElement agreeAndUpload;//codeFile
    @FindBy(id = "nextButton")
    WebElement next;
    @FindBy(id = "newAppUploadCode")
    WebElement uploadCodeBtn;
    @FindBy(id = "nextButton")
    List<WebElement> nexts;
    @FindBy(xpath = "//button[contains(text(), 'FROM MANIFEST')]")
    WebElement fromManiFestBtn;

    static List<String> fileList = new ArrayList<>();
    private  String OUTPUT_ZIP_FILE = System.getProperty("user.dir")+"\\src\\main\\resources\\code\\TH.zip";
    private  String SOURCE_FOLDER = System.getProperty("user.dir")+"\\src\\main\\resources\\code\\unzippedApp";
    public UploadCode(WebDriver driver) {
        super(driver);
    }
    public Marketing upload(String zipFile){
        editManiFest();
        replaceFile(System.getProperty("user.dir")+"\\src\\main\\resources\\code\\unzippedApp\\manifest.txt");
        //generateFileList(new File(SOURCE_FOLDER));
        isElementPresent(uploadCodeBtn);
        this.agreeAndUpload.sendKeys(getFile("code\\" +zipFile));
        wait(5000);
       if (isElementPresent(fromManiFestBtn)) {
           fromManiFestBtn.click();
       }
        if (next.isDisplayed()) {
            this.next.click();
        }else {
            WebDriverWait wait = new WebDriverWait(webDriver, 10000);
            wait.until(ExpectedConditions.elementToBeClickable(nexts.get(1)));
            nexts.get(1).click();
        }
        return new Marketing(webDriver);
    }

    private void editManiFest(){
        try {
            //Write Content
            FileWriter writer = new FileWriter(System.getProperty("user.dir")+"\\src\\main\\resources\\code\\unzippedApp\\manifest.txt");
//            writer.write("Name: Yossi_"+new Timestamp(System.currentTimeMillis())+"\n" +
            //writer.write("Name: Yossi_"+new Random().nextInt((10000 - 2) + 1) + 2+"\n" +
            writer.write("Name: Yossi_"+new Random().nextInt((10000 - 2) + 1) + 2);
            writer.write(System.getProperty("line.separator"));
            writer.write( "Version: 1.0."+new Random().nextInt((1000 - 2) + 1) + 2);
            writer.write(System.getProperty("line.separator"));
            writer.write("Title: appConfig");
            writer.write(System.getProperty("line.separator"));
            writer.write( "Description: application showing config details");
            writer.write(System.getProperty("line.separator"));
            writer.write("Author: ss");
            writer.write(System.getProperty("line.separator"));
            writer.write("Copyright: ss");
            writer.write(System.getProperty("line.separator"));
            writer.write( "License: ss1");
            writer.write(System.getProperty("line.separator"));
            writer.write("Type: application");
            writer.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private void replaceFile(String file) {
        Path myFilePath = Paths.get(file);
        Path zipFilePath = Paths.get(OUTPUT_ZIP_FILE);
        try (FileSystem fs = FileSystems.newFileSystem(zipFilePath, null)) {
            Path fileInsideZipPath = fs.getPath("/manifest.txt");
            Files.delete(fileInsideZipPath);
            Files.copy(myFilePath, fileInsideZipPath);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
