package helpers

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

class VerifikasiTeks {

    static TestObject buatTextElement(String isiTeks) {
        TestObject testObj = new TestObject("text_${isiTeks}")
        testObj.addProperty(
            "xpath",
            ConditionType.EQUALS,
            // cari di elemen apapun yang berisi teks sesuai normalize-space
            "//*[normalize-space(text())='${isiTeks}']"
        )
        return testObj
    }

    static void verifyTextElementVisible(String isiTeks, int timeout = 10) {
        TestObject obj = buatTextElement(isiTeks)
        WebUI.waitForElementVisible(obj, timeout)
        WebUI.verifyElementPresent(obj, timeout)
        WebUI.comment("Teks '${isiTeks}' berhasil diverifikasi.")
    }
}
