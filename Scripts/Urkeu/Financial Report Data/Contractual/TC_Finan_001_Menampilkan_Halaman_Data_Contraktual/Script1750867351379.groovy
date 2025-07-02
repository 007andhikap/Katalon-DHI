import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import helpers.LinkHelper

// Gunakan parameter jika disediakan, atau fallback ke default
boolean finalCloseBrowser = binding.hasVariable('closeBrowserAfter') ? closeBrowserAfter : true


// Step 1: Panggil test case dashboard
WebUI.callTestCase(
    findTestCase('Test Cases/Urkeu/Dashboard/TC_Dash_001_Menampilkan_Halaman_Dashboard_URKEU'), 
    [
        'closeBrowserAfter': false
    ], 
    FailureHandling.STOP_ON_FAILURE
)

LinkHelper.TombolHref("/administrasi-urkeu/financial-report-data")

// Step 3: Verifikasi halaman muncul
// Membuat TestObject secara dinamis
TestObject Finan = new TestObject('Finan')
Finan.addProperty('xpath', 
	ConditionType.EQUALS, 
	"//p[text()='CONTRAKTUAL' and contains(@class, 'MuiTypography-body1')]")

// Verifikasi bahwa elemen muncul
WebUI.verifyElementPresent(Finan, 10)

// Step 4: Tutup browser
if (finalCloseBrowser) {
	WebUI.closeBrowser()
}