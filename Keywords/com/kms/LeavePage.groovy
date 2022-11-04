package com.kms
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException


class LeavePage {

	//	/**
	//	 * Refresh browser
	//	 */
	//	@Keyword
	//	static boolean filterLeaveStatus(String strStatusName) {
	//		boolean isFiltered = false
	//		List<WebElement> lst = WebUiBuiltInKeywords.findWebElements(findTestObject('objWebPIM/Leave/multiselect_LeaveStatusSelectedItems'), GlobalVariable.G_Timeout)
	//		for(WebElement we in lst) {
	//
	//			String selectedItemName = we.getText().trim()
	//			if(selectedItemName != strStatusName) {
	//				WebUiBuiltInKeywords.click(findTestObject('objWebPIM/Leave/ico_Multiselect_SelectedItem', [('selectedItemName') : selectedItemName]))
	//			}else {
	//				isFiltered = true
	//			}
	//		}
	//		return isFiltered
	//	}

	/**
	 * verifyMyLeave
	 */
	@Keyword
	static boolean verifyMyLeave(String strExpectedStatus) {
		List<WebElement> lst = WebUiBuiltInKeywords.findWebElements(findTestObject('Object Repository/objWebPIM/Main/tbl_FilteredRows'), GlobalVariable.G_Timeout)

		for (int i = 1; i < lst.size(); i++) {
			String strStatus = WebUiBuiltInKeywords.getText(findTestObject('objWebPIM/Main/tbl_FilteredCell', [('rowNumber') : i, ('cellNumber') : 7]))
			if(strStatus == strExpectedStatus) {
				//cancel my leave
				WebUiBuiltInKeywords.click(findTestObject('objWebPIM/Main/tbl_FilteredCellButton', [('rowNumber') : i, ('cellNumber') : 9]))

				String strMessage = 'Successfully Updated'
				WebUiBuiltInKeywords.verifyElementText(findTestObject('objWebPIM/Leave/toast_Message'), strMessage)

				strStatus = WebUiBuiltInKeywords.getText(findTestObject('objWebPIM/Main/tbl_FilteredCell', [('rowNumber') : i, ('cellNumber') : 7]))
				strStatus.println()
				strExpectedStatus.replaceAll('Pending Approval', 'Cancelled')
				strExpectedStatus.println()
				return true
				//				if(strStatus == strExpectedStatus) {
				//					return true
				//				}
			}
		}

		//			boolean isPresent = WebUiBuiltInKeywords.verifyElementPresent(findTestObject('objWebPIM/Main/tbl_FilteredCellButton', [('rowNumber') : i, ('cellNumber') : 9]), GlobalVariable.G_Timeout)
		//			if(isPresent == true) {
		//				String strStatus = WebUiBuiltInKeywords.getText(findTestObject('objWebPIM/Main/tbl_FilteredCell', [('rowNumber') : i, ('cellNumber') : 7]))
		//				if(strStatus == strExpectedStatus) {
		//					WebUiBuiltInKeywords.click(findTestObject('objWebPIM/Main/tbl_FilteredCellButton', [('rowNumber') : i, ('cellNumber') : 9]))
		//
		//					String strMessage = 'Successfully Updated'
		//					WebUiBuiltInKeywords.verifyElementText(findTestObject('objWebPIM/Leave/toast_Message'), strMessage)
		//
		//					strStatus = WebUiBuiltInKeywords.getText(findTestObject('objWebPIM/Main/tbl_FilteredCell', [('rowNumber') : i, ('cellNumber') : 7]))
		//					strExpectedStatus.replaceAll('Pending Approval', 'Cancelled')
		//					if(strStatus == strExpectedStatus) {
		//						return true
		//					}
		//				}
		//			}
	}


	/**
	 * Refresh browser
	 */
	@Keyword
	def refreshBrowser() {
		KeywordUtil.logInfo("Refreshing")
		WebDriver webDriver = DriverFactory.getWebDriver()
		webDriver.navigate().refresh()
		KeywordUtil.markPassed("Refresh successfully")
	}

	/**
	 * Click element
	 * @param to Katalon test object
	 */
	@Keyword
	def clickElement(TestObject to) {
		try {
			WebElement element = WebUiBuiltInKeywords.findWebElement(to);
			KeywordUtil.logInfo("Clicking element")
			element.click()
			KeywordUtil.markPassed("Element has been clicked")
		} catch (WebElementNotFoundException e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("Fail to click on element")
		}
	}

	/**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */
	@Keyword
	def List<WebElement> getHtmlTableRows(TestObject table, String outerTagName) {
		WebElement mailList = WebUiBuiltInKeywords.findWebElement(table)
		List<WebElement> selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
		return selectedRows
	}
}