package com.example.contactsmobileapp

interface ContactAppListener {
    fun onItemClick(position: Int)
    fun onItemLongClick(position: Int)
}