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
import com.kms.katalon.core.testdata.CSVData

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

class EmployeePage {
	/**
	 * accessSubPageMenuItem
	 */
	@Keyword
	static accessSubPageMenuItem(String subPageMenuItemName, String subPageSubMenuItemName) {
		WebUiBuiltInKeywords.click(findTestObject('objWebPIM/Main/mnu_Horizon_ConfigIcon'))
		WebUiBuiltInKeywords.click(findTestObject('objWebPIM/Main/mnu_HorizonItem_ConfigDropdownItem', [('dropdownMenuItem') : subPageSubMenuItemName]))
	}

	/**
	 * accessSubPageMenuItemSuccessful
	 */
	@Keyword
	static accessSubPageMenuItemSuccessful(String subPageMenuItemName, String subPageSubMenuItemName) {
		accessSubPageMenuItem(subPageMenuItemName, subPageSubMenuItemName)
		WebUiBuiltInKeywords.verifyElementPresent(findTestObject('objWebPIM/Main/lbl_SubPageMenuItemHeader', [('headerName') : subPageSubMenuItemName]), GlobalVariable.G_Timeout)
	}

	/**
	 * insertToCSVFile
	 */
	@Keyword
	static String[] insertToCSVFile(File csvFile) {
		Date todaysDate = new Date();
		String format = 'yyyy-MM-dd'
		def formattedDate = todaysDate.format(format)
		String firstName = 'Tam ' + formattedDate + todaysDate.getTime()
		String middleName = 'Minh'
		String lastName = 'Huynh'
		
		String[] fullName = [firstName, middleName, lastName]

		//File csvFile = new File(GlobalVariable.G_Path)
		String record = "'${firstName}', '${middleName}', '${lastName}','','','','','','','','','','','','','','','','','','',''"
		csvFile.append("\n")
		csvFile.append(record)

		return fullName
	}

	/**
	 * insertToCSVFile
	 */
	@Keyword
	static boolean browseFile() {
		//	  WebElement we = WebUiBuiltInKeywords.findWebElement(findTestObject('objWebPIM/Employee/btn_Browse'))
		//	  we.click()
		WebElement we2 = WebUiBuiltInKeywords.findWebElement(findTestObject('Object Repository/objWebPIM/Employee/input_File'))
		we2.sendKeys(GlobalVariable.G_Path)

		WebUiBuiltInKeywords.verifyElementText(findTestObject('objWebPIM/Employee/lbl_SelectedBrowseFile'), GlobalVariable.G_FileName)
	}

	/**
	 * verifyNewEmployee
	 */
	@Keyword
	static String verifyNewEmployee (String[] strFullName) {
		List<WebElement> lst = WebUiBuiltInKeywords.findWebElements(findTestObject('Object Repository/objWebPIM/Main/tbl_FilteredRows'), GlobalVariable.G_Timeout)
		String strFirstMiddle = "'" + strFullName[0] + "'  '" + strFullName[1] + "'"
		String strLast = strFullName[2]
		boolean isPresent = false
		String strFirstMiddleName = ''
		String strLastName = ''

		if(lst.size() == 1) {
			strFirstMiddleName = WebUiBuiltInKeywords.getText(findTestObject('objWebPIM/Main/tbl_FilteredCell', [('rowNumber') : 1, ('cellNumber') : 3]))
			strLastName = WebUiBuiltInKeywords.getText(findTestObject('objWebPIM/Main/tbl_FilteredCell', [('rowNumber') : 1, ('cellNumber') : 4]))
			if(strFirstMiddleName == strFirstMiddle) {
				strLastName.replaceAll("'", "")
				if(strLastName == strLast) {
					isPresent = true
				}
			}
		}
		return isPresent
		//return strLast + ' - ' + strLastName.replaceAll("'", "")
	}
		
		/**
		 * verifyNewEmployee
		 */
		@Keyword
		static boolean deleteNewEmployee (String[] strFullName) {
			boolean isPresent = verifyNewEmployee(strFullName)
			boolean isDeleted = false
			if(isPresent) {
				WebUiBuiltInKeywords.click(findTestObject('objWebPIM/Main/tbl_FilteredCellButton', [('rowNumber') : 1, ('cellNumber') : 9]))
				
				WebUiBuiltInKeywords.click(findTestObject('Object Repository/objWebPIM/Main/btn_Delete'))
				
				String strMessage = 'Successfully Deleted'
				WebUiBuiltInKeywords.verifyElementText(findTestObject('objWebPIM/Leave/toast_Message'), strMessage)
				isDeleted = true
			}
			return isDeleted
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