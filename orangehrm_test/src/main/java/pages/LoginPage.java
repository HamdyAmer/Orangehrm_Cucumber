package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@placeholder='Username']")
	private WebElement usernameInput;

	@FindBy(xpath = "//input[@placeholder='Password']")
	private WebElement passwordInput;

	@FindBy(css = "button[type='submit']")
	private WebElement loginButton;

	@FindBy(xpath = "//div[@class='orangehrm-login-branding']")
	private WebElement orangeHrmLogo;

	public void navigateToLoginPage(String url) {
		driver.get(url);
		waitForElementToBeVisible(orangeHrmLogo);
	}

	public void enterUsername(String username) {
		type(usernameInput, username);
	}

	public void enterPassword(String password) {
		type(passwordInput, password);
	}

	public void clickLoginButton() {
		click(loginButton);
	}

	public boolean isLoginPageDisplayed() {
		return usernameInput.isDisplayed() && passwordInput.isDisplayed();
	}

	public void login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLoginButton();
	}
}
