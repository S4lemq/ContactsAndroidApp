package com.example.contactsmobileapp.warnMessages

import android.widget.EditText

fun checkPhoneNumberLength(phoneNumber: EditText): Boolean{
    return (phoneNumber.length() >= 9)
}

fun checkPhoneNumberFormat(phoneNumber: EditText) : Boolean{
    var pattern = Regex("^\\+?[1-9][0-9]{7,14}$")
    return (phoneNumber.getText().toString().matches(pattern))
}

fun checkName(name: EditText): Boolean{
    var pattern = Regex("[a-zA-ZąćęłńóśżźĄĆĘŁŃÓŚŻŹ]+")
    return (name.getText().toString().matches(pattern))
}

fun checkDateOfBirth(date: EditText): Boolean{
    var pattern = Regex("^([0][1-9]|[1|2][0-9]|[3][0|1])[/]([0][1-9]|[1][0-2])[/]([1][9][0-9][0-9]|[2-9][0-9][0-9][0-9])$")
    return (date.getText().toString().matches(pattern))
}





