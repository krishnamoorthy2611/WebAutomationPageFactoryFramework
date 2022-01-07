/**
 * 
 */
package com.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import com.baseclass.BaseClass;

/**
 * @author krish
 *
 */
public class SuiteListener implements ITestListener, IAnnotationTransformer{

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void onTestFailure(ITestResult iTestResult) {
		// TODO Auto-generated method stub
	String fileName = System.getProperty("user.dir")+ File.separator + "screenshots"+File.separator+iTestResult.getMethod().getMethodName();
	File screenshot = ((TakesScreenshot)BaseClass.driver).getScreenshotAs(OutputType.FILE);
	try {
		FileUtils.copyFile(screenshot, new File(fileName +".png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method) {
		// TODO Auto-generated method stub
		
		iTestAnnotation.setRetryAnalyzer(RetryAnalyser.class);
	}

	
}
