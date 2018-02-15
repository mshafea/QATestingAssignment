package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
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
	public void startBrowser(String browser) throws Exception {
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
	
	public static String getUrlFromProperty(String URL) {

		String file = "config.properties";
		FileInputStream fileInput = null;
		String returnedURL = null;

		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Properties prop = new Properties();

		// load properties file
		try {
			prop.load(fileInput);
			if (URL.equalsIgnoreCase("HOME")) {
				returnedURL = prop.getProperty("HOME_URL");
			} else if (URL.equalsIgnoreCase("ADD_COMPUTER")) {
				returnedURL = prop.getProperty("ADD_NEW_COMPUTER_URL");
			} else {
				System.out.println("Incorrect URL");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnedURL;
	}

	// Go to URL
	public void goTo(String url) {

		driver.get(url);
	}

	@AfterTest
	 public void teardown(){
		driver.close();
		driver.quit();
	}

}
