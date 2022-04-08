package com.example.contactsmobileapp.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.DialogFragment

private const val CONTACT_NAME_PARAM = "contact name"
private const val CONTACT_POS_PARAM = "contact pos"

/**
 * A simple [Fragment] subclass.
 * Use the [DeleteDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DeleteDialogFragment : DialogFragment() {

    private var contactNameParam: String? = null
    private var contactPosParam: Int? = null
    private lateinit var mListener: onDeleteDialogInteractionListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            contactNameParam = it.getString(CONTACT_NAME_PARAM)
            contactPosParam = it.getInt(CONTACT_POS_PARAM)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setMessage("Delete this contact?" + " $contactNameParam")
        builder.setPositiveButton("Confirm", DialogInterface.OnClickListener{ dialogInterface, i ->
            mListener?.onDialogPositiveClick(contactPosParam)
        })
        builder.setNegativeButton("Discard", DialogInterface.OnClickListener{ dialogInterface, i ->
            mListener?.onDialogNegativeClick(contactPosParam)
        })
        return builder.create()
    }

    interface onDeleteDialogInteractionListener{
        fun onDialogPositiveClick(pos: Int?)
        fun onDialogNegativeClick(pos: Int?)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param name Parameter 1.
         * @param pos Parameter 2.
         * @return A new instance of fragment DeleteDialogFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(name: String, pos: Int, interactionListener: CallDialogFragment.onCallDialogInteractionListener) =
            CallDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(CONTACT_NAME_PARAM, name)
                    putInt(CONTACT_POS_PARAM, pos)
                }
                mListener = interactionListener
            }
    }
}