package com.example.loginsysfragments.fragments

import android.app.ActionBar.LayoutParams
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.example.loginsysfragments.baseAdapter.ListViewBaseAdapter
import com.example.loginsysfragments.databinding.CustomDialogBinding
import com.example.loginsysfragments.databinding.FragmentBaseAdaptorBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BaseAdaptorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BaseAdaptorFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit  var baseAdapter : ListViewBaseAdapter
    lateinit var binding: FragmentBaseAdaptorBinding
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
        binding = FragmentBaseAdaptorBinding.inflate(layoutInflater)
        val array = arrayListOf("Manish","Peter","Andrew")
        baseAdapter = ListViewBaseAdapter(array)
        binding.lvList.adapter = baseAdapter

        binding.lvList.setOnItemClickListener{ adapterView: AdapterView<*>, view2: View, i: Int, l: Long ->
            val dialog = Dialog(requireActivity())
            val binding = CustomDialogBinding.inflate(layoutInflater)
            dialog.setContentView(binding.root)
            dialog.window?.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
            binding.etName.hint = array[i]
            binding.btnSubmit.setOnClickListener {
                if(binding.etName.text.toString().isEmpty()){
                    binding.etName.error = "Please enter your Name"
                }
                else {
                    array[i] = binding.etName.text.toString()
                    baseAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
        binding.lvList.setOnItemLongClickListener{adapterView: AdapterView<*>, view2: View, i: Int, l: Long ->
            array.removeAt(i)
            baseAdapter.notifyDataSetChanged()
            return@setOnItemLongClickListener true
        }
        binding.fabAdd.setOnClickListener {
            val dialog = Dialog(requireActivity())
            val binding = CustomDialogBinding.inflate(layoutInflater)
            dialog.setContentView(binding.root)
            dialog.window?.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
            binding.btnSubmit.setOnClickListener {
                if(binding.etName.text.toString().isEmpty()){
                    binding.etName.error = "Please Enter your Name"
                }
                else {
                    array.add(binding.etName.text.toString())
                    baseAdapter.notifyDataSetChanged()
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
         * @return A new instance of fragment BaseAdaptorFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BaseAdaptorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}