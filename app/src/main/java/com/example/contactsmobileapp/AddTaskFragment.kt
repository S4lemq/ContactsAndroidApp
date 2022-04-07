package com.example.contactsmobileapp

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.contactsmobileapp.data.TaskItem
import com.example.contactsmobileapp.data.Tasks
import com.example.contactsmobileapp.databinding.FragmentAddTaskBinding
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [AddTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddTaskFragment : Fragment() {

    val args: AddTaskFragmentArgs by navArgs()

    private lateinit var binding: FragmentAddTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddTaskBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveButton.setOnClickListener{saveTask()}
        binding.inputNameSurname.setText(args.taskToEdit?.nameAndSurname)
        binding.inputDateOfBirth.setText(args.taskToEdit?.dateOfBirth.toString())
        binding.inputPhoneNumber.setText(args.taskToEdit?.phoneNumber.toString())
    }


    private fun saveTask() {
        var nameAndSurname: String = binding.inputNameSurname.text.toString()
        var dateOfBirth = binding.inputDateOfBirth.text.toString()
        var phoneNumber = binding.inputPhoneNumber.text.toString().toInt()

        val taskItem = TaskItem(
            {nameAndSurname + dateOfBirth}.hashCode().toString(),
            nameAndSurname,
            dateOfBirth,
            phoneNumber
        )

        if(nameAndSurname.isEmpty()) nameAndSurname = getString(R.string.default_name_and_surname) + "${Tasks.ITEMS.size + 1}"

        if(!args.edit){
            Tasks.addTask(taskItem)
        }else{
            Tasks.updateTask(args.taskToEdit,taskItem)
        }

        val inputMethodManager: InputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken,0)

        findNavController().popBackStack(R.id.taskFragment,false)
    }
}