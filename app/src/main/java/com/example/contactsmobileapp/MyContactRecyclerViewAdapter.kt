package com.example.contactsmobileapp

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.contactsmobileapp.data.Roll
import com.example.contactsmobileapp.data.ContactItem
import com.example.contactsmobileapp.databinding.FragmentItemBinding

/**
 * [RecyclerView.Adapter] that can display a [ContactItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyContactRecyclerViewAdapter(private val values: List<ContactItem>,
                                   private val eventListener: ContactAppListener) : RecyclerView.Adapter<MyContactRecyclerViewAdapter.ViewHolder>() {

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

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val rand = rollImg()
        val resource = when(item.avatarNumber){
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

        holder.imgView.setImageResource(resource)
        holder.contentView.text = item.name + " " + item.surname

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