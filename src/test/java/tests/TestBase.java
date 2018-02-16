package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class TestBase {

	public static WebDriver driver;

	@BeforeTest
	@Parameters("browser")
	public void openBrowser(String browser) throws Exception {
		if (browser.equalsIgnoreCase("firefox")) {

			// create firefox instance
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
		// Check if parameter passed as 'chrome'
		else if (browser.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			// create Chrome instance
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} else {

			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
	}

	// Go to URL
	public void goTo(String url) {

		driver.get(url);
	}

	@AfterTest
	 public void teardown(){
		driver.close();
	}

}
