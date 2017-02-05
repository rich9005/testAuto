package testQA;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;

public class HomePageObjTest extends Initializer {

	dataProvider dp = new dataProvider();
	
	@Test
	public void verifyAllDropDownValuesManufacturer() throws Exception{
		String inputData[] ; 
		inputData = dp.provide("carNames.xls","testData");
		System.out.println(driver.getTitle());
		Select slc = new Select(driver.findElement(By.id("edit-manufacturer")));
		List <WebElement> listOfManufacturer = slc.getOptions();
	
		//checking all 63 elements in drop-down box against expected data present
		//in Excel Sheet
		for (int i=1 ; i<listOfManufacturer.size();i++){
			Assert.assertEquals(listOfManufacturer.get(i).getText().trim(),
					inputData[i].trim(), "Mismatch at index: "+i);
		}
	
	}
	
	@Test
	public void checkEnabledDrowDown(){
		String []inputData = dp.provide("carNames.xls","testData");
		Select slc = new Select(driver.findElement(By.id("edit-manufacturer")));
		slc.selectByVisibleText(inputData[3].trim());
		WebElement modeldisabled = driver.findElement(By.id("edit-car"));
		String modelDisabledValue = modeldisabled.getAttribute("disabled");
		Assert.assertEquals(modelDisabledValue,null, "Drop Down Not Enabled");
	}
	
	@Test
	public void checkDisabledDrowDown(){
		Select slc = new Select(driver.findElement(By.id("edit-manufacturer")));
		slc.selectByIndex(0);;
		WebElement modeldisabled = driver.findElement(By.id("edit-car"));
		String modelDisabledValue = modeldisabled.getAttribute("disabled");
		Assert.assertEquals(modelDisabledValue,"true", "Drop Down Not disabled");
	}
	
	@Test
	public void checkBrandLogoImagePresent(){
		WebElement img =  driver.findElement
				(By.xpath("//div[@class='header__main']/"
						+ "div[@class='header__logo']/a/img"));
		String path = img.getAttribute("src");
		String expectedPath = "http://www.topgear.com/sites/all/themes/custom/tg/logo.png";
		System.out.print("HEllo Path is -----------: "+path);
		Assert.assertEquals( path,expectedPath, "Image path does not match");
	}
	
	@Test
	public void checkLogoHyperlinkPresent(){
		WebElement anchorLink =  driver.findElement
				(By.xpath("//div[@class='header__main']/div[@class='header__logo']/a"));
		String path = anchorLink.getAttribute("href");
		String expectedPath = "http://www.topgear.com/";
		System.out.print("HEllo Path is -----------: "+path);
		Assert.assertEquals( path,expectedPath, "HyperLink href path does not match");
	}
	
	
	@AfterTest
	public void afterTest(){
		System.out.println("End Of HomePage Test");
		driver.close();
	}
}
