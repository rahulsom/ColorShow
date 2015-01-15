package com.nliven.hlt.util

import io.appium.java_client.MobileElement

/**
 * Created by rsom on 7/10/14.
 */
class GroovifyAppium {
	private static boolean initialized = false

	static init() {
		if (!initialized) {
			initialized = true
			MobileElement.metaClass.value = { ->
				((MobileElement) delegate).getAttribute('value')
			}
			MobileElement.metaClass.name = { ->
				((MobileElement) delegate).getAttribute('name')
			}
			MobileElement.metaClass.leftShift = { String s ->
				((MobileElement) delegate).sendKeys(s)
			}
		}
	}
}
