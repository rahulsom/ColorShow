package com.github.rahulsom

import com.nliven.hlt.util.AbstractSpec
import com.nliven.hlt.util.EnhancedAppium

class SampleSpec extends AbstractSpec {

	def "the home page looks ok"() {
		given: "The app is launched"

		when: "You inspect all buttons"
		def buttons = C("UIAButton")

		then: "Buttons with 4 colors are present"
		buttons.size() == 4
		buttons[0].text == 'Red'
		buttons[1].text == 'Blue'
		buttons[2].text == 'Green'
		buttons[3].text == 'Yellow'

		when: "You inspect all labels"
		def textFields = A('lblMessage')

		then: "There is 1 field present"
		textFields.size() == 1
		textFields[0].value() == 'Click a button'

		when: "You click red"
		C("UIAButton").find {it.text == 'Red'}.click()

		then: "The text says 'You chose Red'"
		A('lblMessage')[0].value() == 'You chose Red'

	}

}
