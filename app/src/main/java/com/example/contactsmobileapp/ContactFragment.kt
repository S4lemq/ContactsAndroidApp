package com.example.contactsmobileapp

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsmobileapp.data.ContactItem
import com.example.contactsmobileapp.data.Contacts
import com.example.contactsmobileapp.databinding.FragmentItemListBinding
import com.example.contactsmobileapp.dialogs.CallDialogFragment
import com.example.contactsmobileapp.dialogs.DeleteDialogFragment
import com.google.android.material.snackbar.Snackbar

/**
 * A fragment representing a list of Items.
 */
class ContactFragment() : Fragment(), ContactAppListener,
    CallDialogFragment.onCallDialogInteractionListener,
    DeleteDialogFragment.onDeleteDialogInteractionListener {

    private lateinit var binding: FragmentItemListBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentItemListBinding.inflate(inflater,container,false)

        // Set the adapter
        with(binding.list){
            layoutManager = LinearLayoutManager(context)
            adapter = MyContactRecyclerViewAdapter(Contacts.ITEMS, this@ContactFragment)
        }

        binding.addButton.setOnClickListener{
            addButtonClick()
        }



        return binding.root
    }

    private fun addButtonClick() {
        findNavController().navigate(R.id.action_contactFragment_to_addContactFragment)
    }



    companion object {
        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"
    }

    override fun onItemClick(position: Int) {
        val actionTaskFragmentToDisplayTaskFragment =
            ContactFragmentDirections.actionContactFragmentToDisplayContactFragment(Contacts.ITEMS.get(position))
                findNavController().navigate(actionTaskFragmentToDisplayTaskFragment)
    }

    private fun checkPermission(){
        if(ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.CALL_PHONE),101)
        }
    }

    override fun onItemLongClick(position: Int) {
        showCallDialog(position)
    }

    private fun showCallDialog(position: Int) {
        val callDialog = CallDialogFragment.newInstance(
            (Contacts.ITEMS.get(position).name + " " + Contacts.ITEMS.get(position).surname),
            position,
            this)
        callDialog.show(requireActivity().supportFragmentManager,"CallDialog")
    }

    override fun onDialogPositiveClick(pos: Int?) {
        checkPermission()
        val phoneNumber = Contacts.ITEMS.get(pos!!).phoneNumber.toString()
        Contacts.ITEMS.removeAt(pos!!)
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$phoneNumber")
        startActivity(callIntent)
    }

    override fun onDialogNegativeClick(pos: Int?) {
       Snackbar.make(requireView(),"Call canceled", Snackbar.LENGTH_LONG)
           .setAction("Redo",View.OnClickListener { showCallDialog(pos!!) })
           .show()
    }

    override fun onDeleteContact(position: Int) {
        showDeleteDialog(position)
    }

    private fun showDeleteDialog(position: Int) {
        val deleteDialog = DeleteDialogFragment.newInstance(
            (Contacts.ITEMS.get(position).name),
            position,
            this)

        deleteDialog.show(requireActivity().supportFragmentManager, "DeleteDialog")
    }

    override fun onDialogDeletePositiveClick(pos: Int?) {
        Contacts.ITEMS.removeAt(pos!!)
        Snackbar.make(requireView(),"Contact Deleted", Snackbar.LENGTH_LONG)
            .show()
        notifyDataSetChanged()
    }

    private fun notifyDataSetChanged() {
        val rvAdapter = binding.list.adapter
        rvAdapter?.notifyDataSetChanged()
    }

    override fun onDialogDeleteNegativeClick(pos: Int?) {
        Snackbar.make(requireView(),"Delete cancelled", Snackbar.LENGTH_LONG)
            .setAction("Redo", View.OnClickListener { showDeleteDialog(pos!!) })
            .show()
    }

}