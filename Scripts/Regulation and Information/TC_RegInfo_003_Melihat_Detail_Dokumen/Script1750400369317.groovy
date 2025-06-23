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

// Step 2: Scroll ke elemen "PDF" dan klik
TestObject pdfElement = new TestObject('btnPDF')
pdfElement.addProperty('xpath',
    ConditionType.EQUALS,
    "//p[text()='PDF' and contains(@class, 'MuiTypography-body1')]")

WebUI.waitForElementVisible(pdfElement, 10)
WebUI.scrollToElement(pdfElement, 2)

try {
    WebUI.click(pdfElement)
} catch (Exception e) {
    WebElement el = WebUiCommonHelper.findWebElement(pdfElement, 10)
    WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(el))
}

// Step 3: Verifikasi halaman detail muncul (judul "Peraturan Pimpinan")
TestObject detailTitle = new TestObject('titlePeraturanPimpinan')
detailTitle.addProperty('xpath',
    ConditionType.EQUALS,
    "//h3[text()='Peraturan Pimpinan' and contains(@class, 'MuiTypography-h3')]")

WebUI.waitForElementVisible(detailTitle, 10)
WebUI.verifyElementPresent(detailTitle, 10)

// Step 4: Tutup browser jika perlu
if (finalCloseBrowser) {
    WebUI.closeBrowser()
}
