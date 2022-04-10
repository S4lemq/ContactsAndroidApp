package com.example.contactsmobileapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.contactsmobileapp.data.TaskItem
import com.example.contactsmobileapp.databinding.FragmentDisplayContactBinding

/**
 * A simple [Fragment] subclass.
 * Use the [DisplayContactFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DisplayContactFragment : Fragment() {

    val args: DisplayContactFragmentArgs by navArgs()
    lateinit var binding: FragmentDisplayContactBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentDisplayContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val task: TaskItem = args.contact
        binding.displayNameAndSurname.text = task.name + " " + task.surname
        binding.displayPhoneNumber.text = task.phoneNumber.toString()
        binding.displayDateOfBirth.text = task.dateOfBirth
        val resource = when(task.avatarNumber){
            1 -> R.drawable.builder
            2 -> R.drawable.business_person
            3 -> R.drawable.man
            else -> 1
        }
        binding.displayImage.setImageResource(resource)

        binding.displayEdit.setOnClickListener{
            val taskToEdit =
                DisplayContactFragmentDirections.actionDisplayTaskFragmentToAddTaskFragment(
                    contactToEdit = task,
                    edit = true
                )
            findNavController().navigate(taskToEdit)
        }

    }

}