package com.valbert.programmercalculator

import android.view.View
import android.widget.Button

class Calculator {
    private var base = "hex"

    fun inactivateBase(activity: MainActivity) {
        val buttonHex: Button = activity.findViewById(R.id.hexButton)
        buttonHex.setBackgroundResource(0)

        val buttonDec: Button = activity.findViewById(R.id.decButton)
        buttonDec.setBackgroundResource(0)

        val buttonOct: Button = activity.findViewById(R.id.octButton)
        buttonOct.setBackgroundResource(0)

        val buttonBin: Button = activity.findViewById(R.id.binButton)
        buttonBin.setBackgroundResource(0)
    }

    fun activateBase(view: View) {
        this.base = view.tag.toString()
        view.setBackgroundResource(R.drawable.base_green)
    }
}