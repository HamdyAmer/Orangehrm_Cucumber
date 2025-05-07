package stepdefinitions;


import utils.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class Hooks {


	private WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	Properties prop ;
	FileInputStream fis ;
	String properitesPath;

	@Before
	public void setUp(Scenario scenario) {


		driver = WebDriverManager.getDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {

			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "Failed Screenshot");
		}


		WebDriverManager.quitDriver();
	}}

