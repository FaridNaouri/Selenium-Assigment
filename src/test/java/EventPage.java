import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.JavascriptExecutor;

public class EventPage extends PageBase{

	private JavascriptExecutor js=(JavascriptExecutor)driver;
    private final By categoryTravel = By.cssSelector("ul.sc-186a9jl-1.kXtDHn > li:last-child");
    private final By selectedEvent = By.xpath("//a[@href='/hu/event/624ad5e8ae297f3e9bc420f2']");
    private final By plusBtn = By.cssSelector("div.d2m8ox-0.imPzhW.fl292y-3.kKimgW > div:last-child div.o0tf15-0.hsQpR > button:last-child");
    private final By checktBtn = By.cssSelector(".sc-1g8upvm-2.bCSrMz > button");
    private final By bName = By.xpath("//input[@name='billing.name']");
    private final By bAddress = By.xpath("//input[@name='billing.address.addressLine']");
    private final By bPostCode = By.xpath("//input[@name='billing.address.postalCode']");
    private final By bCity = By.xpath("//input[@name='billing.address.city']");
    private final By bCountry = By.xpath("//input[@name='billing.address.countryCode']");
    private final By nextbtn = By.xpath("//button[@class='sc-1m6grsw-0 jEVcGS']");
    private final By attendName = By.cssSelector("input.sc-1c8q6s8-1.heGloG");
    private final By attendemail = By.xpath("//input[@placeholder='chuck@norris.com']");
    private final By ticketTP = By.xpath("//span[@class='sc-4qvwn2-0 gPKxJU']");
    private final By paybtn = By.cssSelector("button.sc-1m6grsw-0.jEVcGS");
	
	public EventPage(WebDriver driver){
		super(driver);
	}
	
	public void selectCategoryandEvent(){
		WebElement categoryTravelElement = this.waitVisibiltyAndFindElement(categoryTravel);
		categoryTravelElement.click();
		//wait 2 seconds to return results
		try{
			Thread.sleep(2000);
		}catch(InterruptedException e){
			System.out.println(e);
		} 
		WebElement selectedEventElement = this.waitVisibiltyAndFindElement(selectedEvent);
		selectedEventElement.click();
	}
	
	public void selecTicketandCheckout(){
		WebElement plusBtnElement = this.waitVisibiltyAndFindElement(plusBtn);
		plusBtnElement.click();
		WebElement checktBtnElement = this.waitVisibiltyAndFindElement(checktBtn);
		checktBtnElement.click();
	}
	
	public void fillBillingForm(String email){
		
		WebElement bNameElement = this.waitVisibiltyAndFindElement(bName);
		bNameElement.sendKeys("Test Billing Name");
		WebElement bAddressElement = this.waitVisibiltyAndFindElement(bAddress);
		bAddressElement.sendKeys("Winer Leo U");
		WebElement bPostCodeElement = this.waitVisibiltyAndFindElement(bPostCode);
		bPostCodeElement.sendKeys("1065");
		WebElement bCityElement = this.waitVisibiltyAndFindElement(bCity);
		bCityElement.sendKeys("Budapest \n");
		try{
			Thread.sleep(1000);
		}catch(InterruptedException e){
			System.out.println(e);
		}
		WebElement attendNameElement = this.waitVisibiltyAndFindElement(attendName);
		attendNameElement.sendKeys("Test Attendee Name \n");
		WebElement attendemailElement = this.waitVisibiltyAndFindElement(attendemail);
		attendemailElement.sendKeys(email + " \n");
		try{
			Thread.sleep(1000);
		}catch(InterruptedException e){
			System.out.println(e);
		}
		WebElement ticketTPElement = this.waitVisibiltyAndFindElement(ticketTP);
		ticketTPElement.click();
		WebElement paybtnElement = this.waitVisibiltyAndFindElement(paybtn);
		paybtnElement.click();
		
	}
	
	public void alert(){
		JavascriptExecutor js =(JavascriptExecutor)(this.driver);
		String pageTitle = js.executeScript("return document.title;").toString();
		js.executeScript("alert('Welcome To Selenium Testing, oh and page title = \"' + arguments[0] + '\" , Lets check other functionalities');", pageTitle);
		//wait 10 seconds for the user to read the message and click "ok" in the alert
		try{
			Thread.sleep(10000);
		}catch(InterruptedException e){
			System.out.println(e);
		} 
		this.driver.get("https://cooltix.hu/");
	}
}
