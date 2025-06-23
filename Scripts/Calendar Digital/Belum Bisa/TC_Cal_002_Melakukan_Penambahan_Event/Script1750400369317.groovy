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

// Step 1: Panggil test case untuk menampilkan halaman Surat Keluar
WebUI.callTestCase(
    findTestCase('Test Cases/Calendar Digital/TC_Cal_001_Menampilkan_Halaman_Calendar'), 
    [
        'closeBrowserAfter': false
    ], 
    FailureHandling.STOP_ON_FAILURE
)

// Step 2: Klik tombol "Tambah Data" menggunakan XPath dinamis
TestObject btnCreate = new TestObject('btnCreate')
btnCreate.addProperty('xpath', ConditionType.EQUALS, "//button[contains(@class, 'fc-create-button') and text()='Create Event']")

WebUI.waitForElementVisible(btnCreate, 10)
WebUI.waitForElementClickable(btnCreate, 10)
WebUI.scrollToElement(btnCreate, 5)

try {
    WebUI.click(btnCreate)
} catch (Exception e) {
    WebElement el = WebUiCommonHelper.findWebElement(btnCreate, 10)
    WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(el))
}

// Step 3: Verifikasi form "Tambah data" muncul (menggunakan <p>)
TestObject pCreateEvent = new TestObject('pCreateEvent')
pCreateEvent.addProperty('xpath', ConditionType.EQUALS, "//h2[contains(text(), 'Create New Event')]")

WebUI.waitForElementVisible(pCreateEvent, 10)
WebUI.verifyElementPresent(pCreateEvent, 10)

// Step 4: Tutup browser jika perlu
if (finalCloseBrowser) {
    WebUI.closeBrowser()
}
