package com.kms.pom

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class MainPage {
	/**
	 * Login page
	 */
	static loginPage(String btnSubmitName) {
		'Step 1 - Access PIM page'
		WebUI.openBrowser('')
		WebUI.maximizeWindow()
		WebUI.navigateToUrl(GlobalVariable.G_SiteURL)

		'Step 2 - Log in PIM page'
		WebUI.setText(findTestObject('objWebPIM/Main/txt_Login', [('loginInfo') : 'username']), GlobalVariable.G_Username)
		WebUI.setText(findTestObject('objWebPIM/Main/txt_Login', [('loginInfo') : 'password']), GlobalVariable.G_Password)
		WebUI.click(findTestObject('objWebPIM/Main/btn_Submit', [('btnName') : btnSubmitName))
		}

		/**
		 * Login page Successful
		 */
		static loginPageSuccessful(String btnSubmitName='Login') {
			loginPage(btnSubmitName)

			WebUI.verifyElementPresent(findTestObject('objWebPIM/Main/lbl_PageHeader', [('headerName') : GlobalVariable.G_DefaultLabelHeader]), GlobalVariable.G_Timeout)
		}
	}
