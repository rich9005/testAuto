package Sample;
import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.poi.hssf.usermodel.*;


public class sample1 {

	public static void main(String args[]){
		
		try {
	        // Open the Excel file
	        FileInputStream fis = new FileInputStream("C:\\Users\\Richard\\workspace\\testQA\\res\\carNames.xls");
	        // Access the required test data sheet
	        HSSFWorkbook wb = new HSSFWorkbook(fis);
	        HSSFSheet sheet = wb.getSheet("testdata");
	        // Loop through all rows in the sheet
	        // Start at row 1 as row 0 is our header row
	        // count= sheet.getLastRowNum();
	        for(int count = 1;count<=3;count++){
	            HSSFRow row = sheet.getRow(count);
	            System.out.println("Running test case " + row.getCell(0).toString());
	            // Run the test for the current test data row
	            searchEbay(row.getCell(0).toString());
	    //        runTest(row.getCell(1).toString(),row.getCell(2).toString());
	        }
	        fis.close();
	    } catch (IOException e) {
	        System.out.println("Test data file not found");
	    }   
	}
	
	public static void searchEbay (String searchStr){
		
		WebDriver driver ;
		System.setProperty("webdriver.gecko.driver", "C:\\eclipse-committers-mars-R-win32-x86_64\\eclipse\\geckodriver-v0.11.1-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://www.ebay.com");
		System.out.println(driver.getTitle());
		driver.findElement(By.id("gh-ac")).sendKeys(searchStr);
		driver.findElement(By.id("gh-btn")).click();
		
		//System.out.println("Listing Count : "+driver.findElement(By.cssSelector(".rcnt")).getText());
		driver.close();
		
		
	
	}
	
}
