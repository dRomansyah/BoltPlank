import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.boltplank.constant.MyConstant
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

if (WebUI.verifyElementPresent(findTestObject('Object Repository/HRIS/Page_SunFish HR 54/input_User Name_txtname'), 1)) {
    WebUI.setText(findTestObject('Object Repository/HRIS/Page_SunFish HR 54/input_User Name_txtname'), MyConstant.HRIS_USER)

    WebUI.setEncryptedText(findTestObject('Object Repository/HRIS/Page_SunFish HR 54/input_Password_txtpassword'), MyConstant.ENC_PASSWORD)

    WebUI.click(findTestObject('Object Repository/HRIS/Page_SunFish HR 54/input_Password_button'))
}