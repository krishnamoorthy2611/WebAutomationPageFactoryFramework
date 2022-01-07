/**
 * 
 */
package com.utilities;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * @author krish
 *
 */
public class ElementMethods extends BrowserMethods {
	WebDriverWait wait;
	long explicitWait = 30;
	Actions action;
	

	public void enterText(WebElement element, String textToEnter) {
		wait = new WebDriverWait(driver, explicitWait);
		wait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(textToEnter);
	}

	public void click(WebElement element) {

		wait = new WebDriverWait(driver, explicitWait);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public void verifyIfElementDisplayed(WebElement element) {
		wait = new WebDriverWait(driver, explicitWait);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void verifyIfElementIsNotDisplayed(WebElement element) {
		wait = new WebDriverWait(driver, explicitWait);
		wait.until(ExpectedConditions.invisibilityOf(element));

	}

	
	
	public boolean isSelected(WebElement element) {

		return element.isSelected();

	}

	public void clickSubmitButtom(WebElement element) {
		wait = new WebDriverWait(driver, explicitWait);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.submit();

	}

	public Point getLocation(WebElement element) {
		wait = new WebDriverWait(driver, explicitWait);
		wait.until(ExpectedConditions.visibilityOf(element));
		Point point = element.getLocation();
		return point;
	}

	public void clickAndEnterText(WebElement element, String textToEnter) {
		wait = new WebDriverWait(driver, explicitWait);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		wait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(textToEnter);
	}

	public void clearAndEnterText(WebElement element, String textToEnter) {
		wait = new WebDriverWait(driver, explicitWait);
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
		wait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(textToEnter);
	}

	public String getText(WebElement element) {

		wait = new WebDriverWait(driver, explicitWait);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}

	public Dimension getAttribute(WebElement element) {
		wait = new WebDriverWait(driver, explicitWait);
		wait.until(ExpectedConditions.visibilityOf(element));
		Dimension dimension = element.getSize();
		return dimension;
	}

	public void verifyText(WebElement element, String expectedText) {

		Assert.assertEquals(element.getText(), expectedText);
	}

	public void hoverOnElement(WebElement element) {

		action = new Actions(driver);
		action.moveToElement(element).build().perform();

	}

	public void doubleClickOnElement(WebElement element) {
		action = new Actions(driver);
		action.doubleClick(element).build().perform();

	}

	public void rightClickOnElement(WebElement element) {
		action = new Actions(driver);
		action.contextClick(element).build().perform();

	}

	public void clickAndHoldOnElement(WebElement element) {
		action = new Actions(driver);
		action.clickAndHold(element).build().perform();

	}

	public void dragAndDropElement(WebElement source, WebElement target) {
		action = new Actions(driver);
		action.dragAndDrop(source, target).build().perform();

	}

	public void clickWithAction(WebElement element) {

		action = new Actions(driver);
		action.click(element).build().perform();
	}

	public void clickWithJS(WebElement element){
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
}
	public void selectByIndex(WebElement element, int index){
		Select select = new Select(element);
		select.selectByIndex(index);
		
	}
	public void selectByValue(WebElement element, String value){
		Select select = new Select(element);
		select.selectByValue(value);
		
	}
	
	public void selectByVisibleText(WebElement element, String value){
		Select select = new Select(element);
		select.selectByVisibleText(value);
		
	}
	
}