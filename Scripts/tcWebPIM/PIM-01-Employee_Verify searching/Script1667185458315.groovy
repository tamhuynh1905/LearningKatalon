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

lblSearch = 'Employment Status'
'Step 2 - Expand filter secion if section is not expanded'
isPresent = WebUI.verifyElementPresent(findTestObject('objWebPIM/Employee/lbl_FieldName', [('labelName') : lblSearch]), GlobalVariable.G_ShortTimeOut)
//isPresent = We.verifyElementPresent('objWebPIM/Employee/lblFieldName', 'labelName', lblSearch)
if (isPresent == false) {
	//Expand the Employee filter section
	WebUI.click(findTestObject('objWebPIM/Main/ico_ExpandFilter'))
}

optSearch = 'Full-Time Contract'
'Step 3 - Search information with Employment Status: Full-Time Contract and Include: Current and Past Employees'
//expand dropdown > Employment Status - select "Full-Time Contract" > verify "Full-Time Contract"
WebUI.click(findTestObject('objWebPIM/Main/ico_ExpandDropdown', [('labelName') : lblSearch]))
WebUI.click(findTestObject('objWebPIM/Main/dropdown_SpecificItem', [('labelName') : lblSearch, ('optionName') : optSearch]))
WebUI.verifyElementText(findTestObject('objWebPIM/Main/dropdown_SelectedItem', [('labelName') : lblSearch, ('optionName') : optSearch]),
	optSearch)
//We.clickWebElement('objWebPIM/Employee/icoExpandDropdown', 'labelName', lblSearch)
//We.clickWebElement('objWebPIM/Employee/itemDropdown', 'labelName', lblSearch, 'optionName', optSearch)
//We.verifyElementText('objWebPIM/Employee/selectedItemDropdown', 'labelName', lblSearch, 'optionName', optSearch, optSearch)

//expand dropdown > Include - select "Current and Past Employees" > verify "Current and Past Employees"
lblSearch = 'Include'
optSearch = 'Current and Past Employees'
WebUI.click(findTestObject('objWebPIM/Main/ico_ExpandDropdown', [('labelName') : lblSearch]))
WebUI.click(findTestObject('objWebPIM/Main/dropdown_SpecificItem', [('labelName') : lblSearch, ('optionName') : optSearch]))
WebUI.verifyElementText(findTestObject('objWebPIM/Main/dropdown_SelectedItem', [('labelName') : lblSearch, ('optionName') : optSearch]),
    optSearch)
//We.clickWebElement('objWebPIM/Employee/icoExpandDropdown', 'labelName', lblSearch)
//We.clickWebElement('objWebPIM/Employee/itemDropdown', 'labelName', lblSearch, 'optionName', optSearch)
//We.verifyElementText('objWebPIM/Employee/selectedItemDropdown', 'labelName', lblSearch, 'optionName', optSearch, optSearch)

//Click Search button'
WebUI.click(findTestObject('objWebPIM/Main/btn_Submit'))

'Step 4 - Verify filter result'
isPresent = WebUI.verifyElementPresent(findTestObject('objWebPIM/Employee/lbl_FilterResult'), GlobalVariable.G_Timeout)

if (isPresent == true) {
	lblCompare = '(*) Records Found'

	lblResult = WebUI.getText(findTestObject('objWebPIM/Employee/lbl_FilterResult'))

	lblCheck = lblResult.getAt(1)

	if (lblCheck.isNumber() && (lblCheck > 1)) {
		lblResult.replaceAll(lblCheck, '*')

		if (lblResult == lblCompare) {
			println("The result of the table is displayed as $lblCompare and * is more than 1")
		}
	}
}



