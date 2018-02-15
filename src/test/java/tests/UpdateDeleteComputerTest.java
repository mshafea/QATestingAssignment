package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.UpdateAndDeleteComputer;

public class UpdateDeleteComputerTest extends TestBase {

	HomePage homepageobject;
	UpdateAndDeleteComputer updateDeleteComputerObject;
	
	@Test(priority=0, enabled=true)
	public void validate_Header_Name(){
		homepageobject = new HomePage(driver);
		goTo(getUrlFromProperty("HOME"));
		homepageobject.filter_by_name("MComputer");
		homepageobject.press_filter_by_name_button();
		homepageobject.click_on_computer("MComputer");
		updateDeleteComputerObject = new UpdateAndDeleteComputer(driver);
		assertTrue(updateDeleteComputerObject.get_update_computer_heading().contains("Edit computer"), "Correct Header");
	}
	
	@Test(priority=1, enabled=true)
	//Update a computer name in the computer database
	public void update_Computer_Name(){
		homepageobject = new HomePage(driver);
		goTo(getUrlFromProperty("HOME"));
		homepageobject.filter_by_name("MComputer");
		homepageobject.press_filter_by_name_button();
		homepageobject.click_on_computer("MComputer");
		updateDeleteComputerObject = new UpdateAndDeleteComputer(driver);
		updateDeleteComputerObject.clear_computer_name();
		updateDeleteComputerObject.update_computer_name("MTestComputer");
		updateDeleteComputerObject.press_save_computer_button();
		assertEquals(homepageobject.get_update_alert_message(), "Done! Computer MTestComputer has been updated");
	}
	
	@Test(priority=2, enabled=true)
	//Display error message when updating a computer name in the computer database and not providing a computer name
	public void update_Computer_Name_With_No_Value(){
		homepageobject = new HomePage(driver);
		goTo(getUrlFromProperty("HOME"));
		homepageobject.filter_by_name("MComputer");
		homepageobject.press_filter_by_name_button();
		homepageobject.click_on_computer("MComputer");
		updateDeleteComputerObject = new UpdateAndDeleteComputer(driver);
		updateDeleteComputerObject.clear_computer_name();
		updateDeleteComputerObject.press_save_computer_button();
		assertEquals(updateDeleteComputerObject.get_update_alert_error_message(), "Computer name\nRequired");
	}
	
	@Test(priority=3, enabled=true)
	//Cancel updating a computer name
	public void cancel_Update_Computer(){
		homepageobject = new HomePage(driver);
		goTo(getUrlFromProperty("HOME"));
		homepageobject.filter_by_name("MComputer");
		homepageobject.press_filter_by_name_button();
		homepageobject.click_on_computer("MComputer");
		updateDeleteComputerObject = new UpdateAndDeleteComputer(driver);
		updateDeleteComputerObject.press_cancel_button();
		assertTrue(homepageobject.get_home_page_heading().contains("computers found"));
	}
	
	@Test(priority=4, enabled=true)
	//Update a computer in the computer database by filling all field with valid inputs
	public void update_Computer_With_All_Fields(){
		homepageobject = new HomePage(driver);
		goTo(getUrlFromProperty("HOME"));
		homepageobject.filter_by_name("MComputer");
		homepageobject.press_filter_by_name_button();
		homepageobject.click_on_computer("MComputerAll");
		updateDeleteComputerObject = new UpdateAndDeleteComputer(driver);
		updateDeleteComputerObject.clear_computer_name();
		updateDeleteComputerObject.update_computer_name("MTestAll");
		updateDeleteComputerObject.clear_introduced_date();
		updateDeleteComputerObject.update_introduced_date("2016-03-16");
		updateDeleteComputerObject.update_discontinued_date("2018-01-16");
		updateDeleteComputerObject.select_company_from_dropdown("RCA");
		updateDeleteComputerObject.press_save_computer_button();
		assertEquals(homepageobject.get_update_alert_message(), "Done! Computer MTestAll has been updated");
	}
	
	@Test(priority=5, enabled=true)
	//Delete a computer in the computer database
	public void delete_Computer(){
		homepageobject = new HomePage(driver);
		goTo(getUrlFromProperty("HOME"));
		homepageobject.filter_by_name("MComputer");
		homepageobject.press_filter_by_name_button();
		homepageobject.click_on_computer("MTestComputer");
		updateDeleteComputerObject = new UpdateAndDeleteComputer(driver);
		updateDeleteComputerObject.press_delete_computer_button();
		assertEquals(homepageobject.get_delete_alert_message(), "Done! Computer has been deleted");
	}
}
