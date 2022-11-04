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

public class WebElement {
	static boolean verifyElementPresent(TestObject obj, String varName, String varValue) {
		if(WebUI.verifyElementPresent(findTestObject(obj, [(varName) : varValue]), GlobalVariable.G_Timeout)) {
			return true
		}
	}

	static boolean verifyElementText(TestObject obj, String varName, String varValue, String txtVerify) {
		return WebUI.verifyElementText(findTestObject(obj, [(varName) : varValue]), txtVerify)
	}

	static boolean verifyElementText(TestObject obj, String varName, String varValue, String varName2, String varValue2, String txtVerify) {
		return WebUI.verifyElementText(findTestObject(obj, [(varName) : varValue, (varName2) : varValue2]), txtVerify)
	}

	static clickWebElement(TestObject obj, String varName, String varValue) {
		return WebUI.click(findTestObject(obj, [(varName) : varValue]))
	}

	static clickWebElement(TestObject obj, String varName, String varValue, String varName2, String varValue2) {
		return WebUI.click(findTestObject(obj, [(varName) : varValue, (varName2) : varValue2]))
	}
}
