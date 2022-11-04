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

username = 'Nam'
password = '12345678'

'Step 1 - Create new user'
//resCreate = WS.sendRequest(findTestObject('API/POST - Create User'))
//resCreate = WS.sendRequest(findTestObject('API/POST - Create User', [('username') : 'Nam']))
//resCreate = WS.sendRequest(findTestObject('API/POST - Create User', [('username') : username]))
resCreate = WS.sendRequest(findTestObject('API/POST - Create User', [('username') : username, ('password') : password]))

'VP - Create new user'
WS.verifyResponseStatusCode(resCreate, 200)

WS.verifyElementPropertyValue(resCreate, 'username', username)

'Get Id'
getId = WS.getElementPropertyValue(resCreate, 'id')

WS.comment("New user name have ID:  ${getId}")
println("Console log - New user name have ID:  ${getId}")

'Step 2 - Get user by Id'
resGet = WS.sendRequest(findTestObject('API/GET - Get User', [('id') : getId]))

'VP - User info'
WS.verifyResponseStatusCode(resGet, 200)

WS.verifyElementPropertyValue(resGet, 'username', username)

WS.verifyElementPropertyValue(resGet, 'gender', 'MALE')