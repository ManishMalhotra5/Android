package com.example.loginsysfragments.fragments

import android.app.ActionBar.LayoutParams
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.loginsysfragments.R
import com.example.loginsysfragments.ToastAlertActivity
import com.example.loginsysfragments.databinding.CustomDialogBinding
import com.example.loginsysfragments.databinding.FragmentToastAlertBinding
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ToastAlertFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ToastAlertFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentToastAlertBinding

    private lateinit  var toastAlertActivity : ToastAlertActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        toastAlertActivity = activity as ToastAlertActivity
        binding = FragmentToastAlertBinding.inflate(layoutInflater)
        binding.btnToast.setOnClickListener {
            Toast.makeText(requireContext(), "Toast button is clicked", Toast.LENGTH_SHORT).show()
        }
        binding.btnAlert.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(requireContext())
            alertDialogBuilder.setTitle("Alert !")
            alertDialogBuilder.setMessage("This is a alert")
            alertDialogBuilder.setCancelable(false)
            alertDialogBuilder.setPositiveButton("ok",{_,_ ->
            })
            alertDialogBuilder.setNegativeButton("No",{_,_ ->

            })
            alertDialogBuilder.show()
        }

        binding.btnDialog.setOnClickListener {
            val dialogBuilder = Dialog(requireActivity())
            val dialogBinding = CustomDialogBinding.inflate(layoutInflater)
            dialogBuilder.setContentView(binding.root)
            dialogBuilder.window?.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
            dialogBinding.btnSubmit.setOnClickListener {
                if(dialogBinding.etName.text.toString().isEmpty()){
                    dialogBinding.etName.error = "Please enter name"
                }
                else {
                    binding.tvName.setText(dialogBinding.etName.text.toString())
                    dialogBuilder.dismiss()
                }
            }
            dialogBuilder.show()
        }
        binding.btnListView.setOnClickListener {
            findNavController().navigate(R.id.listViewFragment2)
        }
        binding.btnSpinner.setOnClickListener {
            findNavController().navigate(R.id.spinnerFragment)
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ToastAlertFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ToastAlertFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}