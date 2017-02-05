package testQA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Initializer  {
	
	private String url = "http://www.topgear.com/";
	WebDriver driver ;
	
	public  Initializer(){
		System.setProperty("webdriver.gecko.driver",
				"C:\\eclipse-committers-mars-R-win32-x86_64\\eclipse\\geckodriver-v0.11.1"
				+ "-win64\\geckodriver.exe");
		this.driver = new FirefoxDriver();
		this.driver.get(url);
		
		}
	
	
	
}
