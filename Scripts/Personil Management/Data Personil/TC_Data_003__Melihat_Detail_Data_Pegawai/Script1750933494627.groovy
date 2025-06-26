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
    findTestCase('Test Cases/Personil Management/Data Personil/TC_Data_001_Menampilkan_Halaman_Data_Personel'),
    ['closeBrowserAfter': false],
    FailureHandling.STOP_ON_FAILURE
)

// Step 2: Tunggu table tbody muncul (mencegah error dinamis)
TestObject tableBody = new TestObject('tableBody')
tableBody.addProperty('xpath', ConditionType.EQUALS, "//tbody[contains(@class, 'MuiTableBody-root')]")
WebUI.waitForElementVisible(tableBody, 15)

// Step 3: Klik link detail pada kolom Nama baris pertama (kolom ke-3 berdasarkan HTML kamu)
TestObject NrpDetailLink = new TestObject('NrpDetailLink')
NrpDetailLink.addProperty(
	'xpath',
	ConditionType.EQUALS,
	"//tbody/tr[1]/th[2]/a"
)

WebUI.waitForElementVisible(NrpDetailLink, 10)
WebUI.waitForElementClickable(NrpDetailLink, 10)
WebUI.scrollToElement(NrpDetailLink, 2)

try {
	WebUI.click(NrpDetailLink)
	WebUI.comment("Klik berhasil pada link NRP baris pertama.")
} catch (Exception e) {
	WebElement el = WebUiCommonHelper.findWebElement(NrpDetailLink, 10)
	WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(el))
	WebUI.comment("Klik fallback berhasil menggunakan JavaScript.")
}

// Step 4: Verifikasi halaman detail muncul
TestObject detailTitle = new TestObject('detailTitle')
detailTitle.addProperty(
	'xpath',
	ConditionType.EQUALS,
	"//p[contains(@class, 'MuiTypography-body1') and contains(text(),'Detail Data Pegawai')]"
)

WebUI.waitForElementVisible(detailTitle, 15)
WebUI.verifyElementPresent(detailTitle, 10)


// Step 6: Tutup browser jika diinstruksikan
if (finalCloseBrowser) {
    WebUI.closeBrowser()
}
