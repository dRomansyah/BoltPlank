import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.click(findTestObject('Object Repository/HRIS/Page_eHRM/a_Time Attendance'))

WebUI.mouseOver(findTestObject('HRIS/Page_eHRM/div_Overtime'))

WebUI.click(findTestObject('HRIS/Page_eHRM/div_My Overtime Request History'))