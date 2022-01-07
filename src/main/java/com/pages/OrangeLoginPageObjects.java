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
public class OrangeLoginPageObjects extends BaseClass {

	@FindBy(xpath = "//*[@id='divLogo']/img")
	WebElement orangeLogo;

	@FindBy(id = "txtUsername")
	WebElement userName;

	@FindBy(id = "txtPassword")
	WebElement password;

	@FindBy(id = "btnLogin")
	WebElement loginButton;

	public OrangeLoginPageObjects(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

	public OrangeLoginPageObjects validateLogo() throws IOException {
		orangeLogo.isDisplayed();
		verifyIfElementDisplayed(orangeLogo);
		logger.log(Status.PASS, "Orange Logo is getting Displayed", addImageToExtentReport());
		return new OrangeLoginPageObjects(driver);
	}

	public OrangeHomePage loginToOrange() throws Exception {

		enterText(userName,readProperty("username"));
		enterText(password, readProperty("password"));
		click(loginButton);

		return new OrangeHomePage(driver);

	}

}
