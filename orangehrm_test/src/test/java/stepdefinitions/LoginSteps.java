package stepdefinitions;


import pages.HomePage;
import pages.LoginPage;
import utils.WebDriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.*;
import java.io.IOException;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginSteps {




	private WebDriver driver = WebDriverManager.getDriver();
	private LoginPage loginPage = new LoginPage(driver);
	private HomePage homePage = new HomePage(driver);


	@Given("I am on OrangeHRM login page")
	public void iAmOnOrangeHRMLoginPage() throws IOException {

		String baseUrl =  ConfigReader.getProperty("URL");
		loginPage.navigateToLoginPage(baseUrl);

		Assert.assertTrue(loginPage.isLoginPageDisplayed());
	}

	@When("I enter username as {string}")
	public void iEnterUsernameAs(String username) {
		loginPage.enterUsername(username);
	}

	@And("I enter password as {string}")
	public void iEnterPasswordAs(String password) {
		loginPage.enterPassword(password);
	}

	@And("I click on login button")
	public void iClickOnLoginButton() {
		loginPage.clickLoginButton();
	}

	@Then("I should be logged in successfully")
	public void iShouldBeLoggedInSuccessfully() {
		Assert.assertTrue(homePage.isLoggedIn());
		Assert.assertTrue(homePage.isDashboardDisplayed());
	}
}
