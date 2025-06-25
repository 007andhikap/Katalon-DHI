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
    findTestCase('Test Cases/Visitor Management/Visitor Data/TC_VisitD_001_Menampilkan_Halaman_Visitor_Data'),
    ['closeBrowserAfter': false],
    FailureHandling.STOP_ON_FAILURE
)

// Step 2: Ambil WebDriver
WebDriver driver = DriverFactory.getWebDriver()

// Step 3: Ambil semua header kolom
List<WebElement> headers = driver.findElements(By.xpath("//table/thead/tr/th"))

// Step 4: Cari index kolom "NAMA"
int namaIndex = -1
for (int i = 0; i < headers.size(); i++) {
    String headerText = headers[i].getText().trim()
    if (headerText.equalsIgnoreCase("NAMA")) {
        namaIndex = i + 1 // XPath pakai index 1-based
        break
    }
}

if (namaIndex == -1) {
    WebUI.comment("Kolom 'NAMA' tidak ditemukan!")
    if (finalCloseBrowser) {
        WebUI.closeBrowser()
    }
    assert false
}

// Step 5: Klik nama visitor di baris pertama
String xpathNama = "//table/tbody/tr[1]/td[" + namaIndex + "]//a"
WebElement namaLink = driver.findElement(By.xpath(xpathNama))
namaLink.click()

WebUI.delay(2)

// Step 6: Tutup browser jika diinstruksikan
if (finalCloseBrowser) {
    WebUI.closeBrowser()
}
