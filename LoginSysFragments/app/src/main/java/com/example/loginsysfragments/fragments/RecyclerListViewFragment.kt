package com.example.loginsysfragments.fragments

import android.app.ActionBar.LayoutParams
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.room.Database
import androidx.room.Room
import com.example.loginsysfragments.R
import com.example.loginsysfragments.ToastAlertActivity
import com.example.loginsysfragments.adapter.RecyclerListViewAdapter
import com.example.loginsysfragments.databinding.CustomDialogBinding
import com.example.loginsysfragments.databinding.FragmentRecyclerListViewBinding
import com.example.loginsysfragments.databinding.RecyclerViewLayoutBinding
import com.example.loginsysfragments.interfaces.RecyclerViewInterface
import com.example.loginsysfragments.models.RecyclerModel
import com.example.loginsysfragments.roomdatabase.AppDatabase
import com.example.loginsysfragments.roomdatabase.UserDao

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecyclerListViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecyclerListViewFragment : Fragment(),RecyclerViewInterface {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentRecyclerListViewBinding
    private lateinit var recyclerListViewAdapter: RecyclerListViewAdapter
    private lateinit var userDatabase: AppDatabase

    lateinit var toastAlertActivity: ToastAlertActivity
     var arrayList   = ArrayList<RecyclerModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        toastAlertActivity = activity as ToastAlertActivity
        userDatabase = Room.databaseBuilder(toastAlertActivity,AppDatabase::class.java,"UserDatabase").build()
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    fun dialog(index : Int = -1) {
        var dialog = Dialog(requireActivity())
        var binding = CustomDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        dialog.window?.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)

        binding.btnSubmit.setOnClickListener {
            if(binding.etName.text.toString().isEmpty()){
                binding.etName.error = "Enter Name"
            }
            else {
                if(index >= 0){
                    arrayList[index].name = binding.etName.text.toString()
                    userDatabase.userDao().updateData(RecyclerModel())
                    recyclerListViewAdapter.notifyDataSetChanged()
                }
                else {
//                    arrayList.add(RecyclerModel(name = binding.etName.text.toString()))
                    userDatabase.userDao().insertData(RecyclerModel(name = binding.etName.text.toString(), rollno = binding.etRollNo.text.toString().toInt()))
                    recyclerListViewAdapter.notifyDataSetChanged()

                }

                dialog.dismiss()
            }
        }
        dialog.show()


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRecyclerListViewBinding.inflate(layoutInflater)

        recyclerListViewAdapter = RecyclerListViewAdapter(arrayList,this)
        binding.rvlist.layoutManager = LinearLayoutManager(toastAlertActivity)
        binding.rvlist.adapter = recyclerListViewAdapter

        binding.fabAdd.setOnClickListener {
            dialog()
        }
        binding.lvList

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecyclerListViewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecyclerListViewFragment().apply {
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
        arrayList.removeAt(index)
        recyclerListViewAdapter.notifyDataSetChanged()
    }

}