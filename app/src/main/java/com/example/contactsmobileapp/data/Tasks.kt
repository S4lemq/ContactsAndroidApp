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
object Tasks {

    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<TaskItem> = ArrayList()

    /**
     * A map of sample (placeholder) items, by ID.
     */

    private val COUNT = 10

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(createPlaceholderItem(i))
        }
    }

    private fun addItem(item: TaskItem) {
        ITEMS.add(item)
    }

    private fun createPlaceholderItem(position: Int): TaskItem {
        return TaskItem(position.toString(), "Item " + position, makeDetails(position))
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
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

data class TaskItem(val id: String,
                    val nameAndSurname: String,
                    val dateOfBirth: Long,
                    val phoneNumber: Long,
                    val avatarNumber: Long) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readLong(),
        parcel.readLong()
    ) {}

    override fun toString(): String = nameAndSurname

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(nameAndSurname)
        parcel.writeLong(dateOfBirth)
        parcel.writeLong(phoneNumber)
        parcel.writeLong(avatarNumber)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TaskItem> {
        override fun createFromParcel(parcel: Parcel): TaskItem {
            return TaskItem(parcel)
        }

        override fun newArray(size: Int): Array<TaskItem?> {
            return arrayOfNulls(size)
        }
    }
}

class Roll(val iconAmount: Int = 3){
    fun drawNumber(isRangesRandom: Boolean = true): Int{
        if(isRangesRandom){
            return (1..iconAmount).random()
        }else{
            return Random().nextInt(iconAmount) + 1
        }
    }
}