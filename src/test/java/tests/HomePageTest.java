package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import pageObjects.AddNewComputer;
import pageObjects.HomePage;

public class HomePageTest extends TestBase {

	HomePage homepageobject;
	AddNewComputer addNewComputerObject;

	@Test
	public void validate_header_name() {
		homepageobject = new HomePage(driver);
		goTo(getUrlFromProperty("HOME"));
		assertTrue(homepageobject.get_home_page_heading().contains("computers found"), "Correct Header");
	}

	@Test
	public void navigate_to_add_computer_screen() {
		homepageobject = new HomePage(driver);
		goTo(getUrlFromProperty("HOME"));
		homepageobject.press_add_new_computer_button();
		addNewComputerObject = new AddNewComputer(driver);
		assertEquals(addNewComputerObject.get_add_computer_heading(), "Add a computer");
	}

}
