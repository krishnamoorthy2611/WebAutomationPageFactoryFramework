/**
 * 
 */
package com.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.baseclass.BaseClass;

/**
 * @author krish
 *
 */
public class OrangeHomePage extends BaseClass{

	@FindBy(xpath = "//*[@id='menu_dashboard_index']")
	WebElement dashboardTab;

	@FindBy(xpath="//*[@id='welcome']")WebElement welcomeLabel;
	
	@FindBy(xpath="//*[@id='welcome-menu']/ul/li[3]/a") WebElement logoutLink;
	
	public OrangeHomePage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	public OrangeHomePage verifyUserLogin() throws IOException{
		
		verifyIfElementDisplayed(dashboardTab);
		logger.log(Status.PASS,"User has logged in to Orange HRM",addImageToExtentReport());
		return new OrangeHomePage(driver);
	}
	
	
	public OrangeLoginPageObjects logOut() throws IOException, InterruptedException{
		click(welcomeLabel);
		verifyIfElementDisplayed(logoutLink);
		hoverOnElement(logoutLink);
		clickWithJS(logoutLink);
		logger.log(Status.PASS,"User Sucessfully logged out",addImageToExtentReport());
		return new OrangeLoginPageObjects(driver);
		
	}
	
}
