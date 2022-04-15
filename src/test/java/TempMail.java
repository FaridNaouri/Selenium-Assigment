import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.*;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class TempMail extends PageBase{
	
	public static String clipBoard = "";
    private final By icon = By.xpath("//button[@id='click-to-copy']");
	
	public TempMail(WebDriver driver){
		super(driver);
		this.driver.get("https://temp-mail.org/en/");
	}
	
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
	
}