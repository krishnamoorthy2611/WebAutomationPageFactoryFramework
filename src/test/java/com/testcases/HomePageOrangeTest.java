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
public class HomePageOrangeTest extends BaseClass{
	
	
	@Test
	public void verifyOrangeHRMHomePage() throws Exception{
		new OrangeLoginPageObjects(driver).loginToOrange().verifyUserLogin().logOut().validateLogo();		
	}

}
