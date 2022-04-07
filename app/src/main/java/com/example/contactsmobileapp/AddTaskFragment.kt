package com.example.contactsmobileapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.example.contactsmobileapp.data.TaskItem
import com.example.contactsmobileapp.data.Tasks
import com.example.contactsmobileapp.databinding.FragmentAddTaskBinding
import com.example.contactsmobileapp.databinding.FragmentItemListBinding

/**
 * A simple [Fragment] subclass.
 * Use the [AddTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddTaskFragment : Fragment() {

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
    }

    private fun saveTask() {
        var nameAndSurname: String = binding.inputNameSurname.text.toString()
        var dateOfBirth: String = binding.inputDateOfBirth.toString()
        var phoneNumber: String = binding.inputPhoneNumber.toString()

        val taskItem = TaskItem(
            {nameAndSurname}.hashCode().toString(),
            nameAndSurname,
            dateOfBirth.toInt(),
            phoneNumber.toInt()
        )

        Tasks.addTask(taskItem)

        val inputMethodManager: InputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken,0)
    }
}