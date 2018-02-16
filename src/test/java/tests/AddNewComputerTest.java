/**
 * 
 */
package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.AddNewComputer;
import pageObjects.DeleteComputer;
import pageObjects.HomePage;
import utility.Constant;
import utility.ExcelUtils;

public class AddNewComputerTest extends TestBase {
	private int iTestCaseRow;
	HomePage homepageobject;
	AddNewComputer addNewComputerObject;
	DeleteComputer deleteComputerObject;

	@Test(enabled = true)
	public void validate_Header_Name() {
		addNewComputerObject = new AddNewComputer(driver);
		goTo(Constant.ADD_NEW_COMPUTER_URL);
		assertTrue(addNewComputerObject.get_add_computer_heading().contains("Add a computer"), "Correct Header");
	}

	@Test(enabled = true, dataProvider = ("DataForNewComputerOnly"))
	// Add a new computer to the computer database by filling only required
	// fields
	public void add_New_Computer_With_Required_Fields(String computerName, String introducedDate,
			String discontinunedDate, String company) throws Exception {
		addNewComputerObject = new AddNewComputer(driver);
		goTo(Constant.ADD_NEW_COMPUTER_URL);
		addNewComputerObject.enter_computer_name(computerName);
		addNewComputerObject.press_create_computer_button();
		homepageobject = new HomePage(driver);
		assertEquals(homepageobject.get_add_alert_message(), "Done! Computer " + computerName + " has been created");
		homepageobject.retrieve_computer(computerName);
		goTo(homepageobject.get_computer_link(computerName));
		deleteComputerObject = new DeleteComputer(driver);
		deleteComputerObject.press_delete_computer_button();
	}

	@Test(enabled = true)
	// Display error message when adding a new computer to the computer database
	// and not providing a computer name
	public void add_New_Computer_With_No_Inputs() {
		addNewComputerObject = new AddNewComputer(driver);
		goTo(Constant.ADD_NEW_COMPUTER_URL);
		addNewComputerObject.press_create_computer_button();
		assertEquals(addNewComputerObject.get_computername_alert_error_message(), "Computer name\nRequired");
	}

	@Test(enabled = true)
	// Cancel adding a new computer to the computer database
	public void cancel_Add_New_Computer() {
		addNewComputerObject = new AddNewComputer(driver);
		goTo(Constant.ADD_NEW_COMPUTER_URL);
		addNewComputerObject.press_cancel_button();
		homepageobject = new HomePage(driver);
		assertTrue(homepageobject.get_home_page_heading().contains("computers found"));
	}

	@Test(enabled = true, dataProvider = "DataForNewComputerAllFields")
	// Add a new computer to the computer database by filling all fields
	public void add_New_Computer_With_All_Fields(String computerName, String introducedDate, String discontinunedDate,
			String company) throws Exception {
		addNewComputerObject = new AddNewComputer(driver);
		goTo(Constant.ADD_NEW_COMPUTER_URL);
		addNewComputerObject.enter_computer_name(computerName);
		addNewComputerObject.enter_introduced_date(introducedDate);
		addNewComputerObject.enter_discontinued_date(discontinunedDate);
		addNewComputerObject.select_company_from_dropdown(company);
		addNewComputerObject.press_create_computer_button();
		homepageobject = new HomePage(driver);
		assertEquals(homepageobject.get_add_alert_message(), "Done! Computer " + computerName + " has been created");
		homepageobject.retrieve_computer(computerName);
		goTo(homepageobject.get_computer_link(computerName));
		deleteComputerObject = new DeleteComputer(driver);
		deleteComputerObject.press_delete_computer_button();
	}

