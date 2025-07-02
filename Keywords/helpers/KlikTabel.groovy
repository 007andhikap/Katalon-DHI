package helpers

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebElement

class KlikTabel {

	static void klikTombolDenganTeksPadaBaris(int baris, String teks) {
		// XPath-nya mencari semua button atau link dengan teks, pada baris ke-n
		String xpath = "//table//tbody/tr[${baris}]//button[contains(., '${teks}')] | //table//tbody/tr[${baris}]//a[contains(., '${teks}')]"

		TestObject tombol = new TestObject("tombol_baris${baris}_${teks}")
		tombol.addProperty("xpath", ConditionType.EQUALS, xpath)

		WebUI.waitForElementPresent(tombol, 10)
		WebUI.scrollToElement(tombol, 2)

		WebElement el = WebUiCommonHelper.findWebElement(tombol, 10)
		WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(el))

		WebUI.comment("Berhasil klik '${teks}' pada baris ke-${baris}")
		WebUI.delay(2)
	}
}
