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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

// Gunakan parameter jika disediakan, atau fallback ke default
String finalNip = binding.hasVariable('nip') ? nip : '197505252003121007'
String finalPassword = binding.hasVariable('password') ? password : 'fzqqY0qJjYTuJiVJRZh4ag=='
boolean finalCloseBrowser = binding.hasVariable('closeBrowserAfter') ? closeBrowserAfter : true

WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl('http://13.20.23.15:6201/login')

// Klik tombol SSO
WebUI.click(findTestObject('Object Repository/Auth/Login/Page_DHI Workspace/button_Masuk Dengan SSO HUBINTER'))

// Login ke Hubinter
WebUI.setText(findTestObject('Object Repository/Auth/Login/Page_Hubinter - Sistem Global Terpadu/input_Loading_input'), finalNip)
WebUI.setEncryptedText(findTestObject('Object Repository/Auth/Login/Page_Hubinter - Sistem Global Terpadu/input_Loading_normal_login_password'), finalPassword)
WebUI.click(findTestObject('Object Repository/Auth/Login/Page_Hubinter - Sistem Global Terpadu/button_Sign In'))

// Klik tombol Yes jika ada prompt "Enable"
WebUI.click(findTestObject('Object Repository/Auth/Login/Page_DHI Workspace/button_Yes, Enable'))

// Verifikasi login berhasil
WebUI.verifyElementPresent(findTestObject('Object Repository/Auth/Login/Page_DHI Workspace/p_WELCOME'), 5)

// Tutup browser hanya jika parameter menyuruh menutup
if (finalCloseBrowser) {
    WebUI.closeBrowser()
}
