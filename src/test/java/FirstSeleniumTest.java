import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.support.ui.*;


public class FirstSeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10);
    }

    private final By listItem = By.id("requestPassword");
    private final By accountInput = By.id("account");
    private final By confirmMessage = By.id("confirmation");



    private WebElement waitVisibiiltyAndFindElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    @Test
    public void seleniumTest() {
        this.driver.get("http://selenium.thinkcode.se/");

        WebElement listItemElement = waitVisibiiltyAndFindElement(listItem );
        //System.out.println(listItemElement.getText());
        //Assert.assertTrue(listItemElement.getText().contains("Request password - fill out and submit a form"));
		listItemElement.click();
		
		WebElement accountInputElement = waitVisibiiltyAndFindElement(accountInput );
		accountInputElement.sendKeys("FB8ZAN \n");

		WebElement confirmMessageElement = waitVisibiiltyAndFindElement(confirmMessage );
        //System.out.println(confirmMessageElement.getText());
        Assert.assertTrue(confirmMessageElement.getText().contains("A new password has been sent to FB8ZAN"));
    }
    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
