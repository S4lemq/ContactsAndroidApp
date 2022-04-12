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
import com.example.contactsmobileapp.data.ContactItem
import com.example.contactsmobileapp.data.Contacts
import com.example.contactsmobileapp.databinding.FragmentAddContactBinding
import com.example.contactsmobileapp.warnMessages.*
import com.example.contactsmobileapp.warnMessages.validator.manageEditPhoneNumber
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [AddContactFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddContactFragment() : Fragment(), Parcelable {

    val args: AddContactFragmentArgs by navArgs()
    private lateinit var binding: FragmentAddContactBinding

    constructor(parcel: Parcel) : this() {}

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              avedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddContactBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        manageEditPhoneNumber(binding.inputPhoneNumber, binding.warnPhoneNumber)
        manageEditPhoneNumber(binding.inputPhoneNumber,binding.warnPhoneNumber)
        manageEditNameAndSurname(binding.inputName, binding.warnName)
        manageEditNameAndSurname(binding.inputSurname, binding.warnSurname)
        manageEditDateOfBirth(binding.inputDateOfBirth,binding.warnDateOfBirth)

        binding.saveButton.setOnClickListener{
            if(checkPhoneNumberLength(binding.inputPhoneNumber) &&
                checkPhoneNumberFormat(binding.inputPhoneNumber) &&
                checkName(binding.inputName) &&
                checkName(binding.inputSurname) &&
                checkDateOfBirth(binding.inputDateOfBirth))
                saveTask()
        }

        binding.inputName.setText(args.contactToEdit?.name)
        binding.inputSurname.setText(args.contactToEdit?.surname)
        binding.inputDateOfBirth.setText(args.contactToEdit?.dateOfBirth)
        binding.inputPhoneNumber.setText(args.contactToEdit?.phoneNumber)
    }

    private fun saveTask() {
        var name: String = binding.inputName.text.toString()
        var surname: String = binding.inputSurname.text.toString()
        var dateOfBirth = binding.inputDateOfBirth.text.toString()
        var phoneNumber = binding.inputPhoneNumber.text.toString()
        var avatarNumber = (1..11).random()

        val contactItem = ContactItem(
            {name+ surname + dateOfBirth}.hashCode().toString(),
            name,
            surname,
            dateOfBirth,
            phoneNumber,
            avatarNumber
        )

        if(!args.edit){
            Contacts.addContact(contactItem)
        }else{
            Contacts.updateContact(args.contactToEdit,contactItem)
        }

        val inputMethodManager: InputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken,0)

        findNavController().popBackStack(R.id.contactFragment,false)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AddContactFragment> {
        override fun createFromParcel(parcel: Parcel): AddContactFragment {
            return AddContactFragment(parcel)
        }

        override fun newArray(size: Int): Array<AddContactFragment?> {
            return arrayOfNulls(size)
        }
    }
}