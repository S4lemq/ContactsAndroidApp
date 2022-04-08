package com.example.contactsmobileapp.warnMessages


import android.widget.EditText


fun checkPhoneNumber(phoneNumber: EditText): Boolean{
    if(phoneNumber.length() >= 9) return true
    return false
}

