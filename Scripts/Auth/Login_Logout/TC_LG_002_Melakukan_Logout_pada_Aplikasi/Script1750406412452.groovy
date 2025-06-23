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
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.WebElement
import java.util.Arrays

// Step login
WebUI.callTestCase(findTestCase('Auth/Login_Logout/TC_LG_001_Melakukan_Login_Pada_Aplikasi'), [
  'nip': '197505252003121007',
  'password': 'fzqqY0qJjYTuJiVJRZh4ag==',
  'closeBrowserAfter': false
], FailureHandling.STOP_ON_FAILURE)

// Buka dropdown menu avatar
WebUI.click(findTestObject('Object Repository/Auth/Logout/Page_DHI Workspace/img_NRP197505252003121007_MuiAvatar-img css-45do71'))
WebUI.delay(1)

// Tunggu tombol logout muncul dan siap diklik
WebUI.waitForElementVisible(findTestObject('Object Repository/Auth/Logout/Page_DHI Workspace/button_Logout'), 5)
WebUI.waitForElementClickable(findTestObject('Object Repository/Auth/Logout/Page_DHI Workspace/button_Logout'), 5)

// Coba klik biasa dulu
try {
    WebUI.click(findTestObject('Object Repository/Auth/Logout/Page_DHI Workspace/button_Logout'))
} catch (Exception e) {
    // Kalau gagal klik biasa, pakai JavaScript
    WebElement logoutBtn = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Auth/Logout/Page_DHI Workspace/button_Logout'), 5)
    WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(logoutBtn))
}

// Verifikasi kembali ke halaman login
TestObject btnSignIn = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//button[contains(@class, 'login-button') and .//span[text()='Sign In']]")
WebUI.waitForElementVisible(btnSignIn, 5)
WebUI.verifyElementPresent(btnSignIn, 2)

WebUI.closeBrowser()
