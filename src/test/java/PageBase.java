import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.*;

public class PageBase {
	
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
	
}
