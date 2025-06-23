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

// Step 1: Panggil test case untuk menampilkan halaman Regulation & Information
WebUI.callTestCase(
    findTestCase('Test Cases/Regulation and Information/TC_RegInfo_001_Menampilkan_Halaman_Regulation_and_Information'), 
    [
        'closeBrowserAfter': false
    ], 
    FailureHandling.STOP_ON_FAILURE
)

// Step 2: Klik tombol "Tambah Data" menggunakan XPath dinamis
TestObject btnTambah = new TestObject('btnTambahData')
btnTambah.addProperty('xpath', 
    ConditionType.EQUALS, 
    "//button[contains(@class, 'MuiButton-root') and .//p[text()='Tambah Data']]")

WebUI.waitForElementVisible(btnTambah, 10)
WebUI.waitForElementClickable(btnTambah, 10)

try {
    WebUI.click(btnTambah)
} catch (Exception e) {
    WebElement el = WebUiCommonHelper.findWebElement(btnTambah, 10)
    WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(el))
}

// Step 3: Verifikasi form "Media Upload" muncul
TestObject pTambahData = new TestObject('pTambahData')
pTambahData.addProperty('xpath', 
    ConditionType.EQUALS, 
    "//h6[contains(@class, 'MuiTypography-h6') and text()='Media Upload']")

WebUI.waitForElementVisible(pTambahData, 10)
WebUI.verifyElementPresent(pTambahData, 10)

// Step 4: Tutup browser jika perlu
if (finalCloseBrowser) {
    WebUI.closeBrowser()
}
