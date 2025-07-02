package helpers

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

class Konfirmasi {

	static void TombolNotifikasi(String teksTombol) {
		TestObject tombol = new TestObject()
		tombol.addProperty("xpath", ConditionType.EQUALS, "//button[normalize-space(text())='${teksTombol}']")

		WebUI.waitForElementClickable(tombol, 10)
		WebUI.click(tombol)
	}
}

