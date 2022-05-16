import org.junit.Test;
import org.junit.rules.Timeout;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SingUp {
    @Test
    public void singUp() throws InterruptedException {
//        System.setProperty("webdriver.chrome.driver","C:/Drivers/chromedriver.exe");
        WebDriver driver = new EdgeDriver();
        
        driver.get("https://semena-partner.ru/");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("search_main")));
        WebElement searchInput = waitForElementLocatedBy(driver,By.id("search_main"));
//        WebElement searchInput = driver.findElement(By.id("search_main"));
        searchInput.sendKeys("Секатор");
        List<WebElement> submit = driver.findElements(By.xpath("//*[@class='Bottom-header_submit']"));
        submit.get(0).click();
        new WebDriverWait(driver,Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='Bottom-header_submit']")));
//        Thread.sleep(2000);
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("Timeout for waiting");

        driver.quit();
    }

    private static WebElement waitForElementLocatedBy(WebDriver driver,By by) {
        return new WebDriverWait(driver,Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
