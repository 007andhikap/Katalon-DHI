import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.configuration.RunConfiguration
import helpers.LinkHelper
import helpers.Konfirmasi
import helpers.InputHelper
import helpers.DropdownHelper
import helpers.TextAreaLabel
import helpers.UploadDocHelper
import helpers.Scrollhelper
import helpers.VerifikasiTeks

// Gunakan parameter jika disediakan, atau fallback ke default
boolean finalCloseBrowser = binding.hasVariable('closeBrowserAfter') ? closeBrowserAfter : true

// Step 1: Panggil test case dashboard
WebUI.callTestCase(
    findTestCase('Test Cases/Urkeu/Financial Report Data/Non Contractual/TC_Finan_004_Menampilkan_Halaman_Data_Non_Contraktual'),
    ['closeBrowserAfter': false],
    FailureHandling.STOP_ON_FAILURE
)

// Klik tombol Tambah Data
LinkHelper.TombolHref("/financial-report-data/dokumen-noncontraktual")
WebUI.delay(3)

Konfirmasi.TombolNotifikasi("Yes, Enable")

// Verifikasi halaman Kontraktual muncul
VerifikasiTeks.verifyTextElementVisible("Non Kontraktual")

InputHelper.isiTextField('Masukan Perihal', 'Rapat Dinas')
InputHelper.isiTextField('Masukan No Sprint', 'Rapat Dinas')
InputHelper.isiTextField('Masukan Jumlah Nilai', '100')


DropdownHelper.pilihDropdownByLabel("Tahun", "2025")
InputHelper.isiTextField('Masukan nama rekanan', 'Rapat Dinas')

// Fungsi isi textarea berdasarkan label
TextAreaLabel.isiTextareaBerdasarkanLabel("Keterangan", "Ini adalah keterangan perjalanan dinas.")

// Fungsi upload dokumen berdasarkan label
String pathPDF = RunConfiguration.getProjectDir() + "/Data Files/dummy.pdf"
UploadDocHelper.uploadDokumen("Surat Perintah", pathPDF)
UploadDocHelper.uploadDokumen("Berita Acara", pathPDF)
UploadDocHelper.uploadDokumen("Faktur", pathPDF)
UploadDocHelper.uploadDokumen("Bukti Pembayaran", pathPDF)

// ✅ Fungsi diperbaiki: scroll + verifikasi teks fleksibel
Scrollhelper.scrollKeElemenDenganTeks("p", "Berita Acara")
WebUI.delay(3)

// Checklist Disclaimer
TestObject checkboxDisclaimer = new TestObject('checkboxDisclaimer')
checkboxDisclaimer.addProperty('xpath', ConditionType.EQUALS, "//input[@type='checkbox' and contains(@class, 'PrivateSwitchBase-input')]")
WebUI.click(checkboxDisclaimer)

// Tutup browser jika diperlukan
if (finalCloseBrowser) {
    WebUI.closeBrowser()
}
