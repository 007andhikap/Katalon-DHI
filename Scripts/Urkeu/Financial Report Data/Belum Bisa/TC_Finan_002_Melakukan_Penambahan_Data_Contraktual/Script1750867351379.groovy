import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.configuration.RunConfiguration

// Gunakan parameter jika disediakan, atau fallback ke default
boolean finalCloseBrowser = binding.hasVariable('closeBrowserAfter') ? closeBrowserAfter : true

// Step 1: Panggil test case dashboard
WebUI.callTestCase(
    findTestCase('Test Cases/Urkeu/Financial Report Data/TC_Finan_001_Menampilkan_Halaman_Data_Contraktual'),
    ['closeBrowserAfter': false],
    FailureHandling.STOP_ON_FAILURE
)

// Fungsi klik link berdasarkan href
def klikLinkBerdasarkanHref(String hrefPartial) {
    TestObject link = new TestObject()
    link.addProperty("xpath", ConditionType.EQUALS, "//a[contains(@href, '${hrefPartial}')]")
    WebUI.waitForElementClickable(link, 10)
    WebUI.click(link)
}

// Klik menu
klikLinkBerdasarkanHref("/administrasi-urkeu/financial-report-data")

// Verifikasi halaman muncul
TestObject Finan = new TestObject('Finan')
Finan.addProperty('xpath', ConditionType.EQUALS, "//p[text()='CONTRAKTUAL' and contains(@class, 'MuiTypography-body1')]")
WebUI.verifyElementPresent(Finan, 10)

// Klik tombol Tambah Data
klikLinkBerdasarkanHref("/financial-report-data/dokumen-contraktual")
WebUI.delay(3)

// Klik tombol pop-up
def klikTombolBerdasarkanTeks(String teksTombol) {
    TestObject tombol = new TestObject()
    tombol.addProperty("xpath", ConditionType.EQUALS, "//button[normalize-space(text())='${teksTombol}']")
    WebUI.waitForElementClickable(tombol, 10)
    WebUI.click(tombol)
}

klikTombolBerdasarkanTeks("Yes, Enable")

// Verifikasi halaman Kontraktual muncul
TestObject Kontraktual = new TestObject('Kontraktual')
Kontraktual.addProperty('xpath', ConditionType.EQUALS, "//p[text()='Kontraktual' and contains(@class, 'MuiTypography-body1')]")
WebUI.verifyElementPresent(Kontraktual, 10)

// Fungsi isi input field berdasarkan placeholder
def isiTextField(String placeholder, String value) {
    TestObject field = new TestObject()
    field.addProperty('xpath', ConditionType.EQUALS, "//input[@placeholder='${placeholder}']")
    WebUI.waitForElementVisible(field, 10)
    WebUI.setText(field, value)
}

isiTextField('Masukan Perihal', 'Rapat Dinas')
isiTextField('Masukan No Sprint', 'Rapat Dinas')

// Fungsi pilih dropdown berdasarkan label dan opsi
def pilihDropdownByLabel(String labelText, String teksOpsi) {
    String xpathDropdown = "//p[contains(text(),'${labelText}')]/following::div[@role='combobox'][1]"
    TestObject dropdown = new TestObject()
    dropdown.addProperty("xpath", ConditionType.EQUALS, xpathDropdown)

    WebUI.waitForElementClickable(dropdown, 10)
    WebUI.click(dropdown)

    String xpathOpsi = "//li[normalize-space(text())='${teksOpsi}']"
    TestObject opsi = new TestObject()
    opsi.addProperty("xpath", ConditionType.EQUALS, xpathOpsi)

    WebUI.waitForElementClickable(opsi, 5)
    WebUI.click(opsi)
}

pilihDropdownByLabel("Tahun", "2024")
isiTextField('Masukan nama rekanan', 'Rapat Dinas')

// Fungsi isi textarea berdasarkan label
def isiTextareaBerdasarkanLabel(String labelText, String isi) {
    String xpath = "//p[contains(text(), '${labelText}')]/following::textarea[1]"
    TestObject textarea = new TestObject()
    textarea.addProperty("xpath", ConditionType.EQUALS, xpath)

    WebUI.waitForElementVisible(textarea, 10)
    WebUI.setText(textarea, isi)
}

isiTextareaBerdasarkanLabel("Keterangan", "Ini adalah keterangan perjalanan dinas.")

// Fungsi upload dokumen berdasarkan label
def uploadDokumen(String namaDokumen, String pathFile) {
    String xpathInput = "//p[text()='${namaDokumen}']/following::input[@type='file'][1]"
    TestObject inputFile = new TestObject()
    inputFile.addProperty("xpath", ConditionType.EQUALS, xpathInput)

    WebUI.waitForElementPresent(inputFile, 10)
    WebUI.uploadFile(inputFile, pathFile)
}

String pathPDF = RunConfiguration.getProjectDir() + "/Data Files/dummy.pdf"
uploadDokumen("Surat Perintah", pathPDF)
uploadDokumen("Berita Acara", pathPDF)
uploadDokumen("Faktur", pathPDF)
uploadDokumen("Bukti Pembayaran", pathPDF)

// ✅ Fungsi diperbaiki: scroll + verifikasi teks fleksibel
def scrollKeElemenDenganTeks(String tagNama, String isiTeks) {
    String xpath = "//${tagNama}[contains(text(),'${isiTeks}')]"
    TestObject elemen = new TestObject()
    elemen.addProperty("xpath", ConditionType.EQUALS, xpath)

    WebUI.scrollToElement(elemen, 5)
    String actualText = WebUI.getText(elemen)
    assert actualText.contains(isiTeks) : "Teks tidak mengandung '${isiTeks}', tapi: '${actualText}'"
}

// Gunakan fungsi scroll yang sudah diperbaiki
scrollKeElemenDenganTeks("p", "Berita Acara")
WebUI.delay(3)

// Checklist Disclaimer
TestObject checkboxDisclaimer = new TestObject('checkboxDisclaimer')
checkboxDisclaimer.addProperty('xpath', ConditionType.EQUALS, "//input[@type='checkbox' and contains(@class, 'PrivateSwitchBase-input')]")
WebUI.click(checkboxDisclaimer)

// Tutup browser jika diperlukan
if (finalCloseBrowser) {
    WebUI.closeBrowser()
}
