import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling

String nip = '197505252003121006'
String encryptedPassword = 'nwr9I1qT+J2caJf8qNsnMw=='

WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl('http://13.20.23.15:6201/login')

// Klik tombol login SSO
WebUI.click(findTestObject('Object Repository/Auth/Login/Page_DHI Workspace/button_Masuk Dengan SSO HUBINTER'))

// Masukkan NIP dan password
WebUI.setText(findTestObject('Object Repository/Auth/Login/Page_Hubinter - Sistem Global Terpadu/input_Loading_input'), nip)
WebUI.setEncryptedText(findTestObject('Object Repository/Auth/Login/Page_Hubinter - Sistem Global Terpadu/input_Loading_normal_login_password'), encryptedPassword)

// Klik tombol login (jika ada)
WebUI.click(findTestObject('Object Repository/Auth/Login/Page_Hubinter - Sistem Global Terpadu/button_Sign In'))

// Buat TestObject dinamis untuk pesan error
TestObject errorMsg = new TestObject('dynamicErrorMsg')
errorMsg.addProperty("xpath", ConditionType.EQUALS, "//span[contains(text(), 'Failed to sign in')]")

// Tunggu error muncul dan verifikasi
WebUI.waitForElementVisible(errorMsg, 10, FailureHandling.OPTIONAL)

String actualError = WebUI.getText(errorMsg)
WebUI.comment("Pesan error ditampilkan: " + actualError)

// Verifikasi isi pesan fleksibel
if (actualError.contains("Failed to sign in") && actualError.contains(nip)) {
    WebUI.comment("✅ Error login sesuai ekspektasi untuk NIP: " + nip)
} else {
    WebUI.takeScreenshot()
    assert false : "❌ Pesan error tidak sesuai. Ditemukan: " + actualError
}

WebUI.closeBrowser()
