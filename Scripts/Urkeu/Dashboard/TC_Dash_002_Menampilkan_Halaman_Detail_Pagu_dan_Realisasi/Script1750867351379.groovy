import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling


boolean finalCloseBrowser = binding.hasVariable('closeBrowserAfter') ? closeBrowserAfter : true

// Step 1: Buka halaman Visitor Data
WebUI.callTestCase(
    findTestCase('Test Cases/Urkeu/Dashboard/TC_Dash_001_Menampilkan_Halaman_Dashboard_URKEU'),
    ['closeBrowserAfter': false],
    FailureHandling.STOP_ON_FAILURE
)

def klikLinkBerdasarkanHref(String hrefPartial) {
    TestObject link = new TestObject()
    link.addProperty("xpath", ConditionType.EQUALS, "//a[contains(@href, '${hrefPartial}')]")
    WebUI.waitForElementClickable(link, 10)
    WebUI.click(link)
}

klikLinkBerdasarkanHref("/dashboard/detail")

TestObject DetailPagu = new TestObject('DetailPagu')
DetailPagu.addProperty('xpath',
	ConditionType.EQUALS,
	"//p[text()='Detail Informasi' and contains(@class, 'MuiTypography-body1')]")

// Verifikasi bahwa elemen muncul
WebUI.verifyElementPresent(DetailPagu, 10)

// Step 6: Tutup browser jika diinstruksikan
if (finalCloseBrowser) {
    WebUI.closeBrowser()
}
