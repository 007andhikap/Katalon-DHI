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
    findTestCase('Test Cases/E-Office/Dashboard/TC_Das_001_Menampilkan_Halaman_Dashboard'), 
    [
        'closeBrowserAfter': false
    ], 
    FailureHandling.STOP_ON_FAILURE
)

TestObject MenuDisposisi = new TestObject('MenuDisposisi')
MenuDisposisi.addProperty(
	'xpath', 
	ConditionType.EQUALS, 
	"//span[contains(@class, 'MuiTypography-body1') and text()='Disposisi']")

WebUI.waitForElementClickable(MenuDisposisi, 10)
WebUI.click(MenuDisposisi)

// Step 3: Verifikasi halaman muncul
// Membuat TestObject secara dinamis
TestObject Disposisi = new TestObject('Disposisi')
Disposisi.addProperty('xpath', 
	ConditionType.EQUALS, 
	"//h5[contains(@class, 'MuiTypography-h5') and text()='Disposisi']")

// Verifikasi bahwa elemen muncul
WebUI.verifyElementPresent(Disposisi, 10)

// Step 4: Tutup browser
if (finalCloseBrowser) {
	WebUI.closeBrowser()
}