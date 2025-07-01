import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.WebElement

// Gunakan parameter jika disediakan, atau fallback ke default
boolean finalCloseBrowser = binding.hasVariable('closeBrowserAfter') ? closeBrowserAfter : true

// Step 1: Panggil test case dashboard
WebUI.callTestCase(
    findTestCase('Test Cases/Urkeu/Financial Report Data/TC_Finan_001_Menampilkan_Halaman_Data_Contraktual'), 
    [
        'closeBrowserAfter': false
    ], 
    FailureHandling.STOP_ON_FAILURE
)

//pilih menu berdasarkan href
def klikLinkBerdasarkanHref(String hrefPartial) {
    TestObject link = new TestObject()
    link.addProperty("xpath", ConditionType.EQUALS, "//a[contains(@href, '${hrefPartial}')]")
    WebUI.waitForElementClickable(link, 10)
    WebUI.click(link)
}

klikLinkBerdasarkanHref("/administrasi-urkeu/financial-report-data")

TestObject dinasDetailLink = new TestObject('dinasDetailLink')
dinasDetailLink.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "//table//tr[1]//a[contains(text(), 'Detail')]"
)

WebUI.waitForElementPresent(dinasDetailLink, 10)
WebUI.delay(1)
WebUI.scrollToElement(dinasDetailLink, 2)

WebElement el = WebUiCommonHelper.findWebElement(dinasDetailLink, 10)
WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(el))
WebUI.comment("Klik berhasil menggunakan JavaScript.")

WebUI.delay(5)

// Buat TestObject dinamis (Konfirmasi yes enable)
def yesEnableButton = new TestObject('YesEnableButton')
yesEnableButton.addProperty(
	'xpath',
	ConditionType.EQUALS,
	"//button[normalize-space()='Yes, Enable']"
)

// Tunggu dan klik
WebUI.waitForElementVisible(yesEnableButton, 10)
WebUI.waitForElementClickable(yesEnableButton, 10)
WebUI.scrollToElement(yesEnableButton, 2)
WebUI.click(yesEnableButton)

// Fungsi untuk membuat TestObject teks berdasarkan teks <p>
def buatTextElement(String isiTeks) {
	def testObj = new TestObject("text_${isiTeks}")
	testObj.addProperty(
		"xpath",
		ConditionType.EQUALS,
		"//p[normalize-space()='${isiTeks}']"
	)
	return testObj
}

// Contoh penggunaan:
def detailContractualText = buatTextElement("Detail Contractual")

WebUI.waitForElementVisible(detailContractualText, 10)
WebUI.verifyElementPresent(detailContractualText, 10)

// Step 5: Tutup browser jika diinstruksikan
if (finalCloseBrowser) {
    WebUI.closeBrowser()
}
