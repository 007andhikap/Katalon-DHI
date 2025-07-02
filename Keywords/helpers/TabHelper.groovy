package helpers

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebElement

class TabHelper {

	/**
	 * Klik tab berdasarkan teks yang ditampilkan di dalam <p> pada tombol <button>
	 * @param teksTab Contoh: "NON KONTRAKTUAL", "CONTRAKTUAL"
	 */
	static void klikTabBerdasarkanTeks(String teksTab) {
		// Buat test object dinamis
		TestObject tab = new TestObject("tab_" + teksTab)
		tab.addProperty(
				"xpath",
				ConditionType.EQUALS,
				"//button[.//p[normalize-space(.)='${teksTab}']]"
				)

		WebUI.comment("Mencoba klik tab: '${teksTab}'")

		try {
			// Tunggu hingga elemen siap
			WebUI.waitForElementPresent(tab, 10)
			WebUI.waitForElementVisible(tab, 10)
			//			WebUI.scrollToElement(tab, 5)
			WebUI.waitForElementClickable(tab, 10)

			// Coba klik biasa
			try {
				WebUI.click(tab)
				WebUI.comment("Klik tab '${teksTab}' berhasil.")
			} catch (Exception e) {
				// Fallback dengan JavaScript
				WebElement el = WebUiCommonHelper.findWebElement(tab, 10)
				WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(el))
				WebUI.comment("Klik fallback menggunakan JavaScript untuk tab '${teksTab}'.")
			}
		} catch (Exception e) {
			WebUI.comment("Gagal menemukan atau klik tab '${teksTab}': " + e.message)
			throw e
		}
	}
}
