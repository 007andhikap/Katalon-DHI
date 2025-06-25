import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling

// Gunakan parameter jika disediakan, atau fallback ke default
boolean finalCloseBrowser = binding.hasVariable('closeBrowserAfter') ? closeBrowserAfter : true


// Step 1: Panggil test case dashboard
WebUI.callTestCase(
    findTestCase('Test Cases/Visitor Management/Dashboard/TC_Dash_001_Menampilkan_Halaman_Visitor_Attendance'), 
    [
        'closeBrowserAfter': false
    ], 
    FailureHandling.STOP_ON_FAILURE
)

TestObject MenuVisitor = new TestObject('MenuVisitor')
MenuVisitor.addProperty(
	'xpath', 
	ConditionType.EQUALS, 
	"//a[contains(@href, '/kunjungan-tamu/visitor-data')]//span[contains(text(), 'Visitor Data')]")

WebUI.waitForElementClickable(MenuVisitor, 10)
WebUI.click(MenuVisitor)

// Step 3: Verifikasi halaman muncul
// Membuat TestObject secara dinamis
TestObject Visitor = new TestObject('Visitor Attendance')
Visitor.addProperty('xpath', 
	ConditionType.EQUALS, 
	"//p[text()='Visitor Attendance' and contains(@class, 'MuiTypography-body1')]")

// Verifikasi bahwa elemen muncul
WebUI.verifyElementPresent(Visitor, 10)

// Step 4: Tutup browser
if (finalCloseBrowser) {
	WebUI.closeBrowser()
}