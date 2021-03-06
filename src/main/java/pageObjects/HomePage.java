package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	@FindBy(id = "add")
	private WebElement addComputerButton;

	@FindBy(id = "searchsubmit")
	private WebElement filterByNameButton;

	@FindBy(css = "input#searchbox")
	private WebElement searchbox;

	@FindBy(css = "section#main h1")
	private WebElement heading;

	@FindBy(css = "div[class='alert-message warning']")
	private WebElement addAlertMessage;

	@FindBy(css = "div[class='alert-message warning']")
	private WebElement updateAlertMessage;

	@FindBy(css = "div[class='alert-message warning']")
	private WebElement deleteAlertMessage;

	@FindBy(css = "div[class='well']")
	private WebElement nonExitingComputerError;

	@FindBy(xpath = "//*[@id='main']/table/tbody")
	private WebElement computerTable;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String get_home_page_heading() {
		return heading.getText();
	}

	public void press_add_new_computer_button() {
		addComputerButton.click();
	}

	public void filter_by_name(String name) {
		searchbox.sendKeys(name);
	}

	public void press_filter_by_name_button() {
		filterByNameButton.click();
	}

	public String get_computer_link(String computerName) {
		return driver.findElement(By.partialLinkText(computerName)).getAttribute("href");
	}

	public String get_add_alert_message() {
		return addAlertMessage.getText();
	}

	public String get_update_alert_message() {
		return updateAlertMessage.getText();
	}

	public String get_delete_alert_message() {
		return deleteAlertMessage.getText();
	}

	public void retrieve_computer(String computerName) {
		filter_by_name(computerName);
		press_filter_by_name_button();
	}

	public String get_non_existing_computer_error() {
		return nonExitingComputerError.getText();
	}
}
