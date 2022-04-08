package com.example.contactsmobileapp

import android.media.Image
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.contactsmobileapp.data.Roll
import com.example.contactsmobileapp.data.TaskItem
import com.example.contactsmobileapp.databinding.FragmentItemBinding
import com.example.contactsmobileapp.databinding.FragmentItemListBinding

/**
 * [RecyclerView.Adapter] that can display a [TaskItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyTaskRecyclerViewAdapter(private val values: List<TaskItem>,
                                private val eventListener: ToDoListListener) : RecyclerView.Adapter<MyTaskRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun rollImg(): Int{
        val roll = Roll()
        return roll.drawNumber()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        item.avatarNumber = rollImg()
        val resource = when(item.avatarNumber){
            1 -> R.drawable.builder
            2 -> R.drawable.business_person
            3 -> R.drawable.man
            else -> R.drawable.man
        }

        holder.imgView.setImageResource(resource)
        holder.contentView.text = item.nameAndSurname

        holder.itemContainer.setOnClickListener{
            eventListener.onItemClick(position)
        }

        holder.itemContainer.setOnLongClickListener{
            eventListener.onItemLongClick(position)
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgView: ImageView = binding.itemImg
        val contentView: TextView = binding.content
        val itemContainer: View = binding.root

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}