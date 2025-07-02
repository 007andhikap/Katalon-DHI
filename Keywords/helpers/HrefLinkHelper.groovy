package helpers

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

class LinkHelper {

	static void TombolHref(String hrefPartial) {
		TestObject link = new TestObject("link_" + hrefPartial)
		link.addProperty("xpath", ConditionType.EQUALS, "//a[contains(@href, '${hrefPartial}')]")

		WebUI.waitForElementClickable(link, 10)
		WebUI.click(link)
	}
}
