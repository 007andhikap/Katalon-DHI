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

TestObject calendarApps = new TestObject('calendarApps')
calendarApps.addProperty(
	'xpath', 
	ConditionType.EQUALS, 
	"//a[@href='/digital-calendar' and .//p[text()='Calendar']]")

WebUI.waitForElementClickable(calendarApps, 10)
WebUI.click(calendarApps)

// Step 3: Verifikasi halaman muncul
// Membuat TestObject secara dinamis
TestObject DigitalCal = new TestObject('DigitalCal')
DigitalCal.addProperty('xpath', 
	ConditionType.EQUALS, 
	"//span[text()='Digital Calendar' and contains(@class, 'MuiTypography-caption')]")

// Verifikasi bahwa elemen muncul
WebUI.verifyElementPresent(DigitalCal, 10)

// Step 4: Tutup browser
if (finalCloseBrowser) {
	WebUI.closeBrowser()
}