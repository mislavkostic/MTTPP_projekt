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
    public Persona testPersona;

    @BeforeMethod
    public void setupTests(){
        String testURL = "http://www.google.com";
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        testPersona = new Persona();

        driver.get(testURL);
        driver.findElement(By.xpath("//*[contains(text(), 'Slažem se')]")).click();
    }

    @Test
    public void googleResultTest() throws InterruptedException{
        String expected = "OpenCart Demo";

        WebElement searchTextBox = driver.findElement(By.name("q"));
        searchTextBox.sendKeys("opencart demo");
        searchTextBox.submit();
        Thread.sleep(2000);
        WebElement testLink = driver.findElement(By.xpath("//*[@id=\"rso\"]/div[2]/div/div/div/div[1]/a/h3"));
        Assert.assertEquals(testLink.getText(), expected);
    }

    @Test
    public void homepageTest() throws InterruptedException{
        String expected = "Your Store";
        String title;

        driver.navigate().to("https://demo.opencart.com/");
        title = driver.getTitle();
        Thread.sleep(2000);
        Assert.assertEquals(title, expected);
    }

    @Test
    public void registerTest() throws InterruptedException {
        driver.navigate().to("https://demo.opencart.com/");
        driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[1]/a")).click();

        //registration form
        driver.findElement(By.id("input-firstname")).sendKeys(testPersona.firstName);
        driver.findElement(By.id("input-lastname")).sendKeys(testPersona.lastName);
        driver.findElement(By.id("input-email")).sendKeys(testPersona.email);
        driver.findElement(By.id("input-telephone")).sendKeys(testPersona.phoneNumber);
        driver.findElement(By.id("input-password")).sendKeys(testPersona.password);
        driver.findElement(By.id("input-confirm")).sendKeys(testPersona.password);
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/a")).click();
        Thread.sleep(800);

        //logout
        driver.findElement(By.xpath("//*[@id=\"column-right\"]/div/a[13]")).click();
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/a")).click();
        Thread.sleep(1122);
    }

    @Test
    public void loginTest(){

    }

    @Test
    public void addToCartTest(){

    }

    @AfterMethod
    public void teardownTest(){
        driver.quit();
    }

    //TODO: login test
    //TODO: add to shopping cart test
    //TODO: README.md documentation
    //TODO: refactor

}
