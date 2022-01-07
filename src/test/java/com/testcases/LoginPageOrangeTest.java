/**
 * 
 */
package com.testcases;

import org.testng.annotations.Test;

import com.baseclass.BaseClass;
import com.pages.OrangeLoginPageObjects;

/**
 * @author krish
 *
 */
public class LoginPageOrangeTest extends BaseClass{
	
	
	@Test
	public void verifyOrangeHRMlogo() throws Exception{
		new OrangeLoginPageObjects(driver).validateLogo().loginToOrange().verifyUserLogin();
		
		
	}

}
