package com.nliven.hlt.util

import io.appium.java_client.AppiumDriver
import io.appium.java_client.ios.IOSDriver
import org.junit.Rule
import org.junit.rules.TestName
import org.openqa.selenium.OutputType
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import spock.lang.Specification

/**
 * Created by rsom on 7/7/14.
 */
abstract class AbstractSpec extends Specification implements EnhancedAppium {

	@Rule
	TestName name = new TestName()
	int snapshotIndex = 0

	def setup() {
		GroovifyAppium.init()
		File appDir = new File("../build/sym/Debug-iphonesimulator/")
		File app = new File(appDir, "ColorShow.app")
		DesiredCapabilities capabilities = new DesiredCapabilities()
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "")
		capabilities.setCapability("platformVersion", "8.1")
		capabilities.setCapability("platformName", "iOS")
		capabilities.setCapability("deviceName", "iPhone Simulator")
		capabilities.setCapability("app", app.absolutePath)
		driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities)
		snapshotIndex = 0

		if (this.metaClass.methods.find { it.name == 'postSetup' }) {
			this.postSetup()
		}
	}

	void takeSnapshot() {
		int currIndex = snapshotIndex++

		def snapshotDir = 'build/snapshots'
		if (!new File(snapshotDir).exists()) {
			new File(snapshotDir).mkdirs()
		}

		def methodName = name.methodName.replace(' ', '-')
		def className = this.class.name
		def snapshotName = "${className}.${methodName}-${currIndex}"

		new File("${snapshotDir}/${snapshotName}.xml").text = driver.pageSource

		def base64 = driver.getScreenshotAs(OutputType.BASE64)
		def bytes = base64.decodeBase64()

		def pngFileName = "${snapshotDir}/${snapshotName}.png"
		if (new File(pngFileName).exists()) {
			new File(pngFileName).delete()
		}
		def str = new File(pngFileName).newOutputStream()
		str.write(bytes)

	}

	def cleanup() {
		try {
			takeSnapshot()
		} catch (WebDriverException e) {
			println "Error obtaining snapshot"
			e.printStackTrace(System.err)
		} finally {
			driver.quit()
		}
	}

	public boolean waitFor(final Closure<Boolean> c) {
		waitFor(30, c)
	}

	public boolean waitFor(int waitTime, final Closure<Boolean> c) {
		for (int attempt = 0; attempt < waitTime; attempt++) {
			try {
				if (c.call()) {
					break
				} else {
					throw new Exception("Closure failed")
				}
			} catch (Exception e) {
				println "Exception: $e"
				sleep 1000
			}
		}
		c.call()
	}

}
