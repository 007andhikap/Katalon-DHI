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

public class InputHelper {

	static void isiTextField(String placeholder, String value) {
		TestObject field = new TestObject()
		field.addProperty('xpath', ConditionType.EQUALS, "//input[@placeholder='${placeholder}']")

		WebUI.waitForElementVisible(field, 10)
		WebUI.setText(field, value)
	}
	
	static void isiTextFieldDenganLabel(String label, String value) {
		TestObject field = new TestObject("input_dari_label_${label}")
		String xpath = "//*[normalize-space(text())='${label}']/ancestor::div[contains(@class,'MuiStack-root')]//input"
		field.addProperty('xpath', ConditionType.EQUALS, xpath)
		WebUI.waitForElementVisible(field, 10)
		WebUI.setText(field, value)
		WebUI.comment("Berhasil mengisi field '${label}' dengan '${value}'")
	}
}
