# TITLE

```
    CODE_SNIP
```

TEXT text text TExT text text text TExT text text text TExT text

- List List
- List List: 
    - List List
    - List List

# Selenium Assignment

# Java Files

- EventPage.java
- MainPage.java
- PageBase.java
- SeleniumTest.java
- TempMail.java


# TempMail.java

Contains custom code to fetch a random generated email from https://temp-mail.org/en/, save it on clipoard and saves it in a String variable for later use
Included functions:

```
public String getTempMail(){
		try{
			//until temp mail generates a temp email
			Thread.sleep(7000);
		}catch(InterruptedException e){
			System.out.println(e);
		}    
		
        WebElement iconElement = this.waitVisibiltyAndFindElement(icon);
		iconElement.click();
		
		//paste clipboard latest content 'copied email'
		clipBoard = getClipBoard();
		return clipBoard;
	}
	
	public String getClipBoard(){
		try {return (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);} 
		catch (HeadlessException e) {e.printStackTrace();} 
		catch (UnsupportedFlavorException e) {e.printStackTrace();} 
		catch (IOException e) {e.printStackTrace();}
		return "";
	}
```

# PageBase.java

Contains repetative scripts saved in a 'template' structure that includes: 

```
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    public PageBase(WebDriver driver){
		this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
	}
	
	protected WebElement waitVisibiltyAndFindElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }
```

# MainPage.java

Included functions:

```
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
```

# EventPage.java

Included functions:

```
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
```

# SeleniumTest.java

Starts off by requesting a rendom email and saves it in a variable with a fixed password for better use

```
TempMail tempMail = new TempMail(this.driver);
String email = tempMail.getTempMail();
String pass = "SQLA1Selenium1Assignment";
 ```
Then switch to https://cooltix.hu/, click on login menu item, paste the previously copied email in the input field, and fill the registration form data which contains:
- First Name
- Last Name
- Password
- Passowrd Confirmation
- Term and Policy Checkbox
- Register Button

```
MainPage mainPage = new MainPage(this.driver);
mainPage.openLogin();
mainPage.registerEmail(email);
mainPage.fillRegistrationForm(pass);
```
After That we pass the driver to the homepage as logged in user
In the event taps It'll select 'Trave' category and the last item in the list titled 'Hike & Wine - Etyeki Piknik by ELB / 6th May'
P.S. I chose that because of it's date May 6 and available tickets for purchace when you read this
Then we select 'Ticket without ELB Card - Etyek Hike & Wine, 6th May' by clicking +
Finish up by clicking on the 'Checkout' button that redirects to a more detailed form to be filled from the user that includes:
-Customer Information: [Registered User]
  -First Name [from regestration]
  -Last Name  [from regestration]
  -Email    [from regestration]
  -Confirm Email  [from regestration]
-Billing Information: [Alias or whatever]
  -Name
  -Address Line - Postal Code
  -City and Country from dropdown box
  
-Attendee Name
-Email Address again idk why tbh

In the end it shows the transaction details, a checkbox at the bottom for terms and conditions and a payment button which we click

```
EventPage eventPage = new EventPage(this.driver);
eventPage.selectCategoryandEvent();
eventPage.selecTicketandCheckout();
eventPage.fillBillingForm(email);
```
Then it redirects to the bank's website for creditcard information which we will ignore and generate a simple javascript alert using JavascriptExecutor that includes the current page's title as required in the points spreadsheet
And then simply logout :/

```
    eventPage.alert();
		mainPage.logout();
```




















