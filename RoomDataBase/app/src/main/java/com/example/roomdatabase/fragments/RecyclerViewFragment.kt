package com.example.roomdatabase.fragments

import android.app.ActionBar.LayoutParams
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabase.MainActivity
import com.example.roomdatabase.database.UserDatabase
import com.example.roomdatabase.databinding.CustomDialogBinding
import com.example.roomdatabase.databinding.FragmentRecyclerViewBinding
import com.example.roomdatabase.interfaces.RecyclerViewInterface
import com.example.roomdatabase.models.UserModel
import com.example.roomdatabase.recyclerviewadapter.RecyclerViewAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecyclerViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecyclerViewFragment : Fragment() ,RecyclerViewInterface{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding : FragmentRecyclerViewBinding
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var userDatabase: UserDatabase
    private var arrayList = ArrayList<UserModel>()
    private lateinit var mainActivity : MainActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivity = activity as MainActivity
        userDatabase = UserDatabase.getInstance(mainActivity)
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private fun dialog(index : Int = -1){
        val dialog  = Dialog(mainActivity)
        val binding = CustomDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        dialog.window?.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)

        binding.btnSubmit.setOnClickListener {
            if(binding.etName.text.toString().isEmpty()){
                binding.etName.error = "Enter your Name"
            }
            else if(binding.etRollNo.toString().isEmpty()){
                binding.etName.error = "Enter your RollNo"
            }
            else {
                if(index >= 0){
                    arrayList[index].name = binding.etName.text.toString()
                    arrayList[index].rollNo = binding.etRollNo.text.toString().toInt()
                    userDatabase.userDao().updateData(arrayList[index])
                    getData()
                    dialog.dismiss()
                }
                else {
                    userDatabase.userDao().insertData(UserModel(name = binding.etName.text.toString(),
                         rollNo = binding.etRollNo.text.toString().toInt()
                    ))
                    getData()

                    dialog.dismiss()
                }
            }

        }
        dialog.show()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = FragmentRecyclerViewBinding.inflate(layoutInflater)
        recyclerViewAdapter = RecyclerViewAdapter(arrayList, this)
        binding.rvlist.layoutManager = LinearLayoutManager(mainActivity)
        binding.rvlist.adapter = recyclerViewAdapter
        getData()
        binding.fabAdd.setOnClickListener {
            dialog()
        }
        return binding.root
    }
    fun getData(){
        arrayList.clear()
        arrayList.addAll(userDatabase.userDao().getList())
        recyclerViewAdapter.notifyDataSetChanged()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecyclerView.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecyclerViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun updateText(index: Int) {
        dialog(index)

    }

    override fun deleteText(index: Int) {
    }
}