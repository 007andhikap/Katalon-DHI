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
    findTestCase('Test Cases/Official Travel Administration/Dashboard/TC_Dash_001_Menampilkan_Halaman_Official_Travel_Administration'), 
    [
        'closeBrowserAfter': false
    ], 
    FailureHandling.STOP_ON_FAILURE
)

TestObject MenuTravelData = new TestObject('MenuTravelData')
MenuTravelData.addProperty(
	'xpath', 
	ConditionType.EQUALS, 
	"//a[contains(@href, '/perjalanan-dinas/official-travel-data')]//span[contains(text(), 'Official Travel Data')]")

WebUI.waitForElementClickable(MenuTravelData, 10)
WebUI.click(MenuTravelData)

// Step 3: Verifikasi halaman muncul
// Membuat TestObject secara dinamis
TestObject TravelData = new TestObject('TravelData')
TravelData.addProperty('xpath', 
	ConditionType.EQUALS, 
	"//p[text()='Visitor Attendance' and contains(@class, 'MuiTypography-body1')]")

// Verifikasi bahwa elemen muncul
WebUI.verifyElementPresent(TravelData, 10)

// Step 4: Tutup browser
if (finalCloseBrowser) {
	WebUI.closeBrowser()
}