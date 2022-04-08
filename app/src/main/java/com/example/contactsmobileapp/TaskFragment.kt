package com.example.contactsmobileapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.navigation.fragment.findNavController
import com.example.contactsmobileapp.data.Tasks
import com.example.contactsmobileapp.databinding.FragmentItemBinding
import com.example.contactsmobileapp.databinding.FragmentItemListBinding
import com.example.contactsmobileapp.dialogs.CallDialogFragment
import com.google.android.material.snackbar.Snackbar

/**
 * A fragment representing a list of Items.
 */
class TaskFragment : Fragment(), ToDoListListener,
    CallDialogFragment.onCallDialogInteractionListener {

    private lateinit var binding: FragmentItemListBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentItemListBinding.inflate(inflater,container,false)

        // Set the adapter
        with(binding.list){
            layoutManager = LinearLayoutManager(context)
            adapter = MyTaskRecyclerViewAdapter(Tasks.ITEMS, this@TaskFragment)
        }

        binding.addButton.setOnClickListener{
            Log.d("chuj", "chuj")
            addButtonClick()
        }
        return binding.root
    }

    private fun addButtonClick() {
        findNavController().navigate(R.id.action_taskFragment_to_addTaskFragment)
    }

    companion object {
        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"
    }

    override fun onItemClick(position: Int) {
        val actionTaskFragmentToDisplayTaskFragment =
            TaskFragmentDirections.actionTaskFragmentToDisplayTaskFragment(Tasks.ITEMS.get(position))
                findNavController().navigate(actionTaskFragmentToDisplayTaskFragment)
    }


    override fun onItemLongClick(position: Int) {
        showCallDialog(position)
    }

    private fun showCallDialog(position: Int) {
        val callDialog = CallDialogFragment.newInstance(
            (Tasks.ITEMS.get(position).name + " " + Tasks.ITEMS.get(position).surname),
            position,
            this)
        callDialog.show(requireActivity().supportFragmentManager,"CallDialog")
    }

    override fun onDialogPositiveClick(pos: Int?) {
        Tasks.ITEMS.removeAt(pos!!)
        Snackbar.make(requireView(), "Calling...", Snackbar.LENGTH_LONG)
            .show()
    }

    override fun onDialogNegativeClick(pos: Int?) {
       Snackbar.make(requireView(),"Call canceled", Snackbar.LENGTH_LONG)
           .setAction("Redo",View.OnClickListener { showCallDialog(pos!!) })
           .show()
    }
}