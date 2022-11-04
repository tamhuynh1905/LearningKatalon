import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/')

WebUI.click(findTestObject('Object Repository/Page_CuraHomepage/btn_MakeAppointment'))

//set text with dynamic test object
WebUI.setText(findTestObject('Web/Dynamic/Textbox', [('label') : 'Username']), 'John Doe')
WebUI.setText(findTestObject('Web/Dynamic/Textbox', [('label') : 'Password']), 'ThisIsNotAPassword')
//set text with normal test object
//WebUI.setText(findTestObject('Object Repository/Page_Login/txt_UserName'), 'John Doe')
//WebUI.setText(findTestObject('Object Repository/Page_Login/txt_Password'), 'ThisIsNotAPassword')


WebUI.click(findTestObject('Object Repository/Page_Login/btn_Login'))

currentURL = WebUI.getUrl()

WebUI.verifyEqual(currentURL, 'https://katalon-demo-cura.herokuapp.com/#appointment')

currentTitle = WebUI.getWindowTitle()

WebUI.verifyEqual(currentTitle, 'CURA Healthcare Service')

WebUI.verifyElementPresent(findTestObject('Object Repository/Page_CuraAppointment/lbl_MakeAppointmentHeader'), 10)

