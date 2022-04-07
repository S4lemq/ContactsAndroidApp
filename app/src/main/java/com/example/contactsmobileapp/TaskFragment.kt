package com.example.contactsmobileapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.contactsmobileapp.data.Tasks
import com.example.contactsmobileapp.databinding.FragmentItemListBinding

/**
 * A fragment representing a list of Items.
 */
class TaskFragment : Fragment() {

    private lateinit var binding: FragmentItemListBinding



    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentItemListBinding.inflate(inflater,container,false)

        // Set the adapter
        with(binding.list){
            layoutManager = LinearLayoutManager(context)
            adapter = MyTaskRecyclerViewAdapter(Tasks.ITEMS)
        }
        return binding.root
    }

    companion object {
        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"
    }
}