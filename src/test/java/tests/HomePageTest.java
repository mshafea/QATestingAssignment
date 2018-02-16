package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import pageObjects.AddNewComputer;
import pageObjects.HomePage;
import utility.Constant;

public class HomePageTest extends TestBase {

	HomePage homepageobject;
	AddNewComputer addNewComputerObject;

	@Test(enabled=true)
	public void validate_header_name() {
		homepageobject = new HomePage(driver);
		goTo(Constant.HOME_URL);
		assertTrue(homepageobject.get_home_page_heading().contains("computers found"), "Correct Header");
	}

	@Test(enabled=true)
	public void navigate_to_add_computer_screen() {
		homepageobject = new HomePage(driver);
		goTo(Constant.HOME_URL);
		homepageobject.press_add_new_computer_button();
		addNewComputerObject = new AddNewComputer(driver);
		assertEquals(addNewComputerObject.get_add_computer_heading(), "Add a computer");
	}
	
	@Test(enabled=false)
	public void computer_search() {
		homepageobject = new HomePage(driver);
		goTo(Constant.HOME_URL);
		homepageobject.retrieve_computer("ACE");
		homepageobject.get_computer_link("ACE");
		//addNewComputerObject = new AddNewComputer(driver);
		//assertEquals(addNewComputerObject.get_add_computer_heading(), "Add a computer");
	}

}
