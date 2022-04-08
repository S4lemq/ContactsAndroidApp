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

    fun updateTask(taskToEdit: TaskItem?, newTask: TaskItem){
        taskToEdit?.let{oldTask->
            //Perform this operations only when taskToEdit is not null
            //Find the index of old task
            val indexOfOldTask = ITEMS.indexOf(oldTask)
            //place new task in place of oldTask
            ITEMS.add(indexOfOldTask,newTask)
            //Remove the oldTask that was move to the next position in the ITEMS list
            ITEMS.removeAt(indexOfOldTask + 1)
        }
    }

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
            addTask(createPlaceholderItem(i))
        }
    }

    fun addTask(item: TaskItem) {
        ITEMS.add(item)
    }

    private fun createPlaceholderItem(position: Int): TaskItem {
        return TaskItem(position.toString(), "" + position, "" + position, position.toLong(),position )
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
                    val dateOfBirth: String,
                    val phoneNumber: Long,
                    var avatarNumber: Int = 1) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readInt()
    ) {}

    override fun toString(): String = nameAndSurname

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(nameAndSurname)
        parcel.writeString(dateOfBirth)
        parcel.writeLong(phoneNumber)
        parcel.writeInt(avatarNumber)
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