	@Test(enabled = true, dataProvider = "DataForNewComputerWithInvalidIntroducedDates")
	// Add a new computer to the computer database by filling all fields
	public void add_New_Computer_With_Invalid_Introduced_Dates(String computerName, String introducedDate,
			String discontinunedDate, String company) throws Exception {
		addNewComputerObject = new AddNewComputer(driver);
		goTo(Constant.ADD_NEW_COMPUTER_URL);
		addNewComputerObject.enter_computer_name(computerName);
		addNewComputerObject.enter_introduced_date(introducedDate);
		addNewComputerObject.enter_discontinued_date(discontinunedDate);
		addNewComputerObject.select_company_from_dropdown(company);
		addNewComputerObject.press_create_computer_button();
		assertEquals(addNewComputerObject.get_invalid_introduced_date_error_message(),
				"Introduced date\nDate ('yyyy-MM-dd')");

	}

	@Test(enabled = true, dataProvider = "DataForNewComputerWithInvalidDiscontinuedDates")
	// Add a new computer to the computer database by filling all fields
	public void add_New_Computer_With_Invalid_Discontinued_Dates(String computerName, String introducedDate,
			String discontinunedDate, String company) throws Exception {
		addNewComputerObject = new AddNewComputer(driver);
		goTo(Constant.ADD_NEW_COMPUTER_URL);
		addNewComputerObject.enter_computer_name(computerName);
		addNewComputerObject.enter_introduced_date(introducedDate);
		addNewComputerObject.enter_discontinued_date(discontinunedDate);
		addNewComputerObject.select_company_from_dropdown(company);
		addNewComputerObject.press_create_computer_button();
		assertEquals(addNewComputerObject.get_invalid_discontinued_date_error_message(),
				"Discontinued date\nDate ('yyyy-MM-dd')");

	}

	@DataProvider(name = "DataForNewComputerOnly")
	public Object[][] testDataForComputerNameOnly() throws Exception {
		// Setting up the Test Data Excel file
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Sheet1");
		// Getting the Test Case name to get the TestCase row from the Test Data
		// Excel sheet
		iTestCaseRow = ExcelUtils.getRowContains("add_New_Computer_With_Required_Fields", 0);
		Object[][] testObjArray = ExcelUtils.getTableArray(Constant.Path_TestData + Constant.File_TestData, "Sheet1",
				iTestCaseRow);
		return (testObjArray);

	}

	@DataProvider(name = "DataForNewComputerAllFields")
	public Object[][] testDataFromExcel() throws Exception {
		// Setting up the Test Data Excel file
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Sheet1");
		// Getting the Test Case name to get the TestCase row from the Test Data
		// Excel sheet
		iTestCaseRow = ExcelUtils.getRowContains("add_New_Computer_With_All_Fields", 0);
		Object[][] testObjArray = ExcelUtils.getTableArray(Constant.Path_TestData + Constant.File_TestData, "Sheet1",
				iTestCaseRow);
		return (testObjArray);

	}

	@DataProvider(name = "DataForNewComputerWithInvalidIntroducedDates")
	public Object[][] testDataFromExcelForInvalidIntroDates() throws Exception {
		// Setting up the Test Data Excel file
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Sheet1");
		// Getting the Test Case name to get the TestCase row from the Test Data
		// Excel sheet
		iTestCaseRow = ExcelUtils.getRowContains("add_New_Computer_With_Invalid_Introduced_Date", 0);
		Object[][] testObjArray = ExcelUtils.getTableArray(Constant.Path_TestData + Constant.File_TestData, "Sheet1",
				iTestCaseRow);
		return (testObjArray);

	}

	@DataProvider(name = "DataForNewComputerWithInvalidDiscontinuedDates")
	public Object[][] testDataFromExcelForInvalidDisconDates() throws Exception {
		// Setting up the Test Data Excel file
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Sheet1");
		// Getting the Test Case name to get the TestCase row from the Test Data
		// Excel sheet
		iTestCaseRow = ExcelUtils.getRowContains("add_New_Computer_With_Invalid_Discontinued_Date", 0);
		Object[][] testObjArray = ExcelUtils.getTableArray(Constant.Path_TestData + Constant.File_TestData, "Sheet1",
				iTestCaseRow);
		return (testObjArray);

	}

}
