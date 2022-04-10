package com.example.contactsmobileapp

import android.annotation.SuppressLint
import android.app.Fragment
import android.nfc.tech.Ndef.get
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.contactsmobileapp.data.ContactItem
import com.example.contactsmobileapp.data.Contacts
import com.example.contactsmobileapp.databinding.ActivityMainBinding
import com.example.contactsmobileapp.databinding.FragmentItemBinding
import com.example.contactsmobileapp.databinding.FragmentItemListBinding
import com.example.contactsmobileapp.dialogs.CallDialogFragment
import com.example.contactsmobileapp.dialogs.DeleteDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.lang.reflect.Array.get

/**
 * [RecyclerView.Adapter] that can display a [ContactItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyContactRecyclerViewAdapter(private val values: List<ContactItem>,
                                   private val eventListener: ContactAppListener) : RecyclerView.Adapter<MyContactRecyclerViewAdapter.ViewHolder>(),
    DeleteDialogFragment.onDeleteDialogInteractionListener{

    private lateinit var binding: FragmentItemListBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int,): ViewHolder {
        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
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

        holder.deleteButton.setOnClickListener{
           showDeleteDialog(position)
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

    private fun showDeleteDialog(position: Int) {
        val deleteDialog = DeleteDialogFragment.newInstance(
            (Contacts.ITEMS.get(position).name),
            position,
            this)
        deleteDialog.showsDialog
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgView: ImageView = binding.itemImg
        val contentView: TextView = binding.content
        val itemContainer: View = binding.root
        val deleteButton: FloatingActionButton = binding.deleteButton

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    override fun onDialogPositiveClick(pos: Int?) {
        TODO("Not yet implemented")
    }

    override fun onDialogNegativeClick(pos: Int?) {
        TODO("Not yet implemented")
    }

//    override fun onDialogPositiveClick(pos: Int?) {
//        Contacts.ITEMS.removeAt(pos!!)
//        Snackbar.make("Task Deleted", Snackbar.LENGTH_LONG)
//            .show()
//        notifyDataSetChanged()
//    }
//
//    override fun onDialogNegativeClick(pos: Int?) {
//        Snackbar.make(require(),"Delete cancelled", Snackbar.LENGTH_LONG)
//            .setAction("Redo", View.onClickListener{showDeleteDialog(pos!!)})
//            .show()
//
//    }
//
//    private fun notifyDataSetChanged(){
//
//        val rvAdapter = binding.list.adapter
//        rvAdapter?.notifyDataSetChanged()
//    }

}