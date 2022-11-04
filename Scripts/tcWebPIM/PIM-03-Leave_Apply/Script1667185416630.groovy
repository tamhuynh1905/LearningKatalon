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
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import com.kms.MainPage
import com.kms.LeavePage

'Step 1 - Verify access and log in PIM successfully'
MainPage.loginPageSuccessful()

'Step 2 - Access Leave page'
pgeName = 'Leave'
pgeTitle = 'Leave List'
MainPage.accessPageSuccessful(pgeName, pgeTitle)

'Step 3 - Apply Leave form'
pgeName = 'Apply'
pgeTitle = 'Apply Leave'
isActionPage = true
MainPage.accessSubPageSuccessful(pgeName, pgeTitle, isActionPage)

lblFieldName = 'Leave Type'
txtFieldValue = 'CAN - Bereavement'
isAutoExpand = false
MainPage.selectFromDropdownSuccessful(lblFieldName, txtFieldValue, isAutoExpand)

lblValue = 'Leave Balance'
lblRemainBefore = MainPage.getElementText(findTestObject('objWebPIM/Leave/lbl_Value', [('lblValue') : lblValue]))
lblRemainDaysBefore = lblRemainBefore.getAt(0)

lblFieldName = 'From Date'
//lblAttributeName = 'placeholder'
isCurrent = true
MainPage.setDate(findTestObject('objWebPIM/Leave/dte_FromDateField'), isCurrent, 0)

isCurrent = false
addDays = 1
MainPage.setDate(findTestObject('objWebPIM/Leave/dte_ToDateField'), isCurrent, addDays)

lblFieldName = 'Comments'
txtFieldValue = 'Take PTO'
MainPage.inputTextSuccessful(findTestObject('objWebPIM/Main/txt_AreaFieldName', [('labelName') : lblFieldName]), txtFieldValue)

lblFieldName = 'Partial Days'
txtFieldValue = 'All Days'
MainPage.selectFromDropdownSuccessful(lblFieldName, txtFieldValue, isAutoExpand)

lblFieldName = 'Duration'
txtFieldValue = 'Specify Time'
lblValue = 'Duration'
MainPage.selectFromDropdownSuccessful(lblFieldName, txtFieldValue, isAutoExpand)
WebUI.verifyElementText(findTestObject('objWebPIM/Leave/lbl_Value', [('lblValue') : lblValue]), '8.00')
	
WebUI.click(findTestObject('objWebPIM/Main/btn_Submit'))
	
strMessage = 'Successfully Saved'
WebUI.verifyElementText(findTestObject('objWebPIM/Leave/toast_Message'), strMessage)

lblFieldName = 'Leave Type'
txtFieldValue = 'CAN - Bereavement'
MainPage.selectFromDropdownSuccessful(lblFieldName, txtFieldValue, isAutoExpand)

lblValue = 'Leave Balance'
lblRemainAfter = MainPage.getElementText(findTestObject('objWebPIM/Leave/lbl_Value', [('lblValue') : lblValue]))
lblRemainDaysAfter = lblRemainAfter.getAt(0)
strFilteredStatusName = ''
if(lblRemainAfter.isNumber() & lblRemainBefore.isNumber() & lblRemainAfter-lblRemainBefore==2) {
	strFilteredStatusName = 'Pending Approval (' + lblRemainAfter-lblRemainBefore + ')'
	println("Amount of ${lblValue} is reduced by 2") 
}

'Step 3 - Navigate to My Leave'
pgeForm = 'My Leave'
pgeTitle = 'My Leave List'
isActionPage = false
MainPage.accessSubPageSuccessful(pgeForm, pgeTitle, isActionPage)

isCurrent = true
MainPage.setDate(findTestObject('objWebPIM/Leave/dte_FromDateField'), isCurrent, 0)

isCurrent = false
MainPage.setDate(findTestObject('objWebPIM/Leave/dte_ToDateField'), isCurrent, addDays)

lblFieldName = 'Leave Type'
txtFieldValue = 'CAN - Bereavement'
MainPage.selectFromDropdownSuccessful(lblFieldName, txtFieldValue, isAutoExpand)

WebUI.click(findTestObject('objWebPIM/Main/btn_Submit'))

println(strFilteredStatusName)
isCancel = LeavePage.verifyMyLeave(strFilteredStatusName)
if(isCancel) {
	println('Cancel my leave success')
}else {
	println('Cannot cancel my leave')
}

WebUI.closeBrowser()


