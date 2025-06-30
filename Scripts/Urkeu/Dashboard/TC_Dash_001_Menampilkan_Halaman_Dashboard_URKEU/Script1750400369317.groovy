import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling

// Gunakan parameter jika disediakan, atau fallback ke default
boolean finalCloseBrowser = binding.hasVariable('closeBrowserAfter') ? closeBrowserAfter : true


// Step 1: Panggil test case dashboard
WebUI.callTestCase(findTestCase('Auth/Login_Logout/TC_LG_001_Melakukan_Login_Pada_Aplikasi'), [
  'nip': '197505252003121007',
  'password': 'fzqqY0qJjYTuJiVJRZh4ag==',
  'closeBrowserAfter': false
], FailureHandling.STOP_ON_FAILURE)

TestObject UrkeuApps = new TestObject('UrkeuApps')
UrkeuApps.addProperty(
	'xpath', 
	ConditionType.EQUALS, 
	"//a[@href='/administrasi-urkeu/dashboard' and .//p[text()='Urkeu']]")

WebUI.waitForElementClickable(UrkeuApps, 10)
WebUI.click(UrkeuApps)

// Step 3: Verifikasi halaman muncul
// Membuat TestObject secara dinamis
TestObject Urkeu = new TestObject('Urkeu')
Urkeu.addProperty('xpath', 
	ConditionType.EQUALS, 
	"//span[text()='Urkeu' and contains(@class, 'MuiTypography-caption')]")

// Verifikasi bahwa elemen muncul
WebUI.verifyElementPresent(Urkeu, 10)

// Step 4: Tutup browser
if (finalCloseBrowser) {
	WebUI.closeBrowser()
}