package com.example.contactsmobileapp.warnMessages

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView

fun manageEditNameAndSurname(name: EditText, warnName: TextView){

    name.addTextChangedListener(object: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            if(!checkName(name)){
                warnName.setText("Required alphabetical character")
                warnName.setTextColor(Color.rgb(255,0,0))
                warnName.visibility = TextView.VISIBLE
            }
            else{
                warnName.setText("Correct")
                warnName.setTextColor(Color.rgb(0,255,0))
                warnName.visibility = TextView.VISIBLE
            }
        }
        override fun afterTextChanged(p0: Editable?) {
            if(name.length() == 0)
                warnName.visibility = TextView.INVISIBLE
        }
    })
}