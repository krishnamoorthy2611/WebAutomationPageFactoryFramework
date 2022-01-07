/**
 * 
 */
package com.baseclass;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.utilities.Constants;
import com.utilities.ElementMethods;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author krish
 *
 */
public class BaseClass extends ElementMethods{

	
	public ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static String reportPath;

	@BeforeTest
	public void setupTest() throws IOException {
		
		reportPath = pathToAutomationReports();
		htmlReporter = new ExtentHtmlReporter(reportPath);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Automation Test Results");
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("OS", "Win 10");
						
	}
	
	@BeforeMethod
	@Parameters(value = { "browserName" })
	public void beforeMethod(String browserName, Method testMethod) {
		logger = extent.createTest(testMethod.getName());
		selectBrowser(browserName);
		driver.manage().window().maximize();
		driver.get(Constants.URL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//new ElementMethods(driver);
	}

	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			String methodName = result.getMethod().getMethodName();
			String logText = "Test Case: " + methodName + " Passed ";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			logger.log(Status.PASS, m);

		} else if (result.getStatus() == ITestResult.FAILURE) {
			String methodName = result.getMethod().getMethodName();
			String logText = "Test Case: " + methodName + " Failed ";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
			logger.log(Status.FAIL, m);

		} else if (result.getStatus() == ITestResult.SKIP) {
			String methodName = result.getMethod().getMethodName();
			String logText = "Test Case: " + methodName + " Skipped ";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.AMBER);
			logger.log(Status.SKIP, m);

		}

		driver.quit();

	}



	@AfterTest
	@Parameters(value = { "browserName" })
	public void afterTest(String browserName) {
		selectBrowser(browserName);
		driver.manage().window().maximize();
		driver.get(reportPath);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		extent.flush();

	}


public void selectBrowser(String browserName) {
		
		if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			extent.setSystemInfo("Browser", "MS Edge");

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			extent.setSystemInfo("Browser", "Firefox");
		} else {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			extent.setSystemInfo("Browser", "Chrome");

		}

	}

	public static String pathToAutomationReports() throws IOException {

		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		String screenshotPath = System.getProperty("user.dir") + File.separator + "reports" + File.separator
				+ "AutomationReports" + dateName + ".html";
		return screenshotPath;
	}

	
}
