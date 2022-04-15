import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.JavascriptExecutor;

public class SeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    private WebElement waitVisibiltyAndFindElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    @Test
    public void seleniumTest() {
		
		TempMail tempMail = new TempMail(this.driver);
		String email = tempMail.getTempMail();
		String pass = "qazxsw123";
		
		MainPage mainPage = new MainPage(this.driver);
		mainPage.openLogin();
		mainPage.registerEmail(email);
		mainPage.fillRegistrationForm(pass);
		
		EventPage eventPage = new EventPage(this.driver);
		eventPage.selectCategoryandEvent();
		eventPage.selecTicketandCheckout();
		eventPage.fillBillingForm(email);
		
		// wait 1 second to redirect to bank payment page
		try{
			Thread.sleep(1000);
		}catch(InterruptedException e){
			System.out.println(e);
		}
		
		eventPage.alert();
		mainPage.logout();		
    }
    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}

/*

[-----DONE-----]	Fill simple form and send (eg. Login)
[-----DONE-----]	Form sending with user
[-----DONE-----]	Logout
[-----DONE-----]	Fill input (text,radio,check...)
[-----DONE-----]	Send a form
[-----DONE-----]	Static Page test
Multiple page test from array (easily extendable static page tests)
[-----DONE-----]	complex xpath (eg. //div//a[@href='asd'])
Filling or reading textarea content
[-----DONE-----]	Filling or reading drop-down
[-----DONE-----]	Filling or reading Radio button
[-----DONE-----]	At least 4 class
[-----DONE-----]	At least 6 class
[-----DONE-----]	At least 8 class	
[-----DONE-----]	Explicit wait
[-----DONE-----]	Reading the page title
[-----DONE-----]	Page object pattern
[-----DONE-----]	BasePage object class
[-----DONE-----]	Test suite looks like readable test description

*/