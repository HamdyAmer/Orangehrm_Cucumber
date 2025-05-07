package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class AddUserPage extends BasePage {
	public AddUserPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h6[text()='Add User']")
	private WebElement addUserHeader;

	@FindBy(xpath = "//label[text()='User Role']/../..//div[@class='oxd-select-text-input']")
	private WebElement userRoleDropdown;

	@FindBy(xpath = "//label[text()='Status']/../..//div[@class='oxd-select-text-input']")
	private WebElement statusDropdown;

	@FindBy(xpath = "//label[contains(text(), 'Employee Name')]/../..//input")
	private WebElement employeeNameInput;

	@FindBy(xpath = "//label[contains(text(), 'Username')]/../..//input")
	private WebElement usernameInput;

	@FindBy(xpath = "//label[contains(text(), 'Password')]/../..//input")
	private WebElement passwordInput;

	@FindBy(xpath = "//label[contains(text(), 'Confirm Password')]/../..//input")
	private WebElement confirmPasswordInput;

	@FindBy(css = "button[type='submit']")
	private WebElement saveButton;

	public boolean isAddUserPageDisplayed() {
		return waitForElementToBeVisible(addUserHeader).isDisplayed();
	}

	public void selectUserRole(String userRole) {
		click(userRoleDropdown);
		WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@role='listbox']//span[text()='" + userRole + "']")));
		click(option);
	}

	public void selectStatus(String status) {
		click(statusDropdown);
		WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@role='listbox']//span[text()='" + status + "']")));
		click(option);
	}

	public void enterEmployeeName(String employeeName) {
		type(employeeNameInput, employeeName);
		WebElement suggestion = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@role='listbox']//span")));
		click(suggestion);
	}

	public void enterUsername(String username) {
		type(usernameInput, username);
	}

	public void enterPassword(String password) {
		type(passwordInput, password);
	}

	public void enterConfirmPassword(String password) {
		type(confirmPasswordInput, password);
	}

	public void clickSaveButton() {
		click(saveButton);
	}

	public void fillUserDetails(String userRole, String status, String employeeName, String username, String password) {

		selectUserRole(userRole);
		selectStatus(status);
		enterEmployeeName(employeeName);
		enterUsername(username);
		enterPassword(password);
		enterConfirmPassword(password);
	}

}
