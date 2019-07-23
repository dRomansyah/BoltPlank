import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.SimpleDateFormat as SimpleDateFormat

import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement

import com.boltplank.constant.MyConstant
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

try {
    CustomKeywords.'com.boltplank.utils.net.NetUtils.checkURL'(MyConstant.URL, 10)

    WebUI.openBrowser(MyConstant.URL)

    WebDriver driver = DriverFactory.getWebDriver()

    WebUI.callTestCase(findTestCase('HRIS/function/Login'), [:], FailureHandling.CONTINUE_ON_FAILURE)

    WebUI.callTestCase(findTestCase('HRIS/function/openAttendanceMenu'), [:], FailureHandling.STOP_ON_FAILURE)

    WebElement iframe = driver.findElement(By.xpath('.//iframe[@id=\'notHideFrame\']'))

    String src = iframe.getAttribute('src')

    WebUI.navigateToUrl(src)

    iframe = null

    int columnCount = 0
	
    List<WebElement> rows_table = CustomKeywords.'com.boltplank.utils.html.Table.getTableByElement'(driver, '(.//*[normalize-space(text()) and normalize-space(.)=\''+MyConstant.HRIS_NAME+'\'])[1]/following::table[1]')

    List<WebElement> attendanceList = new ArrayList()

    for (int rowIndex = 3; rowIndex < rows_table.size(); rowIndex++) {
        List<WebElement> column = rows_table.get(rowIndex).findElements(By.tagName('td'))

        if (column.get(1).getText().equals('Legend :')) {
            break
        }
        
        if (!(column.get(2).getText().trim().equals('')) && !(column.get(4).getText().trim().equals(''))) {
            Map daily = new HashMap()

            daily.put(MyConstant.DATE_KEY, column.get(1).getText().trim())

            daily.put(MyConstant.OUT_KEY, column.get(4).getText().trim())

            daily.put(MyConstant.DAY_KEY, column.get(0).getText().trim())

            attendanceList.add(daily)
        }
    }
    
    WebUI.navigateToUrl(MyConstant.URL)

    WebUI.callTestCase(findTestCase('HRIS/function/openOvertimeMenu'), [:], FailureHandling.STOP_ON_FAILURE)

    iframe = driver.findElement(By.xpath('.//iframe[@id=\'notHideFrame\']'))

    src = iframe.getAttribute('src')

    WebUI.navigateToUrl(src)

    List<WebElement> overtimeDateList = new ArrayList()

    List<WebElement> overtimeDatas = CustomKeywords.'com.boltplank.utils.html.Table.getTableByElement'(driver, '//tr[4]/td/form/table')

    for (int rowIndex = 10; rowIndex < overtimeDatas.size(); rowIndex++) {
        List<WebElement> overtimeData = overtimeDatas.get(rowIndex).findElements(By.tagName('td'))

        overtimeDateList.add(overtimeData.get(4).getText().trim())
    }
    
    println(overtimeDateList)

    println(attendanceList)

    for (int i = 0; i < attendanceList.size; i++) {
        Map attendanceMap = attendanceList.get(i)

        if (!(overtimeDateList.contains(attendanceMap.get(MyConstant.DATE_KEY)))) {
            WebUI.navigateToUrl('https://hris.aprisma.co.id/aprisma/sunfish5/ehrm/index.cfm?FID=HR0889&FUID=HR08890003&menu=1&selRequestBy=0')

            WebUI.setText(findTestObject('Object Repository/HRIS/Page_eHRM/input_Static Hour_txtFromDate'), attendanceMap.get(
                    MyConstant.DATE_KEY))

            WebUI.setText(findTestObject('Object Repository/HRIS/Page_eHRM/input_Fromtoforminutes_TimeFrAfter'), '17:30')

            WebUI.setText(findTestObject('Object Repository/HRIS/Page_eHRM/input_Fromtoforminutes_TimeToAfter'), attendanceMap.get(
                    MyConstant.OUT_KEY))

            WebUI.click(findTestObject('Object Repository/HRIS/Page_eHRM/input_) Required_idConfirm'))

            driver.switchTo().alert().accept()
        }
    }
}
catch (Exception e) {
    e.printStackTrace()
}
finally { 
    SimpleDateFormat sdf = new SimpleDateFormat('dd-MM-yyyy kk-mm')

    println(('D:/Myself/HRIS/' +"BOLT PLANK SS"+ sdf.format(new Date())) + '.jpg')

    WebUI.takeScreenshot((MyConstant.SCREENSHOT_PATH + sdf.format(new Date())) + '.jpg')

    WebUI.closeBrowser()
}