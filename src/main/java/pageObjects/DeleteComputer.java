package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteComputer {

	WebDriver driver;

	@FindBy(css = "input[value='Delete this computer']")
	private WebElement deleteComputerButton;

	public DeleteComputer(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void press_delete_computer_button() {
		deleteComputerButton.click();
	}

}
