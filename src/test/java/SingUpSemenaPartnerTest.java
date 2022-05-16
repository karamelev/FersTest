import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.InterruptedIOException;
import java.time.Duration;
import java.util.List;

public class SingUpSemenaPartnerTest {

    private WebDriver driver;

    @BeforeMethod (alwaysRun = true)
    @Before
    public void browserSetup() {
        System.out.println("----------hello---------");
        driver = new ChromeDriver();
    }

  @Test
    public void commonSearch() {

      driver.get("https://semena-partner.ru/");


        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("search_main")));
        WebElement searchInput = waitForElementLocatedBy(driver,By.id("search_main"));
        searchInput.sendKeys("Секатор");

        List<WebElement> submit = driver.findElements(By.xpath("//*[@class='Bottom-header_submit']"));
        submit.get(0).click();

        new WebDriverWait(driver,Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='Bottom-header_submit']")));
//        List<WebElement> searchResults = driver.findElements(By.xpath("//*[@class='Bottom-header_submit']"));
//        System.out.println("Search result number for requested term:"+ searchResults.size());

        Assert.assertTrue("Search result are empty",submit.size()>0);

    }
//    @AfterMethod (alwaysRun = true)
    @After
            public void browserOut(){
                 driver.quit();
                 driver = null;
            }

    private static WebElement waitForElementLocatedBy(WebDriver driver,By by) {
        return new WebDriverWait(driver,Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
