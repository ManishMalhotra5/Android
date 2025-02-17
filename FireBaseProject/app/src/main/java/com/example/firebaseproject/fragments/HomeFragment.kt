package com.example.firebaseproject.fragments

import android.app.ActionBar.LayoutParams
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaseproject.R
import com.example.firebaseproject.adapter.RecyclerViewAdapter
import com.example.firebaseproject.databinding.FragmentHomeBinding
import com.example.firebaseproject.databinding.FragmentLoginBinding
import com.example.firebaseproject.databinding.StudentDialogBinding
import com.example.firebaseproject.databinding.StudentLayoutBinding
import com.example.firebaseproject.interfaces.StudentInterface
import com.example.firebaseproject.models.StudentModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() ,StudentInterface{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentHomeBinding
    private  var  db = Firebase.firestore
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private var studentList = ArrayList<StudentModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private fun dialog(index:Int = -1){
        val dialog = Dialog(requireActivity())
        val dialogBinding  = StudentDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialog.window?.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
        dialogBinding.btnSave.setOnClickListener {
            if(dialogBinding.tvName.text.toString().isEmpty()){
                dialogBinding.tvName.error = "Enter your Name"
            }
            else if(dialogBinding.tvRollNo.text.toString().isEmpty()){
                dialogBinding.tvRollNo.error = "Enter your Roll No"
            }
            else {
                if(index < 0){
                    val studentObject = StudentModel(name = dialogBinding.tvName.text.toString(),
                        rollNo = dialogBinding.tvRollNo.text.toString().toInt()
                    )
                    db.collection("StudentModel").add(studentObject).addOnCompleteListener{
                        if(it.isSuccessful){
                            studentList.add(studentObject)
                            recyclerViewAdapter.notifyDataSetChanged()
                            Toast.makeText(requireContext(),"Student Added successfully",Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(requireContext(),"Failed to Add student",Toast.LENGTH_SHORT).show()
                        }
                    }
                    dialog.dismiss()
                }
                else {
                    studentList[index].name = dialogBinding.tvName.text.toString()
                    studentList[index].rollNo = dialogBinding.tvRollNo.text.toString().toInt()

                }
            }
        }
    dialog.show()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        recyclerViewAdapter = RecyclerViewAdapter(studentList,this)
        binding.rvlist.layoutManager = LinearLayoutManager(requireContext())

        binding.rvlist.adapter = recyclerViewAdapter


        binding.fabAdd.setOnClickListener {

        }
        db.collection("StudentModel").addSnapshotListener{
            value,error ->
            if(error != null){
                return@addSnapshotListener
            }
            for (data in value!!.documentChanges){
                when(data.type){
                    DocumentChange.Type.ADDED -> {
                        dialog()
                    }
                    DocumentChange.Type.MODIFIED -> {
                        dialog()
                    }
                    DocumentChange.Type.REMOVED -> {
                        dialog()
                    }
                }
            }
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
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun updateData(index: Int) {

    }

    override fun deleteData(index: Int) {

    }
}