/**
 * 
 */
package com.utilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;

/**
 * @author krish
 *
 */
public class BrowserMethods{
	
	
	public static WebDriver driver;
	
	public String getURL(){
		
		return driver.getCurrentUrl();
		
	}

	
	public String browserGetPageSrc() {

		return driver.getPageSource();
	}

	public String browserGetTitle() {

		return driver.getTitle();
	}

	public String browserGetCurrentURL() {

		return driver.getCurrentUrl();

	}

	public void browserClose() {

		driver.close();
	}

	public void browserQuit() {

		driver.quit();
	}

	public void browserSwitchToFrameName(String frameName) {

		driver.switchTo().frame(frameName);
	}

	public void browserSwitchToFrameId(int frameId) {

		driver.switchTo().frame(frameId);

	}

	public void browserSwitchToFrameUsingLocator(WebElement element) {

		driver.switchTo().frame(element);
	}

	public void browserSwitchToParentOrDefaultContent() {
		driver.switchTo().defaultContent();

	}

	public void browserWindowSwitchToChildWindow(String childWindowTitle) {

		String parentWindowHandle = driver.getWindowHandle();
		Set<String> childWindowsHandles = driver.getWindowHandles();
		Iterator<String> iterator = childWindowsHandles.iterator();

		// We will check and iterate through all the other child windows
		while (iterator.hasNext()) {
			String childWindow = iterator.next();
			if (!parentWindowHandle.equalsIgnoreCase(childWindow)) {
				driver.switchTo().window(childWindow);
				if (childWindowTitle.equalsIgnoreCase(driver.getTitle())) {
									}
			}
		}
	}

	public void verifyPageTitle(String expectedPageTitle) {

		Assert.assertEquals(driver.getTitle(), expectedPageTitle);
	}

	public static String takeScreenshotPage() throws IOException {

		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator
				+ dateName + ".png";
		FileUtils.copyFile(source, new File(screenshotPath));
		return screenshotPath;
	}

	
	public void scrollDown() throws InterruptedException{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,400)");
		Thread.sleep(3000);
	}
	
	public void scrollTillFindElement(WebElement element) throws InterruptedException{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(3000);
	}
	
	
	public void browserNavigateOptions(String typeofNavigation) {

		if (typeofNavigation.equalsIgnoreCase("back")) {

			driver.navigate().back();
		} else if (typeofNavigation.equalsIgnoreCase("forward")) {

			driver.navigate().forward();
		}

		else {

			driver.navigate().refresh();
		}
	}

	public void browserNavigateTo(String URLToNavigate) {

		driver.navigate().to(URLToNavigate);
	}

	/*
	 * 
	 */

	public String readProperty(String key) throws IOException {
		Properties properties = new Properties();
		FileReader file = new FileReader(System.getProperty("user.dir") + File.separator + "config.properties");
		properties.load(file);
		return properties.getProperty(key);
	}

	public MediaEntityModelProvider addImageToExtentReport() throws IOException{
		
		return MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshotPage()).build();
	}

	
}
