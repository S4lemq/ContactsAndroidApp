package com.example.contactsmobileapp.data

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList
import java.util.*

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object Contacts {

    fun updateContact(contactToEdit: ContactItem?, newContact: ContactItem){
        contactToEdit?.let{ oldContact->
            //Perform this operations only when contactToEdit is not null
            //Find the index of old contact
            val indexOfOldTask = ITEMS.indexOf(oldContact)
            //place new contact in place of oldContact
            ITEMS.add(indexOfOldTask,newContact)
            //Remove the oldContact that was move to the next position in the ITEMS list
            ITEMS.removeAt(indexOfOldTask + 1)
        }
    }

    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<ContactItem> = ArrayList()

    /**
     * A map of sample (placeholder) items, by ID.
     */

    private val COUNT = 10

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addContact(createPlaceholderItem(i))
        }
    }

    fun addContact(item: ContactItem) {
        ITEMS.add(item)
    }

    private fun createPlaceholderItem(position: Int): ContactItem {
        return ContactItem(position.toString(), "" + position, "" + position,"" + position, position.toLong(),position )
    }

}

/**
 * A placeholder item representing a piece of content.
 * Aby przekazać niestandardowe obiekty między fragmentami, należy wykonać serializację na
obiekt. W Androidzie odbywa się to za pomocą interfejsu Parcelable. Dodawanie tego interfejsu
jest prawie automatyczny z pomocą Android Studio, jednak w niektórych przypadkach musimy
dokończ implementację ręcznie. W tym przykładzie (w kolejnych krokach) będziemy musieli dodać
ZNACZENIE wyliczenie do paczki.
 */



data class ContactItem(val id: String,
                       val name: String,
                       val surname: String,
                       val dateOfBirth: String,
                       val phoneNumber: Long,
                       var avatarNumber: Int = 1) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readInt()
    ) {}

    override fun toString(): String = name + " " + surname


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeString(dateOfBirth)
        parcel.writeLong(phoneNumber)
        parcel.writeInt(avatarNumber)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ContactItem> {
        override fun createFromParcel(parcel: Parcel): ContactItem {
            return ContactItem(parcel)
        }

        override fun newArray(size: Int): Array<ContactItem?> {
            return arrayOfNulls(size)
        }
    }
}

class Roll(val iconAmount: Int = 11){
    fun drawNumber(isRangesRandom: Boolean = true): Int{
        if(isRangesRandom){
            return (1..iconAmount).random()
        }else{
            return Random().nextInt(iconAmount) + 1
        }
    }
}