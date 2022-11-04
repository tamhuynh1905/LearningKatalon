import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.MainPage
//import com.kms.pom.MainPage
//import com.kms.pom.WebElement as We

'Step 1 - Verify access and log in PIM successfully'
MainPage.loginPageSuccessful()

lblFieldName = 'Employment Status'
'Step 2 - Expand filter secion if section is not expanded'
isPresent = WebUI.verifyElementPresent(findTestObject('objWebPIM/Employee/lbl_FieldName', [('labelName') : lblFieldName]), GlobalVariable.G_ShortTimeOut)
if (isPresent == false) {
	//Expand the Employee filter section
	WebUI.click(findTestObject('objWebPIM/Main/ico_ExpandFilter'))
}

lblFieldName = 'Employee Name'
txtFieldValue = 'Dryan'
WebUI.setText(findTestObject('objWebPIM/Main/txt_FieldName', [('labelName') : lblFieldName]), txtFieldValue)

lblFieldName = 'Employee Id'
txtFieldValue = '12345'
WebUI.setText(findTestObject('objWebPIM/Main/txt_FieldName', [('labelName') : lblFieldName]), txtFieldValue)

lblFieldName = 'Employment Status'
txtFieldValue = 'Freelance'
WebUI.click(findTestObject('objWebPIM/Main/ico_ExpandDropdown', [('labelName') : lblFieldName]))
WebUI.click(findTestObject('objWebPIM/Main/dropdown_SpecificItem', [('labelName') : lblFieldName, ('optionName') : txtFieldValue]))
WebUI.verifyElementText(findTestObject('objWebPIM/Main/dropdown_SelectedItem', [('labelName') : lblFieldName, ('optionName') : txtFieldValue]),
	txtFieldValue)

lblFieldName = 'Include'
txtFieldValue = 'Current and Past Employees'
WebUI.click(findTestObject('objWebPIM/Main/ico_ExpandDropdown', [('labelName') : lblFieldName]))
WebUI.click(findTestObject('objWebPIM/Main/dropdown_SpecificItem', [('labelName') : lblFieldName, ('optionName') : txtFieldValue]))
WebUI.verifyElementText(findTestObject('objWebPIM/Main/dropdown_SelectedItem', [('labelName') : lblFieldName, ('optionName') : txtFieldValue]),
	txtFieldValue)

lblFieldName = 'Job Title'
txtFieldValue = 'QA Engineer'
WebUI.click(findTestObject('objWebPIM/Main/ico_ExpandDropdown', [('labelName') : lblFieldName]))
WebUI.click(findTestObject('objWebPIM/Main/dropdown_SpecificItem', [('labelName') : lblFieldName, ('optionName') : txtFieldValue]))
WebUI.verifyElementText(findTestObject('objWebPIM/Main/dropdown_SelectedItem', [('labelName') : lblFieldName, ('optionName') : txtFieldValue]),
	txtFieldValue)

lblFieldName = 'Sub Unit'
txtFieldValue = 'Engineering'
WebUI.click(findTestObject('objWebPIM/Main/ico_ExpandDropdown', [('labelName') : lblFieldName]))
WebUI.click(findTestObject('objWebPIM/Main/dropdown_SpecificItem', [('labelName') : lblFieldName, ('optionName') : txtFieldValue]))
WebUI.verifyElementText(findTestObject('objWebPIM/Main/dropdown_SelectedItem', [('labelName') : lblFieldName, ('optionName') : txtFieldValue]),
	txtFieldValue)

//Click Search button'
WebUI.click(findTestObject('objWebPIM/Main/btn_Submit'))

'Step 4 - Verify filter result'
isPresent = WebUI.verifyElementPresent(findTestObject('objWebPIM/Employee/lbl_FilterResult'), GlobalVariable.G_Timeout)

if (isPresent == true) {
	lblCompare = 'No Records Found'
	lblResult = WebUI.getText(findTestObject('objWebPIM/Employee/lbl_FilterResult'))
	if(lblResult.equals(lblCompare)) {
		println("The result of the search table is displayed as 'No Records Found'")
	}else {
		println("The result of the table is displayed as $lblResult")
	}
}

WebUI.closeBrowser()
