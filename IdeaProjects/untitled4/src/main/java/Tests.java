import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.channels.SelectableChannel;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Tests {

    WebDriver driver;
    String URL = "https://formy-project.herokuapp.com/form";
    String CHROME_PATH = "C:\\Users\\abhilasha\\Desktop\\Personal\\chromedriver.exe";


    @FindBy(id="first-name")
    WebElement fName;

    @FindBy(id="last-name")
    WebElement lName;

    By job_title = By.id("job-title");
    By radHighEdu = By.cssSelector("input[type='radio']");
    By chkSex = By.cssSelector("input[type='checkbox']");
    By drpYearsofXp = By.cssSelector("option[value]");
    By datepick = By.id("datepicker");
    By btnSubmit = By.xpath(".//a[contains(text(),'Submit')]");


    @BeforeTest
    public void initDriver() {

        System.setProperty("webdriver.chrome.driver", CHROME_PATH);
        driver = new ChromeDriver();
        PageFactory.initElements(driver,this);

        HashMap<Integer,String> items = new HashMap<>();
        items.put(1,"one");
        items.put(2,"two");
        items.put(3,"Three");

        for (var key:items.keySet()) {
            System.out.println(key+" :key and values are: "+items.get(key));
        }

        Map<Integer,String> map = new HashMap<>();


    }

    @Test
    public void test() {
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        fName.sendKeys("Abhi");
        lName.sendKeys("gowda");
        driver.findElement(job_title).sendKeys("SDET");
        List<WebElement> radiobtns = driver.findElements(radHighEdu);
        for (WebElement btn : radiobtns) {
            btn.click();
        }
        List<WebElement> checkboxes = driver.findElements(chkSex);
        for (WebElement box : checkboxes) {
            box.click();
        }

        List<WebElement> xp = driver.findElements(drpYearsofXp);
        WebElement element = xp.get(2);
        element.click();

        driver.findElement(datepick).sendKeys("01/01/2021");
        driver.findElement(btnSubmit).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.titleContains("Formy"));

    }

    @AfterTest
    public void closeTest() {
        driver.quit();
    }

}
