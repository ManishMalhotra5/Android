package com.example.loginsysfragments.fragments

import android.app.ActionBar.LayoutParams
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.loginsysfragments.databinding.CustomDialogBinding
import com.example.loginsysfragments.databinding.FragmentListViewBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListViewFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding : FragmentListViewBinding
    private lateinit var arrayAdapter: ArrayAdapter<String>

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
    ): View {
        binding = FragmentListViewBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        val array = arrayListOf("Manish","Andrew","Emma")
        arrayAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,array)
        binding.lvList.adapter = arrayAdapter
        //update
        binding.lvList.setOnItemClickListener{ adapterView: AdapterView<*>, view2: View, i: Int, l: Long ->
            val dialog  = Dialog(requireActivity())
            val binding = CustomDialogBinding.inflate(layoutInflater)
            dialog.setContentView(binding.root)
            dialog.window?.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
            binding.etName.setHint(array[i])
            binding.btnSubmit.setOnClickListener {
                if(binding.etName.text.toString().isEmpty()){
                    binding.etName.error = "Please enter your new  Name"
                }
                else {
                    array[i] = binding.etName.text.toString()
                    arrayAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
    //delete
     binding.lvList.setOnItemLongClickListener{ adapterView : AdapterView<*>,view2 :View, i:Int,l:Long ->

         array.removeAt(i)
         arrayAdapter.notifyDataSetChanged()
         return@setOnItemLongClickListener true

     }

    //add
        binding.fabAdd.setOnClickListener {
            val dialog = Dialog(requireActivity())
            val dialogBinding  = CustomDialogBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)
            dialog.window?.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
            dialogBinding.btnSubmit.setOnClickListener {
                if(dialogBinding.etName.text.toString().isEmpty()){
                    dialogBinding.etName.error = "Please enter your Name"
                }
                else {
                    array.add(dialogBinding.etName.text.toString())
                    arrayAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
            dialog.show()
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
         * @return A new instance of fragment ListViewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}