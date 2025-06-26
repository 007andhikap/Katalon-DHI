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
    findTestCase('Test Cases/Personil Management/Dashboard/TC_Dash_001_Menampilkan_Halaman_Data_Personil'), 
    [
        'closeBrowserAfter': false
    ], 
    FailureHandling.STOP_ON_FAILURE
)

TestObject MenuDataPersonil = new TestObject('MenuDataPersonil')
MenuDataPersonil.addProperty(
	'xpath', 
	ConditionType.EQUALS, 
	"//a[contains(@href, '/manajemen-personel/data-personel')]//span[contains(text(), 'Data Personil')]")

WebUI.waitForElementClickable(MenuDataPersonil, 10)
WebUI.click(MenuDataPersonil)

// Step 3: Verifikasi halaman muncul
// Membuat TestObject secara dinamis
TestObject Letter = new TestObject('Data Personel')
Letter.addProperty('xpath', 
	ConditionType.EQUALS, 
	"//p[text()='Data Personel' and contains(@class, 'MuiTypography-body1')]")

// Verifikasi bahwa elemen muncul
WebUI.verifyElementPresent(Letter, 10)

// Step 4: Tutup browser
if (finalCloseBrowser) {
	WebUI.closeBrowser()
}