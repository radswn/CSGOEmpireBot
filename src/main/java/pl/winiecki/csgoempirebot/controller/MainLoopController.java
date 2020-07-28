package pl.winiecki.csgoempirebot.controller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import pl.winiecki.csgoempirebot.bet.Bet;
import pl.winiecki.csgoempirebot.bet.BetService;

import java.time.LocalDateTime;

@Component
public class MainLoopController {

    private final double startAmount = 0.1;
    private WebDriver driver;
    private WebDriverWait wait;
    private BetService betService;
    private BotController botController;

    public MainLoopController(BetService betService, WebDriver driver, WebDriverWait wait, BotController botController) {
        this.betService = betService;
        this.driver = driver;
        this.wait = wait;
        this.botController = botController;
    }
    
    public void loop() {

        driver.get("https://csgoempire.com/");
        login();

        wait.until((ExpectedCondition<Boolean>) driver1 -> driver
                .findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div/div/div[1]/div/div/div[6]/div[1]/button"))
                .getAttribute("class")
                .equalsIgnoreCase("bet-btn"));

        WebElement buttonCT = botController.findByXpath("/html/body/div[1]/div[1]/div[3]/div/div/div[1]/div/div/div[6]/div[1]/button");
        WebElement buttonT = botController.findByXpath("/html/body/div[1]/div[1]/div[3]/div/div/div[1]/div/div/div[6]/div[3]/button");
        WebElement coinInput = botController.findByXpath("/html/body/div[1]/div[1]/div[3]/div/div/div[1]/div/div/div[5]/div/div[1]/input");
        WebElement halfButton = botController.findByXpath("/html/body/div[1]/div[1]/div[3]/div/div/div[1]/div/div/div[5]/div/div[2]/div/div[7]/button");
        WebElement twiceButton = botController.findByXpath("/html/body/div[1]/div[1]/div[3]/div/div/div[1]/div/div/div[5]/div/div[2]/div/div[8]/button");


        double betAmount = startAmount;
        coinInput.sendKeys(String.valueOf(betAmount));

        while (getBalance() > 0) {

            String lastRollResult = null;

            for (int i = 0; i < 10; i++) {
                lastRollResult = getRollResultById(9 - i);
                if (lastRollResult.equalsIgnoreCase("t") || lastRollResult.equalsIgnoreCase("ct"))
                    break;
            }


            switch (lastRollResult) {
                case "ct": {
//                    buttonCT.click();
                    System.out.println("Bet na ct, kwota: " + betAmount);
                    break;
                }

                case "t": {
//                    buttonT.click();
                    System.out.println("Bet na t, kwota: " + betAmount);
                    break;
                }
            }

            wait.until((ExpectedCondition<Boolean>) driver1 -> !buttonCT.getAttribute("class").equalsIgnoreCase("bet-btn"));
            wait.until((ExpectedCondition<Boolean>) driver1 -> buttonCT.getAttribute("class").equalsIgnoreCase("bet-btn"));

            String rollResult = getRollResultById(9);
            boolean won = rollResult.equalsIgnoreCase(lastRollResult);

            Bet bet = new Bet(LocalDateTime.now(), betAmount, getBalance(), lastRollResult, rollResult, won);
            betService.addBet(bet);
//            System.out.println(bet);

            if (!won) {
                betAmount *= 2;
                twiceButton.click();
            } else {
                while (betAmount != startAmount) {
                    betAmount /= 2;
                    halfButton.click();
                }
            }
        }
    }

    private void login() {

        WebElement signIn = botController.findByXpath("/html/body/div[1]/div[1]/div[2]/div[1]/div/div[3]/div[2]/div[3]/a");
        signIn.click();
        driver.findElement(By.id("steamAccountName")).sendKeys("");
        driver.findElement(By.id("steamPassword")).sendKeys("");
        driver.findElement(By.id("imageLogin")).click();
        wait.until(ExpectedConditions.titleIs("CSGOEmpire - The Original CSGO Skin Gambling Site!"));

    }

    private String getRollResultById(int id) {
        return driver
                .findElements(By.className("previous-rolls-item"))
                .get(id)
                .findElement(By.tagName("div"))
                .getAttribute("class")
                .substring(46);
    }

    private double getBalance(){
        WebElement balance = botController.findByXpath("/html/body/div[1]/div[1]/div[2]/div[1]/div/div[3]/div[2]/div[3]/div/div/span/span");
        return Double.parseDouble(balance.getText().replaceAll(",", "."));
    }

}
