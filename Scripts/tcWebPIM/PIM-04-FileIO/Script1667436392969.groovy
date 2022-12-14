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

import javax.swing.JFileChooser
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

import com.kms.MainPage
import com.kms.LeavePage
import com.kms.EmployeePage

'Step 1 - Verify access and log in successfully'
MainPage.loginPageSuccessful()

'Step 2 - Go to PIM/Configuration/Data Import'
mnuActiveVerticalItem = WebUI.getText(findTestObject('objWebPIM/Main/mnu_VerticalActiveItem'))
pgeName = 'PIM'
if(mnuActiveVerticalItem != pgeName) {	
	pgeTitle = 'Employee Information'
	MainPage.accessPageSuccessful(pgeName, pgeTitle)
}
pgeMenuItem = 'Configuration'
pgeSubMenuItem = 'Data Import'
EmployeePage.accessSubPageMenuItem(pgeMenuItem, pgeSubMenuItem)

'Step 3 - Download file'
WebUI.click(findTestObject('objWebPIM/Employee/link_Download'))

'Step 4 - Insert data to download file'
WebUI.verifyElementPresent(findTestObject('objWebPIM/Employee/link_Download'), GlobalVariable.G_Timeout)

//File csvFile = new File(GlobalVariable.G_Path)
Path downloadDir = Paths.get(System.getProperty("user.home"), "Downloads")
Path fileToUpload = downloadDir.resolve(GlobalVariable.G_FileName)
File csvFile = new File(fileToUpload.toString())
strFullName = EmployeePage.insertToCSVFile(csvFile)
println(strFullName)

'Step 5 - Upload file'
EmployeePage.browseFile(fileToUpload.toString())
WebUI.click(findTestObject('objWebPIM/Main/btn_Submit'))

//strMessage = 'Number of Records Imported: 1'
strMessage = '1 Record Successfully Imported'
if(WebUI.verifyElementText(findTestObject('objWebPIM/Main/lbl_DialogText'), strMessage)) {
	WebUI.click(findTestObject('objWebPIM/Main/btn_DialogOK'))
}

csvFile.delete()

'Step 6 - Navigate to Employee List'
pgeForm = 'Employee List'
pgeTitle = 'Employee Information'
isActionPage = false
MainPage.accessSubPageSuccessful(pgeForm, pgeTitle, isActionPage)

lblFieldName = 'Employee Name'
strFull = strFullName[0] + '  ' + strFullName[1] + '  ' + strFullName[2]
//strFullForDropDown = "'" + strFullName[0] + "'  '" + strFullName[1] + "'  '" + strFullName[2] + "'"
WebUI.setText(findTestObject('objWebPIM/Main/txt_FieldName', [('labelName') : lblFieldName]), strFullName[0])
isAutoExpand = true
MainPage.selectFromDropdown(lblFieldName, strFull, isAutoExpand)

WebUI.click(findTestObject('objWebPIM/Main/btn_Submit'))

if(EmployeePage.deleteNewEmployee(strFullName)) {
	println('delete new employee success')
}else {
	println('delete new employee not success')
}

WebUI.closeBrowser()



