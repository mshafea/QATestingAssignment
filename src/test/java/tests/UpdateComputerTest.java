package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AddNewComputer;
import pageObjects.DeleteComputer;
import pageObjects.HomePage;
import pageObjects.UpdateComputer;
import utility.Constant;
import utility.ExcelUtils;

public class UpdateComputerTest extends TestBase {
	private int iTestCaseRow;
	HomePage homepageobject;
	UpdateComputer updateComputerObject;
	DeleteComputer deleteComputerObject;
	AddNewComputer addNewComputerObject;

	@Test(dataProvider = "DataForNewComputerAllFields")
	public void add_New_Computer_With_All_Fields(String computerName, String introducedDate, String discontinunedDate,
			String company) {
		addNewComputerObject = new AddNewComputer(driver);
		goTo(Constant.ADD_NEW_COMPUTER_URL);
		addNewComputerObject.enter_computer_name(computerName);
		addNewComputerObject.enter_introduced_date(introducedDate);
		addNewComputerObject.enter_discontinued_date(discontinunedDate);
		addNewComputerObject.press_create_computer_button();
	}

	@Test(priority = 0, enabled = true)
	public void validate_Header_Name() throws Exception {
		String computer = ExcelUtils.getCellData(2,1);
		homepageobject = new HomePage(driver);
		goTo(Constant.HOME_URL);
		homepageobject.retrieve_computer(computer);
		goTo(homepageobject.get_computer_link(computer));
		updateComputerObject = new UpdateComputer(driver);
		assertTrue(updateComputerObject.get_update_computer_heading().contains("Edit computer"), "Correct Header");
	}


	@Test(priority = 1, enabled = true, dependsOnMethods = "add_New_Computer_With_All_Fields")
	// Display error message when updating a computer name in the computer
	// database and not providing a computer name
	public void update_Computer_Name_With_No_Value() throws Exception {
		String computer = ExcelUtils.getCellData(2,1);
		homepageobject = new HomePage(driver);
		goTo(Constant.HOME_URL);
		homepageobject.retrieve_computer(computer);
		goTo(homepageobject.get_computer_link(computer));
		updateComputerObject = new UpdateComputer(driver);
		updateComputerObject.clear_computer_name();
		updateComputerObject.press_save_computer_button();
		assertEquals(updateComputerObject.get_update_alert_error_message(), "Computer name\nRequired");
	}

	@Test(priority = 2, enabled = true, dependsOnMethods = "add_New_Computer_With_All_Fields")
	// Cancel updating a computer name
	public void cancel_Update_Computer() throws Exception {
		String computer = ExcelUtils.getCellData(2,1);
		homepageobject = new HomePage(driver);
		goTo(Constant.HOME_URL);
		homepageobject.retrieve_computer(computer);
		goTo(homepageobject.get_computer_link(computer));
		updateComputerObject = new UpdateComputer(driver);
		updateComputerObject.press_cancel_button();
		assertTrue(homepageobject.get_home_page_heading().contains("computers found"));
	}

	@Test(priority = 3, dataProvider = "DataForUpdateAllFields", dependsOnMethods = "add_New_Computer_With_All_Fields")
	// Update a computer in the computer database by filling all field with
	// valid inputs
	public void update_Computer_With_All_Fields(String computerName,String introducedDate, String discontinunedDate, String company) throws Exception {
		homepageobject = new HomePage(driver);
		goTo(Constant.HOME_URL);
		String computer = ExcelUtils.getCellData(2,1);
		homepageobject.retrieve_computer(computer);
		goTo(homepageobject.get_computer_link(computer));
		updateComputerObject = new UpdateComputer(driver);
		updateComputerObject.clear_computer_name();
		updateComputerObject.update_computer_name(computerName);
		updateComputerObject.clear_introduced_date();
		updateComputerObject.update_introduced_date(introducedDate);
		updateComputerObject.clear_discontinued_date();
		updateComputerObject.update_discontinued_date(discontinunedDate);
		updateComputerObject.select_company_from_dropdown(company);
		updateComputerObject.press_save_computer_button();
		assertEquals(homepageobject.get_update_alert_message(), "Done! Computer " +  computerName +" has been updated");
		homepageobject.retrieve_computer(computerName);
		goTo(homepageobject.get_computer_link(computerName));
		deleteComputerObject = new DeleteComputer(driver);
		deleteComputerObject.press_delete_computer_button();
	}

	@DataProvider(name = "DataForNewComputerAllFields")
	public Object[][] testDataFromExcelForUpdate() throws Exception {
		// Setting up the Test Data Excel file
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Sheet1");
		// Getting the Test Case name to get the TestCase row from the Test Data
		// Excel sheet
		iTestCaseRow = ExcelUtils.getRowContains("add_New_Computer_With_All_Fields", 0);
		Object[][] testObjArray = ExcelUtils.getTableArray(Constant.Path_TestData + Constant.File_TestData, "Sheet1",
				iTestCaseRow);
		return (testObjArray);
	}
	
	@DataProvider(name = "DataForUpdateAllFields")
	public Object[][] testDataFromExcel() throws Exception {
		// Setting up the Test Data Excel file
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Sheet1");
		// Getting the Test Case name to get the TestCase row from the Test Data
		// Excel sheet
		iTestCaseRow = ExcelUtils.getRowContains("Update_All_Fields", 0);
		Object[][] testObjArray = ExcelUtils.getTableArray(Constant.Path_TestData + Constant.File_TestData, "Sheet1",
				iTestCaseRow);
		return (testObjArray);
	}
	
}
