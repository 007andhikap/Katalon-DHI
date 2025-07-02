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

public class Scrollhelper {
	static void scrollKeElemenDenganTeks(String tagNama, String isiTeks) {
	    String xpath = "//${tagNama}[contains(text(),'${isiTeks}')]"
	    TestObject elemen = new TestObject()
	    elemen.addProperty("xpath", ConditionType.EQUALS, xpath)
	
	    WebUI.scrollToElement(elemen, 5)
	    String actualText = WebUI.getText(elemen)
	    assert actualText.contains(isiTeks) : "Teks tidak mengandung '${isiTeks}', tapi: '${actualText}'"
	}
}
