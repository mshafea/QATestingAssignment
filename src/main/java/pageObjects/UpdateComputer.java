package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class UpdateComputer {

	WebDriver driver;

	@FindBy(css = "input[value='Save this computer']")
	private WebElement updateComputerButton;

	@FindBy(linkText = "Cancel")
	private WebElement cancelButton;

	@FindBy(id = "name")
	private WebElement computerName;

	@FindBy(css = "div[class='clearfix error']")
	private WebElement computerNameError;

	@FindBy(id = "introduced")
	private WebElement introducedDate;

	@FindBy(id = "discontinued")
	private WebElement discontinuedDate;

	@FindBy(name = "company")
	private WebElement companySelectList;

	@FindBy(css = "input[value='Delete this computer']")
	private WebElement deleteComputerButton;

	@FindBy(css = "section#main h1")
	private WebElement heading;

	public UpdateComputer(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String get_update_computer_heading() {
		return heading.getText();
	}

	public void update_computer_name(String name) {
		computerName.sendKeys(name);
	}

	public void clear_computer_name() {
		computerName.clear();
	}

	public void update_introduced_date(String iDate) {
		introducedDate.sendKeys(iDate);
	}

	public void clear_introduced_date() {
		introducedDate.clear();
	}

	public void update_discontinued_date(String dDate) {
		discontinuedDate.sendKeys(dDate);
	}

	public void clear_discontinued_date() {
		discontinuedDate.clear();
	}

	public void select_company_from_dropdown(String company) {
		Select selectCompany = new Select(companySelectList);
		selectCompany.selectByVisibleText(company);
	}

	public String get_update_alert_error_message() {
		return computerNameError.getText();
	}

	public void press_save_computer_button() {
		updateComputerButton.click();
	}

	public void press_cancel_button() {
		cancelButton.click();
	}

	public void press_delete_computer_button() {
		deleteComputerButton.click();
	}

	public void delete_computer(String computerName) {

	}
}
