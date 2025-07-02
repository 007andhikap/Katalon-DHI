import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import helpers.TabHelper

// Gunakan parameter jika disediakan, atau fallback ke default
boolean finalCloseBrowser = binding.hasVariable('closeBrowserAfter') ? closeBrowserAfter : true

// Step 1: Panggil test case dashboard
WebUI.callTestCase(
    findTestCase('Test Cases/Urkeu/Financial Report Data/Contractual/TC_Finan_001_Menampilkan_Halaman_Data_Contraktual'), 
    [
        'closeBrowserAfter': false
    ], 
    FailureHandling.STOP_ON_FAILURE
)

// Step 2: Klik tab "NON KONTRAKTUAL"
TabHelper.klikTabBerdasarkanTeks("NON CONTRAKTUAL")

// Step 3: Verifikasi halaman muncul
// Membuat TestObject secara dinamis
TestObject Non = new TestObject('Non')
Non.addProperty('xpath', 
	ConditionType.EQUALS, 
	"//p[normalize-space()='NON CONTRAKTUAL' and contains(@class, 'MuiTypography-body1')]")

// Delay sebentar untuk memastikan UI stabil
WebUI.delay(1)

// Verifikasi bahwa elemen muncul
WebUI.verifyElementPresent(Non, 10)

// Step 4: Tutup browser jika diminta
if (finalCloseBrowser) {
	WebUI.closeBrowser()
}
