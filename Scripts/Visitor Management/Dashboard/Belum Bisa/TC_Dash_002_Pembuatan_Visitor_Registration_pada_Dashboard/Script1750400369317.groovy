import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import org.openqa.selenium.WebElement
import com.kms.katalon.core.configuration.RunConfiguration
import java.util.Arrays
import helpers.LinkHelper
import helpers.Konfirmasi
import helpers.InputHelper
import helpers.DropdownHelper
import helpers.TextAreaLabel
import helpers.UploadDocHelper
import helpers.Scrollhelper
import helpers.VerifikasiTeks
import helpers.IsiFormTanggal

boolean finalCloseBrowser = binding.hasVariable('closeBrowserAfter') ? closeBrowserAfter : true

// Step 1: Buka dashboard
WebUI.callTestCase(
    findTestCase('Test Cases/Visitor Management/Dashboard/TC_Dash_001_Menampilkan_Halaman_Visitor_Attendance'),
    ['closeBrowserAfter': false],
    FailureHandling.STOP_ON_FAILURE
)

// Klik tombol Tambah Data
LinkHelper.TombolHref("/kunjungan-tamu/visitor-data/create")
WebUI.delay(5)

// Step 3: Verifikasi halaman muncul
VerifikasiTeks.verifyTextElementVisible("Visitor Registration Form")

// Step 4: Isi field teks
InputHelper.isiTextField('Masukan Asal Delegasi', 'Dinas Kominfo')
InputHelper.isiTextField('Masukan Tujuan', 'Amerika')
InputHelper.isiTextField('Masukan Jabatan', 'Sesdit')
InputHelper.isiTextField('Masukan Status Keanggotaan', 'Sesdit')
InputHelper.isiTextField('Masukan nama pejabat yang menerima', 'Samsudin')

// Untuk input pertama (Start Date)
IsiFormTanggal.isiDate("Waktu Kunjungan", "07", "02", "2025", 1)

// Untuk input kedua (End Date)
IsiFormTanggal.isiDate("Waktu Kunjungan", "07", "05", "2025", 2)

// Fungsi isi textarea berdasarkan label
TextAreaLabel.isiTextareaBerdasarkanLabel("Perihal", "Ini adalah keterangan perjalanan dinas.")

// Step 8: Contact Person
InputHelper.isiTextField('Masukan Nama', 'Dedi Mulyadi')
InputHelper.isiTextField('Masukan No Telp', '081723782382')

// ✅ Fungsi diperbaiki: scroll + verifikasi teks fleksibel
Scrollhelper.scrollKeElemenDenganTeks("p", "Upload Dokumen")
WebUI.delay(3)


// Fungsi upload dokumen berdasarkan label
String pathPDF = RunConfiguration.getProjectDir() + "/Data Files/dummy.pdf"
UploadDocHelper.uploadDokumen("Surat Tugas", pathPDF)
UploadDocHelper.uploadDokumen("Id Card / Passpor", pathPDF)


// Step 12: Checklist Disclaimer
TestObject checkboxDisclaimer = new TestObject('checkboxDisclaimer')
checkboxDisclaimer.addProperty('xpath', ConditionType.EQUALS, "//input[@type='checkbox' and contains(@class, 'PrivateSwitchBase-input')]")
WebUI.click(checkboxDisclaimer)

// Step 13: Tutup browser
if (finalCloseBrowser) {
    WebUI.closeBrowser()
}
