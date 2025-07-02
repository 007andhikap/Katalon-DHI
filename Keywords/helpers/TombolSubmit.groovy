package helpers

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class TombolHelper {

    static void klikTombolDenganTeks(String teksTombol) {
        String xpath = "//button[normalize-space()='${teksTombol}']"
        TestObject tombol = new TestObject("tombolByText")
        tombol.addProperty("xpath", ConditionType.EQUALS, xpath)

        WebUI.waitForElementClickable(tombol, 10)
        WebUI.click(tombol)
    }
}
