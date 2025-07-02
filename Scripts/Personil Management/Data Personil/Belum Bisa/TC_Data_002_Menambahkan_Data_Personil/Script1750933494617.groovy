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
import helpers.TombolHelper

boolean finalCloseBrowser = binding.hasVariable('closeBrowserAfter') ? closeBrowserAfter : true

// Step 1: Buka dashboard
WebUI.callTestCase(
    findTestCase('Test Cases/Personil Management/Data Personil/TC_Data_001_Menampilkan_Halaman_Data_Personel'),
    ['closeBrowserAfter': false],
    FailureHandling.STOP_ON_FAILURE
)

// Klik tombol Tambah Data
LinkHelper.TombolHref("/manajemen-personel/data-personel/create")
WebUI.delay(5)

// Verifikasi halaman
VerifikasiTeks.verifyTextElementVisible("Tambah Data Personil")

// Step 4: Isi field teks
InputHelper.isiTextFieldDenganLabel('Nama Lengkap', 'dimas1 (Dummy)')
InputHelper.isiTextFieldDenganLabel('NRP / NIP', '12345667')

// Step Tambahan: Pilihan dari dropdown
DropdownHelper.pilihDropdownByLabel("Divisi", "Bagian Renmin")

// Step Tambahan: Pilihan dari dropdown
DropdownHelper.pilihDropdownByLabel("Sub Divisi", "Sub Bagian SDM")

// Step Tambahan: Pilihan dari dropdown
DropdownHelper.pilihDropdownByLabel("Pangkat", "IPTU")

//Isi tanggal Start
IsiFormTanggal.isiDate("Tanggal Lahir", "07", "02", "1990", 1)


// Step Tambahan: Pilihan dari dropdown
DropdownHelper.pilihDropdownByLabel("Jenis Kelamin", "Laki Laki")

// Step 4: Isi field teks
InputHelper.isiTextFieldDenganLabel('Email', 'dimas1@gmail.com')

// Step 12: Checklist Disclaimer
TestObject checkboxDisclaimer = new TestObject('checkboxDisclaimer')
checkboxDisclaimer.addProperty('xpath', ConditionType.EQUALS, "//input[@type='checkbox' and contains(@class, 'PrivateSwitchBase-input')]")
WebUI.click(checkboxDisclaimer)

// Klik tombol Create Data
TombolHelper.klikTombolDenganTeks("Create Data")

//delay
WebUI.delay(2)

// Step 3: Verifikasi halaman muncul
VerifikasiTeks.verifyTextElementVisible("Personel Baru berhasil dibuat.")
// Step 13: Tutup browser
if (finalCloseBrowser) {
    WebUI.closeBrowser()
}
