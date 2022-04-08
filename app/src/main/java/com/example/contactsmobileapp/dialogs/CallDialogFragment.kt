package com.example.contactsmobileapp.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.contactsmobileapp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val CALL_NAME = "call name"
private const val POS_NAME = "pos name"

/**
 * A simple [Fragment] subclass.
 * Use the [CallDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CallDialogFragment : DialogFragment() {

    private var callNameParam: String? = null
    private var callPosParam: Int? = null
    lateinit var mListener: onCallDialogInteractionListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            callNameParam = it.getString(CALL_NAME)
            callPosParam = it.getInt(POS_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_call_dialog, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setMessage("Call to this person?" + " $callNameParam")
        builder.setPositiveButton("Confirm", DialogInterface.OnClickListener{ dialogInterface, i ->
            mListener?.onDialogPositiveClick(callPosParam)
        })
        builder.setNegativeButton("Discard", DialogInterface.OnClickListener{ dialogInterface, i ->
            mListener?.onDialogNegativeClick(callPosParam)
        })
        return builder.create()
    }

    interface onCallDialogInteractionListener{
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
         * @return A new instance of fragment CallDialogFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(name: String, pos: Int, interactionListener: onCallDialogInteractionListener) =
            CallDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(CALL_NAME, name)
                    putInt(POS_NAME, pos)
                }
                mListener = interactionListener
            }
    }
}

