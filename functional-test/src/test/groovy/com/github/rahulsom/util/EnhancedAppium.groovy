package com.nliven.hlt.util

import groovy.transform.CompileStatic
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement

/**
 * Created by rahul on 10/31/14.
 */
@CompileStatic
trait EnhancedAppium {
	AppiumDriver driver

	List<MobileElement> A(String accessibilityId) {
		driver.findElementsByAccessibilityId(accessibilityId)
	}

	List<MobileElement> C(String className) {
		driver.findElementsByClassName(className)
	}
}
