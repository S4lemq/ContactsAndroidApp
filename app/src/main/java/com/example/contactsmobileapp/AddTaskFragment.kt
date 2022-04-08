package com.example.contactsmobileapp

import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.contactsmobileapp.data.TaskItem
import com.example.contactsmobileapp.data.Tasks
import com.example.contactsmobileapp.databinding.FragmentAddTaskBinding
import com.example.contactsmobileapp.warnMessages.validators.manageEditPhoneNumber
import com.example.contactsmobileapp.warnMessages.checkPhoneNumber
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [AddTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddTaskFragment() : Fragment(), Parcelable {

    val args: AddTaskFragmentArgs by navArgs()
    val logs: String = "passLog"
    private lateinit var binding: FragmentAddTaskBinding

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              avedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddTaskBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        manageEditPhoneNumber(binding.inputPhoneNumber, binding.warnPhoneNumber)
        binding.saveButton.setOnClickListener{
            if(checkPhoneNumber(binding.inputPhoneNumber))
            saveTask()
        }
        binding.inputNameSurname.setText(args.taskToEdit?.nameAndSurname)
        binding.inputDateOfBirth.setText(args.taskToEdit?.dateOfBirth)
        binding.inputPhoneNumber.setText(args.taskToEdit?.phoneNumber.toString())
    }

    private fun saveTask() {
        var nameAndSurname: String = binding.inputNameSurname.text.toString()
        var dateOfBirth = binding.inputDateOfBirth.text.toString()
        var phoneNumber = binding.inputPhoneNumber.text.toString()

        val taskItem = TaskItem(
            {nameAndSurname + dateOfBirth}.hashCode().toString(),
            nameAndSurname,
            dateOfBirth,
            phoneNumber.toLong()
        )

        if(nameAndSurname.isEmpty()) nameAndSurname = getString(R.string.default_name_and_surname) + "${Tasks.ITEMS.size + 1}"
        if(dateOfBirth.isEmpty()) dateOfBirth = getString(R.string.default_date_of_birth)

        if(!args.edit){
            Tasks.addTask(taskItem)
        }else{
            Tasks.updateTask(args.taskToEdit,taskItem)
        }

        val inputMethodManager: InputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken,0)

        findNavController().popBackStack(R.id.taskFragment,false)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AddTaskFragment> {
        override fun createFromParcel(parcel: Parcel): AddTaskFragment {
            return AddTaskFragment(parcel)
        }

        override fun newArray(size: Int): Array<AddTaskFragment?> {
            return arrayOfNulls(size)
        }
    }
}