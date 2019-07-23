package com.boltplank.utils.html

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword

public class Table {
	@Keyword
	def getTableByElement(WebDriver driver, String xpath){
		WebElement tabel = driver.findElement(By.xpath(xpath))
		List<WebElement> rows = tabel.findElements(By.tagName('tr'))
		return rows
	}
}
