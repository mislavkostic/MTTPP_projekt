import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumTests{
    //-------------------Global Variables-----------------------------------
    public WebDriver driver;

    @BeforeMethod
    public void setupTests(){
        String testURL = "http://www.google.com";
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();

        driver.get(testURL);
        driver.findElement(By.xpath("//*[contains(text(), 'Slažem se')]")).click();
    }

    @Test
    public void google_result() throws InterruptedException{
        String expected = "OpenCart Demo";

        WebElement searchTextBox = driver.findElement(By.name("q"));
        searchTextBox.sendKeys("opencart demo");
        searchTextBox.submit();
        Thread.sleep(2000);
        WebElement testLink = driver.findElement(By.xpath("//*[@id=\"rso\"]/div[2]/div/div/div/div[1]/a/h3"));
        Assert.assertEquals(testLink.getText(), expected);
    }

    @Test
    public void homepage_load() throws InterruptedException{
        String expected = "Your Store";
        String title;

        driver.navigate().to("https://demo.opencart.com/");
        title = driver.getTitle();
        Thread.sleep(2000);
        Assert.assertEquals(title, expected);
    }

    @AfterMethod
    public void teardownTest(){
        driver.quit();
    }


    //TODO: register test
    //TODO: login test
    //TODO: browse shop test
    //TODO: add to shopping cart test
    //TODO: README.md documentation
    //TODO: refactor

}
