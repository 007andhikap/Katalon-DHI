import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.WebElement
import helpers.LinkHelper
import helpers.Konfirmasi
import helpers.InputHelper
import helpers.DropdownHelper
import helpers.TextAreaLabel
import helpers.UploadDocHelper
import helpers.Scrollhelper
import helpers.KlikTabel
import helpers.VerifikasiTeks

// Gunakan parameter jika disediakan, atau fallback ke default
boolean finalCloseBrowser = binding.hasVariable('closeBrowserAfter') ? closeBrowserAfter : true

// Step 1: Panggil test case dashboard
WebUI.callTestCase(
    findTestCase('Test Cases/Urkeu/Financial Report Data/Non Contractual/TC_Finan_004_Menampilkan_Halaman_Data_Non_Contraktual'), 
    [
        'closeBrowserAfter': false
    ], 
    FailureHandling.STOP_ON_FAILURE
)

// Klik pada baris ke-1, yang ada teks "Detail"
KlikTabel.klikTombolDenganTeksPadaBaris(1,"Detail")

// Buat TestObject dinamis (Konfirmasi yes enable)
Konfirmasi.TombolNotifikasi("Yes, Enable")

// Fungsi untuk membuat TestObject teks berdasarkan teks <p>
// Verifikasi teks muncul
VerifikasiTeks.verifyTextElementVisible("Detail Non Contractual")

// Step 5: Tutup browser jika diinstruksikan
if (finalCloseBrowser) {
    WebUI.closeBrowser()
}
