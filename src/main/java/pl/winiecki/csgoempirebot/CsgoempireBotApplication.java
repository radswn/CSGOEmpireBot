package pl.winiecki.csgoempirebot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import pl.winiecki.csgoempirebot.controller.MainLoopController;


@SpringBootApplication
public class CsgoempireBotApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CsgoempireBotApplication.class, args);

        MainLoopController controller = context.getBean(MainLoopController.class);
        controller.loop();
    }

    @Bean
    public WebDriver getDriver(){
        return new FirefoxDriver();
    }

    @Bean
    public WebDriverWait getWait(){
        return new WebDriverWait(getDriver(), 60);
    }

}
