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
    findTestCase('Test Cases/Visitor Management/Dashboard/TC_Dash_001_Menampilkan_Halaman_Visitor_Attendance'),
    ['closeBrowserAfter': false],
    FailureHandling.STOP_ON_FAILURE
)

// Step 2: Klik tombol Visitor Registration
TestObject btnApplication = new TestObject('btnRegis')
btnApplication.addProperty('xpath', ConditionType.EQUALS, "//button[normalize-space(text())='Application Letter of Support']")
WebUI.waitForElementClickable(btnApplication, 10)
try {
    WebUI.click(btnApplication)
} catch (Exception e) {
    WebElement el = WebUiCommonHelper.findWebElement(btnApplication, 10)
    WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(el))
}

// Step 3: Verifikasi form
TestObject pTambahData = new TestObject('pTambahData')
pTambahData.addProperty('xpath', ConditionType.EQUALS, "//p[contains(@class, 'MuiTypography-body1') and text()='Application Letter Of Support Form']")
WebUI.verifyElementPresent(pTambahData, 10)

// Step 4: Isi field teks
def isiTextField(String placeholder, String value) {
    TestObject inputField = new TestObject()
    inputField.addProperty('xpath', ConditionType.EQUALS, "//input[@placeholder='${placeholder}']")
    WebUI.waitForElementVisible(inputField, 10)
    WebUI.setText(inputField, value)
}

isiTextField('Masukan Nama Perseta', 'Dimas')
isiTextField('Masukan Tujuan', 'Amerika')

// Step Tambahan: Pilih Negara Asal dari dropdown
def pilihDropdown(String label, String nilaiPilihan) {
    TestObject dropdown = new TestObject("dropdown_" + label)
    dropdown.addProperty('xpath', ConditionType.EQUALS, "//p[contains(text(),'${label}')]/following-sibling::div//div[@role='combobox']")
    WebUI.waitForElementVisible(dropdown, 10)
    WebUI.click(dropdown)

    TestObject opsi = new TestObject("opsi_" + nilaiPilihan)
    opsi.addProperty('xpath', ConditionType.EQUALS, "//li[normalize-space(text())='${nilaiPilihan}']")
    WebUI.waitForElementVisible(opsi, 10)
    WebUI.click(opsi)
}

pilihDropdown('Negara Asal', 'INDONESIA')
pilihDropdown('Negara Tujuan', 'PHILIPINA')
pilihDropdown('Jenis Permohonan', 'MERP (Multiple Exit Re-Entry Permit)')

// Step 7: Isi Deskripsi
TestObject inputDeskripsi = new TestObject('inputDeskripsi')
inputDeskripsi.addProperty('xpath', ConditionType.EQUALS, "//p[contains(text(),'Deskripsi Singkat Permohonan')]/following::textarea[1]")
WebUI.setText(inputDeskripsi, "Surat Undangan Dinas Luar Negri")

// Step 9: Scroll ke bagian Upload Dokumen
TestObject labelUploadDokumen = new TestObject("labelUploadDokumen")
labelUploadDokumen.addProperty("xpath", ConditionType.EQUALS, "//p[contains(text(),'Passpor')]")
WebElement uploadLabelElement = WebUiCommonHelper.findWebElement(labelUploadDokumen, 10)
WebUI.executeJavaScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", Arrays.asList(uploadLabelElement))
WebUI.delay(1)

// Step 10: Fungsi Upload
def uploadDokumen(String namaInput, String pathFile) {
    File file = new File(pathFile)
    assert file.exists() : "❌ File tidak ditemukan: ${pathFile}"
    
    TestObject fileInput = new TestObject("fileUpload_" + namaInput)
    fileInput.addProperty("xpath", ConditionType.EQUALS, "//input[@type='file' and @name='${namaInput}']")
    WebUI.waitForElementVisible(fileInput, 10)
    WebUI.uploadFile(fileInput, file.getAbsolutePath())

    // Verifikasi nama file
    TestObject uploadedFileLabel = new TestObject("uploadedFile_" + namaInput)
    uploadedFileLabel.addProperty("xpath", ConditionType.EQUALS, "//*[text()='${file.getName()}']")
    WebUI.verifyElementPresent(uploadedFileLabel, 10)
    WebUI.comment("✅ File '${file.getName()}' berhasil diupload pada '${namaInput}'")
}

// Step 11: Upload file
String pathPDF = RunConfiguration.getProjectDir() + "/Data Files/dummy.pdf"
uploadDokumen("letter_of_support", pathPDF)
uploadDokumen("Passpor", pathPDF)

// Step 12: Checklist Disclaimer
TestObject checkboxDisclaimer = new TestObject('checkboxDisclaimer')
checkboxDisclaimer.addProperty('xpath', ConditionType.EQUALS, "//input[@type='checkbox' and contains(@class, 'PrivateSwitchBase-input')]")
WebUI.click(checkboxDisclaimer)

// Step 13: Tutup browser
if (finalCloseBrowser) {
    WebUI.closeBrowser()
}
