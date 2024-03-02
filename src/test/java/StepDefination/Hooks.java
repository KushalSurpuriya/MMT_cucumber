package StepDefination;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeAll;

public class Hooks {
	public static  WebDriver driver=new ChromeDriver();
	
	@BeforeAll
   public static void setup() throws IOException
   {
   	driver.get("https://www.makemytrip.com/");
   	driver.manage().window().maximize();
   }
	
	@AfterStep
	public void screenshot() throws IOException 
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		// Get the current time-stamp
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String timestamp = dateFormat.format(new Date());
		
		// Create a unique filename using the time-stamp
		String target = System.getProperty("user.dir") + "/ScreenShot/";
		String filename = target + "screenshot_" + timestamp + ".png";
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File(filename);
		FileUtils.copyFile(src, trg);
	}
//	public void addScreenshot(Scenario scenario) {       
//	       	TakesScreenshot ts=(TakesScreenshot) driver;
//	       	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
//	       	scenario.attach(screenshot, "image/png",scenario.getName());
//	   }

   @AfterAll
   public static  void tearDown() {
      driver.quit();
   }
}
