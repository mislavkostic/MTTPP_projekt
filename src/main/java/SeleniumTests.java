import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class SeleniumTests{
    //-------------------Global Variables-----------------------------------
    public WebDriver driver;
    public EmailGenerator generator = new EmailGenerator();
    public Persona testPersona = new Persona(generator);

    //--------------------Tests-----------------------------------------
    @BeforeMethod
    public void setupTests(){
        String testURL = "http://www.google.com";
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();

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

    @Test(priority = 3)
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
        driver.findElement(By.id("input-confirm")).sendKeys(testPersona.password); //confirm password
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/a")).click();
        Thread.sleep(1000);

        //logout
        driver.findElement(By.xpath("//*[@id=\"column-right\"]/div/a[13]")).click();
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/a")).click();
    }

    @Test(priority = 4)
    public void loginTest() {
        String expected = "My Account";
        String title;

        driver.navigate().to("https://demo.opencart.com/");
        driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]/a")).click();

        //login form
        driver.findElement(By.id("input-email")).sendKeys(testPersona.email);
        driver.findElement(By.id("input-password")).sendKeys(testPersona.password);
        driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();

        title = driver.getTitle();
        Assert.assertEquals(title, expected);
    }

    @Test(priority = 5)
    public void addToCartTest() throws InterruptedException {
        //navigate to item
        driver.navigate().to("https://demo.opencart.com/");
        driver.findElement(By.linkText("Desktops")).click();
        driver.findElement(By.linkText("Mac (1)")).click();

        //add item to cart
        Thread.sleep(1000);
        driver.findElement(By.linkText("iMac")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("button-cart")).click();

        //check cart
        Thread.sleep(400);
        driver.findElement(By.id("cart")).click();
        driver.findElement(By.className("fa-shopping-cart")).click();

        //check if item exists in cart
        Assert.assertNotNull(driver.findElement(By.linkText("iMac")));

    }

    @AfterMethod
    public void teardownTest(){
        driver.quit();
    }

}
