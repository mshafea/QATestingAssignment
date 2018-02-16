package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.AddNewComputer;
import pageObjects.DeleteComputer;
import pageObjects.HomePage;
import utility.Constant;
import utility.ExcelUtils;

public class DeleteComputerTest extends TestBase {
	private int iTestCaseRow;
	DeleteComputer deleteComputerObject;
	HomePage homePageObject;
	AddNewComputer addNewComputerObject;

	@Test(dataProvider = "DataForNewComputerAllFields")
	public void add_New_Computer_With_All_Fields(String computerName, String introducedDate, String discontinunedDate,
			String company) {
		addNewComputerObject = new AddNewComputer(driver);
		goTo(Constant.ADD_NEW_COMPUTER_URL);
		addNewComputerObject.enter_computer_name(computerName);
		addNewComputerObject.enter_introduced_date(introducedDate);
		addNewComputerObject.enter_discontinued_date(discontinunedDate);
		addNewComputerObject.select_company_from_dropdown(company);
		addNewComputerObject.press_create_computer_button();
	}

	@Test(enabled = true, dependsOnMethods = "add_New_Computer_With_All_Fields")
	// Delete a computer in the computer database
	public void delete_Computer() throws Exception {
		String computerName = ExcelUtils.getCellData(3, 1);
		homePageObject = new HomePage(driver);
		goTo(Constant.HOME_URL);
		homePageObject.retrieve_computer(computerName);
		goTo(homePageObject.get_computer_link(computerName));
		deleteComputerObject = new DeleteComputer(driver);
		deleteComputerObject.press_delete_computer_button();
		assertEquals(homePageObject.get_delete_alert_message(), "Done! Computer has been deleted");
	}

	@DataProvider(name = "DataForNewComputerAllFields")
	public Object[][] testDataFromExcel() throws Exception {
		// Setting up the Test Data Excel file
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Sheet1");
		// Getting the Test Case name to get the TestCase row from the Test Data
		// Excel sheet
		iTestCaseRow = ExcelUtils.getRowContains("Delete_Computer", 0);
		Object[][] testObjArray = ExcelUtils.getTableArray(Constant.Path_TestData + Constant.File_TestData, "Sheet1",
				iTestCaseRow);
		return (testObjArray);
	}
}
