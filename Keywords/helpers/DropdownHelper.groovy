package helpers

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class DropdownHelper {
	static void pilihDropdownByLabel(String labelText, String teksOpsi) {
	    String xpathDropdown = "//p[contains(text(),'${labelText}')]/following::div[@role='combobox'][1]"
	    TestObject dropdown = new TestObject()
	    dropdown.addProperty("xpath", ConditionType.EQUALS, xpathDropdown)
	
	    WebUI.waitForElementClickable(dropdown, 10)
	    WebUI.click(dropdown)
	
	    String xpathOpsi = "//li[normalize-space(text())='${teksOpsi}']"
	    TestObject opsi = new TestObject()
	    opsi.addProperty("xpath", ConditionType.EQUALS, xpathOpsi)
	
	    WebUI.waitForElementClickable(opsi, 5)
	    WebUI.click(opsi)
	}
}
