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
    findTestCase('Test Cases/Visitor Management/Visitor Data/TC_VisitD_001_Menampilkan_Halaman_Visitor_Data'),
    ['closeBrowserAfter': false],
    FailureHandling.STOP_ON_FAILURE
)

// Step 2: Klik tombol Visitor Registration
TestObject btnVisitorRegis = new TestObject('btnVisitorRegis')
btnVisitorRegis.addProperty('xpath', ConditionType.EQUALS, "//button[.//p[text()='Visitor Registration']]")
WebUI.click(btnVisitorRegis)

// Step 3: Verifikasi form
TestObject pTambahData = new TestObject('pTambahData')
pTambahData.addProperty('xpath', ConditionType.EQUALS, "//p[contains(@class, 'MuiTypography-body1') and text()='Visitor Registration Form']")
WebUI.verifyElementPresent(pTambahData, 10)

// Step 4: Isi field teks
def isiTextField(String placeholder, String value) {
    TestObject inputField = new TestObject()
    inputField.addProperty('xpath', ConditionType.EQUALS, "//input[@placeholder='${placeholder}']")
    WebUI.waitForElementVisible(inputField, 10)
    WebUI.setText(inputField, value)
}

isiTextField('Masukan Asal Delegasi', 'Dinas Kominfo')
isiTextField('Masukan Tujuan', 'Amerika')
isiTextField('Masukan Jabatan', 'Sesdit')
isiTextField('Masukan Status Keanggotaan', 'Sesdit')
isiTextField('Masukan nama pejabat yang menerima', 'Samsudin')

// Step 5: Isi tanggal Start
TestObject inputTanggal1 = new TestObject('inputTanggalStart')
inputTanggal1.addProperty('xpath', ConditionType.EQUALS, "(//input[contains(@class, 'MuiPickersInputBase-input')])[1]")
WebElement tanggalElement1 = WebUiCommonHelper.findWebElement(inputTanggal1, 10)
WebUI.executeJavaScript("arguments[0].value='07/30/2025'; arguments[0].dispatchEvent(new Event('input'));", Arrays.asList(tanggalElement1))
assert WebUI.getAttribute(inputTanggal1, 'value') == "07/30/2025"

// Step 6: Isi tanggal End
TestObject inputTanggal2 = new TestObject('inputTanggalEnd')
inputTanggal2.addProperty('xpath', ConditionType.EQUALS, "(//input[contains(@class, 'MuiPickersInputBase-input')])[2]")
WebElement tanggalElement2 = WebUiCommonHelper.findWebElement(inputTanggal2, 10)
WebUI.executeJavaScript("arguments[0].value='08/25/2025'; arguments[0].dispatchEvent(new Event('input'));", Arrays.asList(tanggalElement2))
assert WebUI.getAttribute(inputTanggal2, 'value') == "08/25/2025"

// Step 7: Isi Perihal
TestObject inputPerihal = new TestObject('inputPerihal')
inputPerihal.addProperty('xpath', ConditionType.EQUALS, "//p[contains(text(),'Perihal')]/following::textarea[1]")
WebUI.setText(inputPerihal, "Surat Undangan Dinas Luar Kota")

// Step 8: Contact Person
isiTextField('Masukan Nama', 'Dedi Mulyadi')
isiTextField('Masukan No Telp', '081723782382')

// Step 9: Scroll ke bagian Upload Dokumen
TestObject labelUploadDokumen = new TestObject("labelUploadDokumen")
labelUploadDokumen.addProperty("xpath", ConditionType.EQUALS, "//p[contains(text(),'Id Card / Passpor')]")
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
uploadDokumen("surat_tugas", pathPDF)
uploadDokumen("idcard", pathPDF)

// Step 12: Checklist Disclaimer
TestObject checkboxDisclaimer = new TestObject('checkboxDisclaimer')
checkboxDisclaimer.addProperty('xpath', ConditionType.EQUALS, "//input[@type='checkbox' and contains(@class, 'PrivateSwitchBase-input')]")
WebUI.click(checkboxDisclaimer)

// Step 13: Tutup browser
if (finalCloseBrowser) {
    WebUI.closeBrowser()
}
