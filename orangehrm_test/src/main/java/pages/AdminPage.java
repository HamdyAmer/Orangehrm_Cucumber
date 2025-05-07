package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AdminPage extends BasePage {

	public AdminPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h6[normalize-space()='Admin']")
	private WebElement adminHeader;

	@FindBy(xpath = "//button[normalize-space()='Add']")
	private WebElement addButton;

	@FindBy(xpath = "//li[@class='oxd-topbar-body-nav-tab --parent --visited']")
	private WebElement userManagementTitle;

	@FindBy(xpath = "//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']")
	private WebElement recordsFoundText;

	@FindBy(xpath = "//label[contains(text(), 'Username')]/../..//input")
	private WebElement usernameSearchField;

	@FindBy(css = ".oxd-form-actions button[type='submit']")
	private WebElement searchButton;

	@FindBy(css = ".oxd-table-card")
	private List<WebElement> userRecords;

	@FindBy(xpath = "//button[normalize-space()='Reset']")
	private WebElement resetButton;

	private  String deleteButtonXpath = "//div[contains(text(), '%s')]/../..//i[contains(@class, 'bi-trash')]";
	private  String confirmDeleteButtonXpath = "//div[@role='document']//button[contains(@class, 'oxd-button--label-danger')]";

	public boolean isAdminPageDisplayed() {
		return waitForElementToBeVisible(adminHeader).isDisplayed() &&
				getText(adminHeader).contains("Admin");
	}

	public void clickAddButton() {
		click(addButton);
	}

	public int getNumberOfRecords() {
		String recordsText = getText(recordsFoundText);

		return Integer.parseInt(recordsText.replaceAll("[^0-9]", ""));
	}

	public void searchForUser(String username) {
		type(usernameSearchField, username);
		click(searchButton);

		wait.until(ExpectedConditions.visibilityOf(recordsFoundText));
	}

	public boolean isUserFound(String username) {
		if (userRecords.isEmpty()) {
			return false;
		}


		wait.until(ExpectedConditions.visibilityOfAllElements(userRecords));

		for (WebElement record : userRecords) {
			String recordText = record.getText();
			if (recordText.contains(username)) {
				return true;
			}
		}
		return false;
	}

	public void deleteUser(String username) {

		WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath(String.format(deleteButtonXpath, username))));
		click(deleteButton);


		WebElement confirmDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath(confirmDeleteButtonXpath)));
		click(confirmDeleteButton);

		wait.until(ExpectedConditions.stalenessOf(deleteButton));
	}

	public void resetSearch() {
		click(resetButton);
		wait.until(ExpectedConditions.visibilityOf(recordsFoundText));
	}
}
