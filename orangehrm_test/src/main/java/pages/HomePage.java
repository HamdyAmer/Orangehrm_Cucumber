package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = ".oxd-userdropdown-tab")
	private WebElement userDropdown;

	@FindBy(xpath = "//span[text()='Admin']")
	private WebElement adminTab;

	@FindBy(xpath = "//span[text()='Dashboard']")
	private WebElement dashboardTab;

	public boolean isLoggedIn() {
		return waitForElementToBeVisible(userDropdown).isDisplayed();
	}

	public void clickOnAdminTab() {
		click(adminTab);
	}

	public boolean isDashboardDisplayed() {
		return waitForElementToBeVisible(dashboardTab).isDisplayed();
	}
}
