import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.navigateToUrl('https://hris.aprisma.co.id/aprisma/sunfish5/ehrm/index.cfm?FID=HR0889&FUID=HR08890003&menu=1&selRequestBy=0')

WebUI.setText(findTestObject('Object Repository/HRIS/Page_eHRM/input_Static Hour_txtFromDate'), '07/22/2019')

WebUI.setText(findTestObject('Object Repository/HRIS/Page_eHRM/input_Fromtoforminutes_TimeFrAfter'), '17:30')

WebUI.setText(findTestObject('Object Repository/HRIS/Page_eHRM/input_Fromtoforminutes_TimeToAfter'), '22:00')

WebUI.click(findTestObject('Object Repository/HRIS/Page_eHRM/input_) Required_idConfirm'))