import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import org.openqa.selenium.WebElement
import java.util.Arrays


boolean finalCloseBrowser = binding.hasVariable('closeBrowserAfter') ? closeBrowserAfter : true

// Panggil Test Case pembuka halaman
WebUI.callTestCase(
    findTestCase('Test Cases/Calendar Digital/TC_Cal_001_Menampilkan_Halaman_Calendar'), 
    ['closeBrowserAfter': false], 
    FailureHandling.STOP_ON_FAILURE
)

// Klik tombol "Tambah Agenda"
TestObject btnTambahAgenda = new TestObject('btnTambahAgenda')
btnTambahAgenda.addProperty('xpath', ConditionType.EQUALS, "//button[contains(text(),'Tambah Agenda')]")
WebUI.waitForElementVisible(btnTambahAgenda, 10)
WebUI.click(btnTambahAgenda)

// Verifikasi form Create New Event muncul
TestObject pCreateEvent = new TestObject('pCreateEvent')
pCreateEvent.addProperty('xpath', ConditionType.EQUALS, "//h2[contains(text(), 'Create New Event')]")
WebUI.verifyElementPresent(pCreateEvent, 10)

// Fungsi isi field text berdasar placeholder
def isiTextField(String placeholder, String value) {
    TestObject field = new TestObject()
    field.addProperty('xpath', ConditionType.EQUALS, "//input[@placeholder='${placeholder}']")
    WebUI.waitForElementVisible(field, 10)
    WebUI.setText(field, value)
}

isiTextField('Masukan No Sprint', 'Rapat Dinas')

// Pilih dropdown kategori
TestObject ddlCategory = new TestObject()
ddlCategory.addProperty('xpath', ConditionType.EQUALS, "//div[@role='combobox' and @id='mui-component-select-type']")
WebUI.click(ddlCategory)

TestObject opsiCategory = new TestObject()
opsiCategory.addProperty('xpath', ConditionType.EQUALS, "//li[text()='Personal Calendar']")
WebUI.click(opsiCategory)

// Fungsi isi bagian waktu
def isiTanggalWaktu(String label, String bulan, String tanggal, String tahun, String jam, String menit, String ampm) {
    String baseXPath = "//p[text()='${label}']/following::div[contains(@class, 'MuiPickersSectionList-root')][1]"

    def isiField = { String ariaLabel, String value ->
        TestObject field = new TestObject()
        field.addProperty('xpath', ConditionType.EQUALS, "${baseXPath}//span[@aria-label='${ariaLabel}']")
        WebUI.waitForElementVisible(field, 5)
        try {
            WebUI.click(field)
        } catch (Exception e) {
            WebElement el = WebUiCommonHelper.findWebElement(field, 5)
            WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(el))
        }
        WebUI.sendKeys(field, value)
    }

    isiField("Month", bulan)
    isiField("Day", tanggal)
    isiField("Year", tahun)
    isiField("Hours", jam)
    isiField("Minutes", menit)
    isiField("Meridiem", ampm)
}

// Isi tanggal Start, End, dan Reminder
isiTanggalWaktu("Start Date", "06", "30", "2025", "10", "00", "AM")
isiTanggalWaktu("End Date", "06", "30", "2025", "11", "00", "AM")
isiTanggalWaktu("Reminder", "06", "30", "2025", "09", "45", "AM")

// Isi link meeting & lokasi
isiTextField("Enter link for Zoom, Google Meet, etc.", "https://meet.google.com")
isiTextField("Enter location, e.g. Meeting Room 2nd Floor", "Ruang Rapat A Lt 2")

// Lewati bagian Upload File

// Isi deskripsi
TestObject txtDescription = new TestObject()
txtDescription.addProperty('xpath', ConditionType.EQUALS, "//textarea[@placeholder='Notes, agenda, participants, or any other important details']")
WebUI.waitForElementVisible(txtDescription, 10)
WebUI.setText(txtDescription, "Pembahasan anggaran operasional dan rencana evaluasi triwulan")

// Klik tombol "Create Event"
TestObject btnCreate = new TestObject()
btnCreate.addProperty('xpath', ConditionType.EQUALS, "//button[.//span[text()='Create Event']]")
WebUI.waitForElementClickable(btnCreate, 10)
WebUI.click(btnCreate)

WebUI.delay(2)

// Tutup browser jika diatur
if (finalCloseBrowser) {
    WebUI.closeBrowser()
}
