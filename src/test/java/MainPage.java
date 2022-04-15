import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.*;

public class MainPage extends PageBase{
	
    private final By loginBtn = By.xpath("//button[@class='sc-1m6grsw-0 iUbbix']");
    private final By emailInput = By.xpath("//input[@placeholder='chuck@norris.com']");
    private final By fnamelInput = By.xpath("//input[@name='firstName']");
    private final By lnamelInput = By.xpath("//input[@name='lastName']");
    private final By passInput = By.xpath("//input[@name='password']");
    private final By passConfnamelInput = By.xpath("//input[@name='confirmedPassword']");
    private final By termBox = By.xpath("//span[@class='sc-4qvwn2-0 gPKxJU']");
    private final By regBtn = By.xpath("//button[@class='sc-1m6grsw-0 jEVcGS']");
    private final By profileIcon = By.xpath("//button[@class='sc-1rer11e-0 uNcZM']");
    private final By logoutBtn = By.cssSelector("ul.sc-1ekpsoi-0.fuWXaS.sc-711nbw-1.fpUXow > li:last-child");
	
	public MainPage(WebDriver driver){
		super(driver);
		this.driver.get("https://cooltix.hu/");
	}
	
	public void openLogin(){
        WebElement loginElement = this.waitVisibiltyAndFindElement(loginBtn);
		loginElement.click();
	}
	
	public void registerEmail(String email){
        WebElement emailElement = this.waitVisibiltyAndFindElement(emailInput);
		emailElement.sendKeys(email + " \n");
	}
	
	public void fillRegistrationForm(String pass){
		
        WebElement fname = this.waitVisibiltyAndFindElement(fnamelInput);
		fname.sendKeys("First");
        WebElement lname = this.waitVisibiltyAndFindElement(lnamelInput);
		lname.sendKeys("Last");
        WebElement pass1 = this.waitVisibiltyAndFindElement(passInput);
		pass1.sendKeys(pass + "\n");
        WebElement pass2 = this.waitVisibiltyAndFindElement(passConfnamelInput);
		pass2.sendKeys(pass + "\n");
        WebElement checkbox = this.waitVisibiltyAndFindElement(termBox);
		checkbox.click();
        WebElement submitBtn = this.waitVisibiltyAndFindElement(regBtn);
		submitBtn.submit();
		
	}
	
	public void logout(){
        WebElement profileIconElement = this.waitVisibiltyAndFindElement(profileIcon);
		profileIconElement.click();
        WebElement logoutBtnElement = this.waitVisibiltyAndFindElement(logoutBtn);
		logoutBtnElement.click();
		try{
			Thread.sleep(3000);
		}catch(InterruptedException e){
			System.out.println(e);
		}
	}
		
}
