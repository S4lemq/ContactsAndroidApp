package com.example.contactsmobileapp.warnMessages.validators

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import com.example.contactsmobileapp.warnMessages.checkPhoneNumber

fun manageEditPhoneNumber(phoneNumber: EditText, warnPhoneNumber: TextView){

    phoneNumber.addTextChangedListener(object: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if(phoneNumber.length() == 0)
                warnPhoneNumber.visibility = TextView.INVISIBLE
        }
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if(!checkPhoneNumber(phoneNumber)){
                warnPhoneNumber.setText("Minimum 9 digits required")
                warnPhoneNumber.setTextColor(Color.rgb(255,0,0))
                warnPhoneNumber.visibility = TextView.VISIBLE
            }
            else{
                warnPhoneNumber.setText("Number is correct")
                warnPhoneNumber.setTextColor(Color.rgb(0,255,0))
                warnPhoneNumber.visibility = TextView.VISIBLE
            }
        }
        override fun afterTextChanged(p0: Editable?) {
        }
    })
}