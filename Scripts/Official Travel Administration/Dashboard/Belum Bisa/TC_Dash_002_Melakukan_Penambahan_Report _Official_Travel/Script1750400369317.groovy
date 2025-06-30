import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.configuration.RunConfiguration
import org.openqa.selenium.WebElement
import java.util.Arrays

// Gunakan parameter jika disediakan, atau fallback ke default
boolean finalCloseBrowser = binding.hasVariable('closeBrowserAfter') ? closeBrowserAfter : true

// Step 1: Panggil test case untuk menampilkan halaman Regulation & Information
WebUI.callTestCase(
    findTestCase('Test Cases/Official Travel Administration/Dashboard/TC_Dash_001_Menampilkan_Halaman_Official_Travel_Administration'), 
    [
        'closeBrowserAfter': false
    ], 
    FailureHandling.STOP_ON_FAILURE
)

// Step 2: Klik tombol "Tambah Data" menggunakan XPath dinamis
TestObject btnTambah = new TestObject('btnTambahData')
btnTambah.addProperty('xpath', 
    ConditionType.EQUALS, 
    "//a[@href='/perjalanan-dinas/create']//button[normalize-space(text())='Official Travel']")

WebUI.waitForElementVisible(btnTambah, 10)
WebUI.waitForElementClickable(btnTambah, 10)

try {
    WebUI.click(btnTambah)
} catch (Exception e) {
    WebElement el = WebUiCommonHelper.findWebElement(btnTambah, 10)
    WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(el))
}

// Step 3: Verifikasi form "Media Upload" muncul
TestObject pTambahData = new TestObject('pTambahData')
pTambahData.addProperty('xpath', 
    ConditionType.EQUALS, 
    "//p[text()='OFFICIAL TRAVEL REPORT' and contains(@class, 'MuiTypography-body1')]")

WebUI.waitForElementVisible(pTambahData, 10)
WebUI.verifyElementPresent(pTambahData, 10)

//input field
// Fungsi isi field text berdasar placeholder
def isiTextField(String placeholder, String value) {
	TestObject field = new TestObject()
	field.addProperty('xpath', ConditionType.EQUALS, "//input[@placeholder='${placeholder}']")
	WebUI.waitForElementVisible(field, 10)
	WebUI.setText(field, value)
}

// Isi judul event
isiTextField('Masukan Nama Kegiatan', 'Rapat Dinas')
isiTextField('Masukan No Sprint', 'Rapat Dinas')
isiTextField('Masukan Ketua Delegasi', 'Rapat Dinas')
isiTextField('Masukan Delegasi Pendamping', 'Rapat Dinas')
isiTextField('Masukan Jabatan Ketua Delegasi', 'Rapat Dinas')
isiTextField('Masukan Jumlah Rombongan', 'Rapat Dinas')

//// Tunggu sampai elemen input tanggal tersedia
//TestObject inputTanggal = findTestObject('Input_TanggalBerangkat')
//WebUI.waitForElementPresent(inputTanggal, 10)
//
//// Dapatkan ID elemen input (diperlukan agar bisa remove "disabled")
//String inputId = WebUI.getAttribute(inputTanggal, 'id')
//
//// Eksekusi JavaScript untuk hapus atribut disabled
//String script = "document.getElementById('${inputId}').removeAttribute('disabled');"
//WebUI.executeJavaScript(script, null)
//
//// Setelah elemen aktif, isi tanggal
//WebUI.setText(inputTanggal, '07/01/2025')
//
//// (Optional) Validasi isi field
//String actualValue = WebUI.getAttribute(inputTanggal, 'value')
//WebUI.verifyMatch(actualValue, '07/01/2025', false)

/**
 * Fungsi pilih dropdown Material UI berdasarkan urutan
 * @param urutanDropdown -> urutan ke berapa dropdown ke-n (1-based index)
 * @param teksOpsi -> teks opsi yang ingin dipilih (misal: 'MALAYSIA')
 */
def pilihDropdownMUIKe(int urutanDropdown, String teksOpsi) {
    // Ambil dropdown ke-n berdasarkan urutan
    String dropdownXPath = "(//div[@role='combobox' and contains(@class,'MuiSelect-select')])[${urutanDropdown}]"
    
    TestObject ddl = new TestObject()
    ddl.addProperty("xpath", ConditionType.EQUALS, dropdownXPath)
    
    WebUI.waitForElementClickable(ddl, 10)
    WebUI.click(ddl)
    
    // Pilih opsi berdasarkan teks
    TestObject opsi = new TestObject()
    opsi.addProperty("xpath", ConditionType.EQUALS, "//li[contains(text(), '${teksOpsi}')]")
    
    WebUI.waitForElementVisible(opsi, 5)
    WebUI.click(opsi)
}

