package com.example.contactsmobileapp.warnMessages.validator

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import com.example.contactsmobileapp.warnMessages.checkPhoneNumberLength
import com.example.contactsmobileapp.warnMessages.checkPhoneNumberFormat

fun manageEditPhoneNumber(phoneNumber: EditText, warnPhoneNumber: TextView){

    phoneNumber.addTextChangedListener(object: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if(!checkPhoneNumberLength(phoneNumber)){
                warnPhoneNumber.setText("Minimum 9 digits required")
                warnPhoneNumber.setTextColor(Color.rgb(255,0,0))
                warnPhoneNumber.visibility = TextView.VISIBLE
            }
            else if(!checkPhoneNumberFormat(phoneNumber)){
                warnPhoneNumber.setText("Required numerical format or (+xxPhoneNumber) format")
                warnPhoneNumber.setTextColor(Color.rgb(255,0,0))
                warnPhoneNumber.visibility = TextView.VISIBLE
            }
            else{
                warnPhoneNumber.setText("Correct")
                warnPhoneNumber.setTextColor(Color.rgb(0,255,0))
                warnPhoneNumber.visibility = TextView.VISIBLE
            }
        }
        override fun afterTextChanged(p0: Editable?) {
            if(phoneNumber.length() == 0)
                warnPhoneNumber.visibility = TextView.INVISIBLE
        }
    })
}