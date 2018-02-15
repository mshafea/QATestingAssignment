/**
 * 
 */
package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

import pageObjects.AddNewComputer;
import pageObjects.HomePage;

public class AddNewComputerTest extends TestBase {
	HomePage homepageobject;
	AddNewComputer addNewComputerObject;

	@Test
	public void validate_header_name() {
		addNewComputerObject = new AddNewComputer(driver);
		goTo(getUrlFromProperty("ADD_COMPUTER"));
		assertTrue(addNewComputerObject.get_add_computer_heading().contains("Add a computer"), "Correct Header");
	}

	@Test
	// Add a new computer to the computer database by filling only required fields
	public void add_New_Computer_With_Required_Fields() {
		addNewComputerObject = new AddNewComputer(driver);
		goTo(getUrlFromProperty("ADD_COMPUTER"));
		addNewComputerObject.enter_computer_name("MComputer");
		addNewComputerObject.press_create_computer_button();
		homepageobject = new HomePage(driver);
		assertEquals(homepageobject.get_add_alert_message(), "Done! Computer MComputer has been created");
	}

	@Test
	// Display error message when adding a new computer to the computer database
	// and not providing a computer name
	public void add_New_Computer_With_No_Inputs() {
		addNewComputerObject = new AddNewComputer(driver);
		goTo(getUrlFromProperty("ADD_COMPUTER"));
		addNewComputerObject.press_create_computer_button();
		assertEquals(addNewComputerObject.get_add_alert_error_message(), "Computer name\nRequired");
	}

	@Test
	// Cancel adding a new computer to the computer database
	public void cancel_Add_New_Computer() {
		addNewComputerObject = new AddNewComputer(driver);
		goTo(getUrlFromProperty("ADD_COMPUTER"));
		addNewComputerObject.press_cancel_button();
		homepageobject = new HomePage(driver);
		assertTrue(homepageobject.get_home_page_heading().contains("computers found"));
	}

	@Test
	// Add a new computer to the computer database by filling all fields
	public void add_New_Computer_With_All_Fields() {
		addNewComputerObject = new AddNewComputer(driver);
		goTo(getUrlFromProperty("ADD_COMPUTER"));
		addNewComputerObject.enter_computer_name("MComputerTestAll");
		addNewComputerObject.enter_introduced_date("2018-02-16");
		addNewComputerObject.enter_discontinued_date("2018-01-20");
		addNewComputerObject.select_company_from_dropdown("IBM");
		addNewComputerObject.press_create_computer_button();
		homepageobject = new HomePage(driver);
		assertEquals(homepageobject.get_add_alert_message(), "Done! Computer MComputerTestAll has been created");
	}
}
