package com.example.contactsmobileapp

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
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
    val logs: String = "passLog"
    private lateinit var binding: FragmentAddTaskBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              avedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddTaskBinding.inflate(inflater,container,false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submitData(binding.inputPhoneNumber,binding.warnPhoneNumber)
        binding.saveButton.setOnClickListener{
            if(!(binding.inputPhoneNumber.getText().toString().length < 9))
            saveTask()
        }
        binding.inputNameSurname.setText(args.taskToEdit?.nameAndSurname)
        binding.inputDateOfBirth.setText(args.taskToEdit?.dateOfBirth)
        binding.inputPhoneNumber.setText(args.taskToEdit?.phoneNumber.toString())
    }


    private fun submitData(phoneNumber: EditText, warnPhoneNumber: TextView){
        var inputTextIsCorrect: Boolean = false
        binding.inputPhoneNumber.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    //binding.warnPhoneNumber.visibility = TextView.INVISIBLE

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.inputPhoneNumber.length() < 9){
                    binding.warnPhoneNumber.setText("Minimum 9 digits required")
                    binding.warnPhoneNumber.setTextColor(Color.rgb(255,0,0))
                    binding.warnPhoneNumber.visibility = TextView.VISIBLE
                }
                else{
                    binding.warnPhoneNumber.setText("Number is correct")
                    binding.warnPhoneNumber.setTextColor(Color.rgb(0,255,0))
                    binding.warnPhoneNumber.visibility = TextView.VISIBLE
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
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
}