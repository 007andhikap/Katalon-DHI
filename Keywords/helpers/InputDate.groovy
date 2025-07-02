package helpers

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

class IsiFormTanggal {

    static void isiDate(String label, String bulan, String tanggal, String tahun, int indexKe = 1) {
        // Cari div yang mengandung label, lalu cari nth-form tanggal berdasarkan urutan div
        String baseXPath = "(//*[normalize-space(text())='${label}']/following::div[contains(@class, 'MuiFormControl-root')])[${indexKe}]"

        // Bulan
        TestObject bulanField = new TestObject("bulan_${label}_${indexKe}")
        bulanField.addProperty("xpath", ConditionType.EQUALS, baseXPath + "//span[@aria-label='Month']")
        WebUI.click(bulanField)
        WebUI.sendKeys(bulanField, bulan)

        // Tanggal
        TestObject tanggalField = new TestObject("tanggal_${label}_${indexKe}")
        tanggalField.addProperty("xpath", ConditionType.EQUALS, baseXPath + "//span[@aria-label='Day']")
        WebUI.click(tanggalField)
        WebUI.sendKeys(tanggalField, tanggal)

        // Tahun
        TestObject tahunField = new TestObject("tahun_${label}_${indexKe}")
        tahunField.addProperty("xpath", ConditionType.EQUALS, baseXPath + "//span[@aria-label='Year']")
        WebUI.click(tahunField)
        WebUI.sendKeys(tahunField, tahun)

        WebUI.comment("Tanggal berhasil diisi untuk '${label}' (input ke-${indexKe})")
    }
}
