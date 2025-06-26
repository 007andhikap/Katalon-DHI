import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling


boolean finalCloseBrowser = binding.hasVariable('closeBrowserAfter') ? closeBrowserAfter : true

// Step 1: Buka halaman Visitor Data
WebUI.callTestCase(
    findTestCase('Test Cases/Personil Management/Dashboard/TC_Dash_001_Menampilkan_Halaman_Data_Personil'),
    ['closeBrowserAfter': false],
    FailureHandling.STOP_ON_FAILURE
)

// Membuat TestObject baru secara dinamis
TestObject DetailUnit = new TestObject('DetailUnit')
// Menambahkan properti XPath ke TestObject
DetailUnit.addProperty(
	'xpath',
	ConditionType.EQUALS,
	"//a[@href='/manajemen-personel/dashboard-manajemen-personel/URKEU?selected=URKEU']//div[text()='URKEU']"
)
WebUI.waitForElementVisible(DetailUnit, 15, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyElementPresent(DetailUnit, 10, FailureHandling.STOP_ON_FAILURE)
WebUI.click(DetailUnit, FailureHandling.STOP_ON_FAILURE)

// Step 4: Verifikasi halaman detail muncul
TestObject detailTitle = new TestObject('detailTitle')
detailTitle.addProperty(
	'xpath',
	ConditionType.EQUALS,
	"//p[contains(@class, 'MuiTypography-body1') and contains(text(),'Detail Data Personil')]"
)
WebUI.waitForElementVisible(detailTitle, 15)
WebUI.verifyElementPresent(detailTitle, 10)


// Step 6: Tutup browser jika diinstruksikan
if (finalCloseBrowser) {
    WebUI.closeBrowser()
}
