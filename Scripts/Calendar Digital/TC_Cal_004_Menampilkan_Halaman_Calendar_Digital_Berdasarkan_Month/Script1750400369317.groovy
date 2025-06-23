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

// Step 2: Klik menu Calendar
TestObject calendarApps = new TestObject('calendarApps')
calendarApps.addProperty(
	'xpath', 
	ConditionType.EQUALS, 
	"//a[@href='/digital-calendar' and .//p[text()='Calendar']]"
)

WebUI.waitForElementClickable(calendarApps, 10)
WebUI.click(calendarApps)

// Step 3: Verifikasi halaman Digital Calendar muncul
TestObject DigitalCal = new TestObject('DigitalCal')
DigitalCal.addProperty(
	'xpath', 
	ConditionType.EQUALS, 
	"//span[text()='Digital Calendar' and contains(@class, 'MuiTypography-caption')]"
)

WebUI.verifyElementPresent(DigitalCal, 10)

// Step 4: Klik tombol week
TestObject Months = new TestObject('Months')
Months.addProperty(
	'xpath', 
	ConditionType.EQUALS, 
	"//button[@title='month view' and text()='month']"
)

WebUI.waitForElementClickable(Months, 10)
WebUI.click(Months)

// Step 5: Verifikasi tombol week aktif (punya class 'fc-button-active')
String monthButtonClass = WebUI.getAttribute(Months, 'class')

if (monthButtonClass.contains('fc-button-active')) {
	WebUI.comment('✅ Tombol Month aktif.')
} else {
	WebUI.comment('❌ Tombol Month tidak aktif.')
	WebUI.verifyMatch('fc-button-active', '', false) // Paksa gagal
}

// Step 6: Tutup browser jika diminta
if (finalCloseBrowser) {
	WebUI.closeBrowser()
}
