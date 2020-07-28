package pl.winiecki.csgoempirebot.controller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Controller;

@Controller
public class BotController {

    private WebDriver driver;

    public BotController(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement findByXpath(String xpath){
        return driver.findElement(By.xpath(xpath));
    }

    public void end(){
        driver.quit();
    }
}
