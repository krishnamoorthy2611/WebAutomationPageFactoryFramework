/**
 * 
 */
package com.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * @author krish
 *
 */
public class RetryAnalyser implements IRetryAnalyzer {

	int count = 0;
	int retryCount = 1;

	public boolean retry(ITestResult iTestResult) {
		// TODO Auto-generated method stub
		while(count<retryCount){
			
			count++;
			return true;
		}
		
		return false;
	}

}
