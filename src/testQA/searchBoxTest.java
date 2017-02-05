package testQA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
public class searchBoxTest extends Initializer{
	
	//This Test checks check-box entry and verifies the H1 inner text "Search results"
	// on the next page
	@Test
	public void checkSearchBox() throws InterruptedException{
		
		driver.findElement(By.id("edit-query")).sendKeys("BMW");
		driver.findElement(By.id("edit-submit-search-results")).click();
		String expectedString = "Search results";
		Thread.sleep(2000); //Since it takes time to load
		WebElement h1InnerText =  driver.findElement(By.xpath
				("//main[@id='main-content']/div[@class='l-container']/"
						+ "div[@class='l-content']/div[@class='l-content-inner']/"
						+ "div[@class='l-page-header']/h1"));
		String actualString = h1InnerText.getText();
		Assert.assertEquals( actualString,expectedString, 
				"Search Results Text doesn't match");
		
	}
	
	@AfterTest
	public void afterTest(){
		System.out.println("End Of Search Box Test");
		driver.close();
	}
}
