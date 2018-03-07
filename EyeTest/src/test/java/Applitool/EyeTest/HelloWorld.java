package Applitool.EyeTest;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.FileLogger;
import com.applitools.eyes.RectangleSize;

public class HelloWorld {

	public WebDriver driver;
	public Eyes eyes = new Eyes();

	@DataProvider(name = "Resolution")
	Object[][] data_resolutions() {
		return new Object[][] { { 1536, 760 }, { 1024, 768 }, { 800, 600 }, };
	}

	@Test(dataProvider = "Resolution")
	public void Responsive(Integer width, Integer height) {

		// Start the test and set the browser's viewport size to 800x600.
		eyes.open(driver, "Hello World!", "My first Selenium Java test!", new RectangleSize(width, height));

		// Navigate the browser to the "hello world!" web-site.
		driver.get("https://applitools.com/helloworld?diff2");

		// Visual checkpoint #1.
		eyes.checkWindow("Hello!");

		// Click the "Click me!" button.
		driver.findElement(By.tagName("button")).click();

		// Visual checkpoint #2.
		eyes.checkWindow("Click!");

		// End the test.
		eyes.close();
	}

	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "c:\\Automatisation\\chromedriver.exe");

		// Open a Chrome browser.
		driver = new ChromeDriver();

		// Initialize the eyes SDK and set your private API key.

		eyes.setApiKey("YM6aQGIgnE5cMJzeON1q77j2UpIYZAtvvYoOEiOcQJA110");
		// eyes.ForceFullPageScreenshot = true;
		// eyes.StitchMode = StitchModes.CSS;
		eyes.setLogHandler(new FileLogger("c:\\Automatisation\\file.log", true, true));

	}

	@AfterMethod
	public void teardown() {
		driver.quit();

		// If the test was aborted before eyes.close was called, ends the test as
		// aborted.
		eyes.abortIfNotClosed();
	}

}