package com.example.contactsmobileapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import androidx.navigation.fragment.navArgs
import com.example.contactsmobileapp.data.ContactItem
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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contact: ContactItem = args.contact
        binding.displayNameAndSurname.text = contact.name + " " + contact.surname
        binding.displayPhoneNumber.text = contact.phoneNumber.toString()
        binding.displayDateOfBirth.text = contact.dateOfBirth

        val resource = when(contact.avatarNumber){
            1 -> R.drawable.builder
            2 -> R.drawable.business_person
            3 -> R.drawable.man
            4 -> R.drawable.costume
            5 -> R.drawable.dracula
            6 -> R.drawable.man1
            7 -> R.drawable.man3
            8 -> R.drawable.old_man
            9 -> R.drawable.woman
            10 -> R.drawable.zombie
            11 -> R.drawable.zombie2
            else -> R.drawable.builder
        }
        binding.displayImage.setImageResource(resource)

        binding.displayEdit.setOnClickListener {
            val taskToEdit =
                DisplayContactFragmentDirections.actionDisplayContactFragmentToAddContactFragment(
                    contactToEdit = contact,
                    edit = true
                )
            findNavController().navigate(taskToEdit)
        }
    }
}