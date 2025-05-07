package stepdefinitions;


import pages.AddUserPage;
import pages.AdminPage;
import pages.HomePage;
import utils.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AdminSteps {



	private WebDriver driver = WebDriverManager.getDriver();
	private HomePage homePage = new HomePage(driver);
	private AdminPage adminPage = new AdminPage(driver);
	private AddUserPage addUserPage = new AddUserPage(driver);

	private int initialRecordCount;

	@When("I click on Admin tab")
	public void iClickOnAdminTab() {
		homePage.clickOnAdminTab();
	}

	@Then("I should be on Admin page")
	public void iShouldBeOnAdminPage() {
		Assert.assertTrue(adminPage.isAdminPageDisplayed());
	}

	@And("I get the initial number of records")
	public void iGetTheInitialNumberOfRecords() {
		initialRecordCount = adminPage.getNumberOfRecords();

	}

	@When("I click on Add button")
	public void iClickOnAddButton() {
		adminPage.clickAddButton();
	}

	@And("I fill the required user data")
	public void iFillTheRequiredUserData() {

		addUserPage.fillUserDetails(ConfigReader.getProperty("UserRole"), ConfigReader.getProperty("Status"), 
				ConfigReader.getProperty("EmployeeName"), ConfigReader.getProperty("Username"), 
				ConfigReader.getProperty("Password"));

	}

	@And("I click on Save button")
	public void iClickOnSaveButton() {
		addUserPage.clickSaveButton();
	}

	@Then("the number of records should increase by 1")
	public void theNumberOfRecordsShouldIncreaseBy1() {
		Assert.assertTrue(adminPage.isAdminPageDisplayed());

		int newRecordCount = adminPage.getNumberOfRecords();
		System.out.println("New record count: " + newRecordCount);
		Assert.assertEquals(newRecordCount, initialRecordCount + 1);
	}

	@And("I should find the newly added user")
	public void iSearchForTheNewlyAddedUser() {
		adminPage.searchForUser(ConfigReader.getProperty("Username"));

		Assert.assertTrue(adminPage.isUserFound(ConfigReader.getProperty("Username")));
	}

	@When("I delete the newly added user")
	public void iDeleteTheNewlyAddedUser() {
		adminPage.deleteUser(ConfigReader.getProperty("Username"));
	}

	@Then("the number of records should decrease by 1")
	public void theNumberOfRecordsShouldDecreaseBy1() {

		adminPage.resetSearch();
		int finalRecordCount = adminPage.getNumberOfRecords();
		System.out.println("Final record count: " + finalRecordCount);
		Assert.assertEquals(finalRecordCount, initialRecordCount);
	} 
}
