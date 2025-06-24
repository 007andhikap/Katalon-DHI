import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import org.openqa.selenium.WebElement
import java.util.Arrays

// Gunakan parameter jika disediakan, atau fallback ke default
boolean finalCloseBrowser = binding.hasVariable('closeBrowserAfter') ? closeBrowserAfter : true

// Step 1: Buka halaman Official Travel Data
WebUI.callTestCase(
    findTestCase('Test Cases/Official Travel Administration/Official Travel Data/TC_Data_001_Menampilkan_Halaman_Official_Travel_Data'),
    ['closeBrowserAfter': false],
    FailureHandling.STOP_ON_FAILURE
)

// Step 2: Tunggu table tbody muncul (mencegah error dinamis)
TestObject tableBody = new TestObject('tableBody')
tableBody.addProperty('xpath', ConditionType.EQUALS, "//tbody[contains(@class, 'MuiTableBody-root')]")
WebUI.waitForElementVisible(tableBody, 15)

// Step 3: Klik link detail pada kolom DINAS baris pertama (kolom ke-3 berdasarkan HTML kamu)
TestObject dinasDetailLink = new TestObject('dinasDetailLink')
dinasDetailLink.addProperty(
    'xpath', 
    ConditionType.EQUALS, 
    "//tbody/tr[1]/td[2]/a"
)

WebUI.waitForElementVisible(dinasDetailLink, 10)
WebUI.waitForElementClickable(dinasDetailLink, 10)
WebUI.scrollToElement(dinasDetailLink, 2)

try {
    WebUI.click(dinasDetailLink)
    WebUI.comment("Klik berhasil pada link DINAS baris pertama.")
} catch (Exception e) {
    WebElement el = WebUiCommonHelper.findWebElement(dinasDetailLink, 10)
    WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(el))
    WebUI.comment("Klik fallback berhasil menggunakan JavaScript.")
}

// Step 4: Verifikasi halaman detail muncul
TestObject detailTitle = new TestObject('detailTitle')
detailTitle.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "//p[contains(@class, 'MuiTypography-body1') and contains(text(),'Detail Offical Travel Data')]"
)

WebUI.waitForElementVisible(detailTitle, 15)
WebUI.verifyElementPresent(detailTitle, 10)

// Step 5: Tutup browser jika perlu
if (finalCloseBrowser) {
    WebUI.closeBrowser()
}
