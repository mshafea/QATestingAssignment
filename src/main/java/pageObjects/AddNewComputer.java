package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class AddNewComputer {

	WebDriver driver;
	
	@FindBy (css="input[value='Create this computer']")
	private WebElement createComputerButton;
	
	@FindBy (linkText="Cancel")
	private WebElement cancelButton;
	
	@FindBy (id="name")
	private WebElement computerName;
	
	@FindBy (id="introduced")
	private WebElement introducedDate;
	
	@FindBy (id="discontinued")
	private WebElement discontinuedDate;
	
	@FindBy (name="company")
	private WebElement companySelectList;
	
	@FindBy(css="div[class='clearfix error']")
	private WebElement computerNameError;
	
	@FindBy (css="section#main h1")
	private WebElement heading;
	
	public AddNewComputer(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void press_create_computer_button(){
		createComputerButton.click();
	}
	
	public void press_cancel_button(){
		cancelButton.click();
	}
	
	public String get_add_computer_heading(){
		return heading.getText();
	}
	
	public String get_url(){
		return driver.getCurrentUrl();
	}
	
	public void enter_computer_name(String name) {
		computerName.sendKeys(name);
	}
	
	public void enter_introduced_date(String iDate) {
		introducedDate.sendKeys(iDate);
	}
	
	public void enter_discontinued_date(String dDate) {
		discontinuedDate.sendKeys(dDate);
	}
	
	public void select_company_from_dropdown(String company){
		Select selectCompany = new Select(companySelectList);
		selectCompany.selectByVisibleText(company);
	}
	
	
	public String get_add_alert_error_message(){
		return computerNameError.getText();
	}
	
}
