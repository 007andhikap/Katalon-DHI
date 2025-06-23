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

// Step 2: Klik menu Surat Masuk menggunakan XPath fleksibel
TestObject suratKeluar = new TestObject('menuSuratKeluar')
suratKeluar.addProperty(
	'xpath',
	ConditionType.EQUALS,
	"//a[contains(@href, '/e-office/surat-keluar') and .//span[text()='Surat Keluar']]"
)

WebUI.waitForElementClickable(suratKeluar, 10)
WebUI.click(suratKeluar)

// Step 3: Verifikasi halaman Surat Keluar muncul
// Membuat TestObject secara dinamis
TestObject SuratKeluar = new TestObject('SuratKeluar')
SuratKeluar.addProperty('xpath', ConditionType.EQUALS, "//h5[text()='Surat Keluar']")

// Verifikasi bahwa elemen muncul
WebUI.verifyElementPresent(SuratKeluar, 10)

// Step 4: Tutup browser
if (finalCloseBrowser) {
	WebUI.closeBrowser()
}