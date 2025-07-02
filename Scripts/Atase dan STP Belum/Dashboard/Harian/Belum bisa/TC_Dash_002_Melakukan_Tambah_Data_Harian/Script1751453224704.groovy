import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
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
    findTestCase('Test Cases/Atase dan STP Belum/Dashboard/TC_Dash_001_Menampilkan_Halaman_Dashboard_URKEU'), 
    [
        'closeBrowserAfter': false
    ], 
    FailureHandling.STOP_ON_FAILURE
)

LinkHelper.TombolHref("atpol-dan-stp/laporan-harian")
WebUI.delay(3)

// Step 3: Verifikasi halaman muncul
VerifikasiTeks.verifyTextElementVisible("LAPORAN HARIAN KEGIATAN")

//input
InputHelper.isiTextField('masukan nama kegiatan', 'Rapat Dinas')



// Step 4: Tutup browser
if (finalCloseBrowser) {
	WebUI.closeBrowser()
}