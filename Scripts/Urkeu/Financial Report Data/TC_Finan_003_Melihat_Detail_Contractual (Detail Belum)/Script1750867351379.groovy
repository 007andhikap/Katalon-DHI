import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.WebElement

// Gunakan parameter jika disediakan, atau fallback ke default
boolean finalCloseBrowser = binding.hasVariable('closeBrowserAfter') ? closeBrowserAfter : true

// Step 1: Panggil test case dashboard
WebUI.callTestCase(
    findTestCase('Test Cases/Urkeu/Financial Report Data/TC_Finan_001_Menampilkan_Halaman_Data_Contraktual'), 
    [
        'closeBrowserAfter': false
    ], 
    FailureHandling.STOP_ON_FAILURE
)

//pilih menu berdasarkan href
def klikLinkBerdasarkanHref(String hrefPartial) {
    TestObject link = new TestObject()
    link.addProperty("xpath", ConditionType.EQUALS, "//a[contains(@href, '${hrefPartial}')]")
    WebUI.waitForElementClickable(link, 10)
    WebUI.click(link)
}

klikLinkBerdasarkanHref("/administrasi-urkeu/financial-report-data")

// Step 3: Verifikasi halaman muncul
TestObject Finan = new TestObject('Finan')
Finan.addProperty('xpath', 
    ConditionType.EQUALS, 
    "//p[text()='CONTRAKTUAL' and contains(@class, 'MuiTypography-body1')]"
)
WebUI.verifyElementPresent(Finan, 10)

// Step 4: Klik tombol Detail baris pertama
TestObject dinasDetailButton = new TestObject('dinasDetailButton')
dinasDetailButton.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "(//button[contains(., 'Detail')])[1]" // XPath disederhanakan agar lebih fleksibel
)

// Tambahkan delay untuk memastikan halaman sudah render
WebUI.delay(2)

// Cek jumlah tombol yang ditemukan
List<WebElement> tombolList = WebUiCommonHelper.findWebElements(dinasDetailButton, 10)
WebUI.comment("Jumlah tombol 'Detail' ditemukan: " + tombolList.size())

if (tombolList.size() == 0) {
    WebUI.comment("❌ Tidak ditemukan tombol 'Detail'. Cek apakah data tabel sudah dimuat.")
} else {
    WebUI.scrollToElement(dinasDetailButton, 2)

    try {
        WebUI.click(dinasDetailButton)
        WebUI.comment("✅ Klik berhasil pada tombol Detail baris pertama.")
    } catch (Exception e) {
        WebElement el = WebUiCommonHelper.findWebElement(dinasDetailButton, 10)
        WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(el))
        WebUI.comment("⚠️ Klik fallback berhasil menggunakan JavaScript.")
    }
}

// Step 5: Tutup browser jika diinstruksikan
if (finalCloseBrowser) {
    WebUI.closeBrowser()
}
