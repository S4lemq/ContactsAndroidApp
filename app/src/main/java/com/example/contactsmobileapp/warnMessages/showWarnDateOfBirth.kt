package com.example.contactsmobileapp.warnMessages

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView

fun manageEditDateOfBirth(date: EditText, warnDate: TextView){

    date.addTextChangedListener(object: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            if(!checkDateOfBirth(date)){
                warnDate.setText("Required dd/mm/yyyy format")
                warnDate.setTextColor(Color.rgb(255,0,0))
                warnDate.visibility = TextView.VISIBLE
            }
            else{
                warnDate.setText("Correct")
                warnDate.setTextColor(Color.rgb(0,255,0))
                warnDate.visibility = TextView.VISIBLE
            }
        }
        override fun afterTextChanged(p0: Editable?) {
            if(date.length() == 0)
                warnDate.visibility = TextView.INVISIBLE
        }
    })
}