// Klik dropdown negara ke-1 dan pilih 'MALAYSIA'
pilihDropdownMUIKe(1, "MALAYSIA")

// Klik dropdown kategori ke-2 dan pilih 'Meeting'
pilihDropdownMUIKe(2, "INDONESIA")

// Klik dropdown jenis cuti ke-3 dan pilih 'Cuti Tahunan'
pilihDropdownMUIKe(3, "Laki - Laki")

// Klik dropdown jenis cuti ke-3 dan pilih 'Cuti Tahunan'
pilihDropdownMUIKe(4, "Laki - Laki")

/**
 * Mengisi textarea berdasarkan label judul sebelum textarea (seperti 'Deskripsi Singkat Permohonan')
 * @param labelText -> teks label/p (misalnya: 'Deskripsi Singkat Permohonan')
 * @param isi -> isi yang akan dituliskan ke dalam textarea
 */
def isiTextareaDenganLabel(String labelText, String isi) {
	String xpathTextarea = "//p[contains(text(), '${labelText}')]/following::textarea[1]"
	TestObject txtArea = new TestObject()
	txtArea.addProperty("xpath", ConditionType.EQUALS, xpathTextarea)

	WebUI.waitForElementVisible(txtArea, 10)
	WebUI.setText(txtArea, isi)
}

// Isi textarea untuk Deskripsi Singkat Permohonan
isiTextareaDenganLabel("Deskripsi Singkat Permohonan", "Ini adalah pengajuan perjalanan dinas untuk menghadiri konferensi di luar kota.")
isiTextareaDenganLabel("Tujuan Kegiatan", "Perjalanan dinas untuk menghadiri konferensi di luar kota.")

// Step 12: Checklist Disclaimer
TestObject checkboxDisclaimer = new TestObject('checkboxDisclaimer')
checkboxDisclaimer.addProperty('xpath', ConditionType.EQUALS, "//input[@type='checkbox' and contains(@class, 'PrivateSwitchBase-input')]")
WebUI.click(checkboxDisclaimer)

/**
 * Scroll ke elemen berdasarkan tag dan teks yang persis.
 * Cocok untuk <p>, <h1>, <div>, dll.
 *
 * @param tagNama    -> nama tag, misal: "p", "h1", "div"
 * @param isiTeks    -> isi teks persis yang dicari
 */
def scrollKeElemenDenganTeks(String tagNama, String isiTeks) {
	String xpath = "//${tagNama}[text()='${isiTeks}']"
	TestObject elemen = new TestObject()
	elemen.addProperty("xpath", ConditionType.EQUALS, xpath)
	
	WebUI.scrollToElement(elemen, 5)
	WebUI.verifyElementText(elemen, isiTeks)
}

// Scroll ke <p> yang berisi teks 'OFFICIAL TRAVEL REPORT'
scrollKeElemenDenganTeks("p", "OFFICIAL TRAVEL REPORT")


def klikTab(String namaTab) {
	String xpath = "//button[@role='tab']//p[text()='${namaTab}']/ancestor::button"
	TestObject tab = new TestObject()
	tab.addProperty("xpath", ConditionType.EQUALS, xpath)

	WebUI.waitForElementClickable(tab, 5)
	WebUI.click(tab)
}

klikTab("Financial Accountability")

/**
 * Upload dokumen berdasarkan nama label seperti 'Tiket Pesawat', 'Tiket Hotel', dll.
 * @param namaDokumen -> Label dokumen seperti 'Tiket Pesawat'
 * @param pathFile -> Path lengkap file yang akan di-upload
 */
def uploadDokumen(String namaDokumen, String pathFile) {
	// XPath ke <input type="file"> yang berada di bawah label dokumen
	String xpathInput = "//p[text()='${namaDokumen}']/following::input[@type='file'][1]"
	
	TestObject inputFile = new TestObject()
	inputFile.addProperty("xpath", ConditionType.EQUALS, xpathInput)
	
	WebUI.waitForElementPresent(inputFile, 10)
	WebUI.uploadFile(inputFile, pathFile)
}

String pathPDF = RunConfiguration.getProjectDir() + "/Data Files/dummy.pdf"
uploadDokumen("Tiket Pesawat", pathPDF)
uploadDokumen("Tiket Hotel", pathPDF)
uploadDokumen("Dokumen Lainnya", pathPDF)


// Step 4: Tutup browser jika perlu
if (finalCloseBrowser) {
    WebUI.closeBrowser()
}
