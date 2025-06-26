import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import org.openqa.selenium.WebElement
import com.kms.katalon.core.configuration.RunConfiguration
import java.util.Arrays

boolean finalCloseBrowser = binding.hasVariable('closeBrowserAfter') ? closeBrowserAfter : true

// Step 1: Buka dashboard
WebUI.callTestCase(
    findTestCase('Test Cases/Personil Management/Data Personil/TC_Data_001_Menampilkan_Halaman_Data_Personel'),
    ['closeBrowserAfter': false],
    FailureHandling.STOP_ON_FAILURE
)

// Step 2: Klik tombol Application Letter Support
TestObject btnApplication = new TestObject('btnRegis')
btnApplication.addProperty('xpath', ConditionType.EQUALS, "//button[.//p[text()='Tambah Personel']]")
WebUI.waitForElementClickable(btnApplication, 10)
try {
    WebUI.click(btnApplication)
} catch (Exception e) {
    WebElement el = WebUiCommonHelper.findWebElement(btnApplication, 10)
    WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(el))
}

// Step 3: Verifikasi form
TestObject pTambahData = new TestObject('pTambahData')
pTambahData.addProperty('xpath', ConditionType.EQUALS, "//p[contains(@class, 'MuiTypography-body1') and text()='Tambah Data Personil']")
WebUI.verifyElementPresent(pTambahData, 10)

// Step 4: Isi field teks
def isiTextFieldByLabel(String labelText, String value) {
    String xpath = "//p[text()='${labelText}']/ancestor::div[contains(@class,'css-3lm9t3')]//input"

    TestObject inputField = new TestObject()
    inputField.addProperty('xpath', ConditionType.EQUALS, xpath)

    WebUI.waitForElementVisible(inputField, 10)
    WebUI.setText(inputField, value)
}

isiTextFieldByLabel('Nama Lengkap', 'dimas')
isiTextFieldByLabel('NRP', '123456')
isiTextFieldByLabel('Satuan Kerja', 'Sesdit')


// Step Tambahan: Pilihan dari dropdown
def pilihDropdown(String labelDropdown, String nilaiOpsi) {
    // Cari dropdown berdasarkan label
    TestObject dropdown = new TestObject("dropdown")
    dropdown.addProperty("xpath", ConditionType.EQUALS,
        "//div[p[text()='${labelDropdown}']]/following-sibling::div//div[@role='combobox']")

    // Klik dropdown untuk membuka opsi
    WebUI.waitForElementClickable(dropdown, 10)
    WebUI.click(dropdown)

    // Cari dan klik opsi berdasarkan teks
    TestObject opsi = new TestObject("opsiDropdown")
    opsi.addProperty("xpath", ConditionType.EQUALS,
        "//li[normalize-space(text())='${nilaiOpsi}']")

    WebUI.waitForElementVisible(opsi, 10)
    WebUI.click(opsi)
}

pilihDropdown('Divisi', 'BATANAS')

// Step 5: Isi tanggal Start
def isiTanggalDenganSpinbutton(String label, String bulan, String tanggal, String tahun) {
    // Base XPath berdasarkan label field (misal: "Tanggal Lahir")
    String baseXPath = "//p[text()='${label}']/ancestor::div[contains(@class, 'MuiStack-root')]"

    // Set bulan
    TestObject bulanField = new TestObject()
    bulanField.addProperty('xpath', ConditionType.EQUALS, baseXPath + "//span[@aria-label='Month']")
    WebUI.click(bulanField)
    WebUI.sendKeys(bulanField, bulan) // contoh: "06"

    // Set tanggal
    TestObject tanggalField = new TestObject()
    tanggalField.addProperty('xpath', ConditionType.EQUALS, baseXPath + "//span[@aria-label='Day']")
    WebUI.click(tanggalField)
    WebUI.sendKeys(tanggalField, tanggal) // contoh: "19"

    // Set tahun
    TestObject tahunField = new TestObject()
    tahunField.addProperty('xpath', ConditionType.EQUALS, baseXPath + "//span[@aria-label='Year']")
    WebUI.click(tahunField)
    WebUI.sendKeys(tahunField, tahun) // contoh: "2025"
}

isiTanggalDenganSpinbutton("Tanggal Lahir", "06", "19", "2025")

pilihDropdown('Jenis Kelamin', 'Laki Laki')
isiTextFieldByLabel('Email', 'dimas@gmail.com')

// Step 12: Checklist Disclaimer
TestObject checkboxDisclaimer = new TestObject('checkboxDisclaimer')
checkboxDisclaimer.addProperty('xpath', ConditionType.EQUALS, "//input[@type='checkbox' and contains(@class, 'PrivateSwitchBase-input')]")
WebUI.click(checkboxDisclaimer)

// Step 13: Tutup browser
if (finalCloseBrowser) {
    WebUI.closeBrowser()
}