//CustomKeywords.'com.kms.MainPage.loginPageSuccessful'()
//WebUI.callTestCase(findTestCase('tcWebPIM/Common/PIM-Verify login'), [:], FailureHandling.STOP_ON_FAILURE)
//isPresent = WebUI.verifyElementPresent(findTestObject('objWebPIM/Employee/lbl_FieldName', [('labelName') : 'Employment Status']),
//  GlobalVariable.G_Timeout)
//WebUI.click(findTestObject('objWebPIM/Main/ico_ExpandDropdown', [('labelName') : lblSearch])j)
//WebUI.click(findTestObject('objWebPIM/Main/dropdown_SpecificItem', [('labelName') : lblSearch, ('optionName') : optSearch]))
//WebUI.verifyElementText(findTestObject('objWebPIM/Main/dropdown_SelectedItem', [('labelName') : lblSearch, ('optionName') : optSearch]), 
 //   optSearch)
//WebUI.click(findTestObject('objWebPIM/Main/ico_ExpandDropdown', [('labelName') : lblSearch]))
//WebUI.click(findTestObject('objWebPIM/Main/dropdown_SpecificItem', [('labelName') : lblSearch, ('optionName') : optSearch]))
//WebUI.verifyElementText(findTestObject('objWebPIM/Main/dropdown_SelectedItem', [('labelName') : lblSearch, ('optionName') : optSearch]), 
  //  optSearch)

firstItem = ''
nextItem = ''
'Step 5 - Sort "ID" in ascending'
colHeader = 'Id'
sortType = 'Ascending'
isOrder = false

if (isPresent == true) {
    WebUI.click(findTestObject('objWebPIM/Employee/ico_ExpandSortDropdown', [('columnHeaderName') : colHeader]))
    WebUI.click(findTestObject('objWebPIM/Employee/dropdown_SortItems', [('columnHeaderName') : colHeader, ('sortType') : sortType]))
	
	totalRows = MainPage.countListTotalNumber(findTestObject('Object Repository/objWebPIM/Main/tbl_FilteredRows'))
    //totalRows = WebUI.getElementHeight(findTestObject('objWebPIM/Main/tbl_FilteredRows'))

	isOrder = true
    if (totalRows > 1) {
        for (i = 1; i <= (totalRows - 1); i++) {
            firstItem = WebUI.getText(findTestObject('objWebPIM/Main/tbl_FilteredCell', [('rowNumber') : i, ('cellNumber') : 2]))
            nextItem = WebUI.getText(findTestObject('objWebPIM/Main/tbl_FilteredCell', [('rowNumber') : i + 1, ('cellNumber') : 2]))

            if (firstItem.isNumber() && nextItem.isNumber()) {
                if (firstItem > nextItem) {
                    isOrder = false
                    break
                } 
            }
        }
        
        if (isOrder == true) {
            println('The list of IDs is sorted in ascending order')
        } else {
            println('The list of IDs is not sorted in ascending order')
        }
    }
}

'Step 5 - Sort "Job Title" in ascending'
colHeader = 'Job Title'
sortType = 'Decending'


if (isPresent == true) {
	WebUI.click(findTestObject('objWebPIM/Employee/ico_ExpandSortDropdown', [('columnHeaderName') : colHeader]))
    WebUI.click(findTestObject('objWebPIM/Employee/dropdown_SortItems', [('columnHeaderName') : colHeader, ('sortType') : sortType]))
	
	totalRows = MainPage.countListTotalNumber(findTestObject('Object Repository/objWebPIM/Main/tbl_FilteredRows'))
	
	if (totalRows > 1) {
		isOrder = true;
		for (i = 1; i <= (totalRows - 1); i++) {
			firstItem = WebUI.getText(findTestObject('objWebPIM/Main/tbl_FilteredCell', [('rowNumber') : i, ('cellNumber') : 2]))
			nextItem = WebUI.getText(findTestObject('objWebPIM/Main/tbl_FilteredCell', [('rowNumber') : i + 1, ('cellNumber') : 2]))
			if (nextItem.compareTo(firstItem) > 0) {
				 isOrder = false;
				 break;
			}
		}
		if (isOrder == true) {
			println('The list of Job Title is sorted in decending order')
		} else {
			println('The list of Job Title is not sorted in decending order')
		}
	}
}

WebUI.closeBrowser()


