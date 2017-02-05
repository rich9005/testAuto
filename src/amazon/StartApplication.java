package amazon;
 
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import testQA.dataProvider;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class StartApplication {
 
		private static AndroidDriver driver;
		public static void main(String[] args) throws MalformedURLException, InterruptedException {
 
			dataProvider dp = new dataProvider();
			String inputData[] ; 
			inputData = dp.provide("testData.xls","console");
			
			System.out.println("Hello" +inputData[1]+" : "+inputData[2]);
			File classpathRoot = new File(System.getProperty("user.dir"));
			File appDir = new File(classpathRoot, "/apkfiles/");
			File app = new File(appDir, "Amazon_Shopping.apk");
 
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
			capabilities.setCapability("deviceName", "nexus6ARM1");
			//capabilities.setCapability("deviceName", "03020bd808d4ead8");
			capabilities.setCapability("platformVersion", "6.0");
			//capabilities.setCapability("platformVersion", "6.0.1");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("app", app.getAbsolutePath());
			capabilities.setCapability("appPackage", "com.amazon.mShop.android.shopping");
			capabilities.setCapability("appActivity", "com.amazon.mShop.splashscreen.StartupActivity");
			//capabilities.setCapability("newCommandTimeout", 120);
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			System.out.println("Opened up");
			Thread.sleep(25000);
			WebDriverWait wait = new WebDriverWait(driver,59);
			wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.xpath("//android.widget.Button[@text='Skip sign in']"))); 
			
			driver.findElement(By.xpath("//android.widget.Button[@text='Skip sign in']"))
			.click();
			System.out.println("Skiped Signing In");
			Thread.sleep(20000);
			
			
			
			driver.findElement(By.xpath("//android.widget."
					+ "EditText[contains(@resource-id,'rs_search_src_text')]"))
			.sendKeys(inputData[0]);
			driver.pressKeyCode(AndroidKeyCode.ENTER);
			Thread.sleep(45000);
			System.out.println("Going to Locate Element For Test 1");			
			wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.xpath("//android.widget.TextView[contains"
							+ "(@resource-id,'rs_results_count_text')]"))); 
			String resultsText = driver.findElement
					(By.xpath("//android.widget.TextView"
							+ "[contains(@resource-id,'rs_results_count_text')]")).getText();
			String expectedText = "results";
			
			
			if (resultsText.toLowerCase().contains(expectedText.toLowerCase())){
				System.out.println("Results page Loaded:  " +resultsText);
				System.out.println("Test 1 Passed");
			}
			else{
				System.out.println("Test 1 Failed: Results Page Not Loaded");
			}
			
			///Test2
			driver.findElement(By.xpath("//android.widget."
					+ "EditText[contains(@resource-id,'rs_search_src_text')]"))
			.sendKeys(inputData[1]);
			
			driver.pressKeyCode(AndroidKeyCode.ENTER);
			Thread.sleep(45000);
			System.out.println("Going to Locate Element For Test 2");
			wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.xpath("//android.widget.TextView[contains"
							+ "(@resource-id,'rs_no_result_header')]"))); 
			String resultsText2 = driver.findElement
					(By.xpath("//android.widget.TextView"
							+ "[contains(@resource-id,'rs_no_result_header')]")).getText();
			String expectedText2 = "Your search \"asssddfghjitrewc\" did not match any products.";
			
			
			if (resultsText2.toLowerCase().contains(expectedText2.toLowerCase())){
				System.out.println("Header reads :  " +resultsText2);
				System.out.println("Test 2 Passed");
			}
			else{
				System.out.println("Test 2 Failed");
			}
			
			//////////////
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			Thread.sleep(10000);
			driver.quit();
 
	}
 
}