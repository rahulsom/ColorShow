//
//  ViewController.swift
//  ColorShow
//
//  Created by Rahul Somasunderam on 1/14/15.
//  Copyright (c) 2015 Rahul. All rights reserved.
//


import UIKit

extension UILabel {
    dynamic func accessibilityValue() -> String! {
        return self.text
    }
}

class ViewController: UIViewController {

    @IBOutlet weak var btnRed: UIButton!
    @IBOutlet weak var btnBlue: UIButton!
    @IBOutlet weak var btnGreen: UIButton!
    @IBOutlet weak var btnYellow: UIButton!
    @IBOutlet weak var txtResult: UILabel!

    @IBAction func buttonTouched(sender: UIButton) {
        if (sender == btnRed) {
            txtResult.text = "You chose Red"
        } else if (sender == btnBlue) {
            txtResult.text = "You chose Blue"
        } else if (sender == btnGreen) {
            txtResult.text = "You chose Green"
        } else if (sender == btnYellow) {
            txtResult.text = "You chose Yellow"
        }
    }


}
