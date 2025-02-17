package com.example.loginsysfragments.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.example.loginsysfragments.MainActivity
import com.example.loginsysfragments.R
import com.example.loginsysfragments.databinding.FragmentOtpBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OtpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OtpFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentOtpBinding
    lateinit var mainActivity: MainActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOtpBinding.inflate(layoutInflater)

        binding.btnSubmit.setOnClickListener{
            if(binding.etEmail.text.toString().isEmpty())
            {
                binding.etEmail.error ="Please enter your Email"
            }
            else if(!emailValidator(binding.etEmail.text.toString())){
                binding.etEmail.error = "Please enter valid Email"
            }
            else if(binding.etOtp1.text.toString().isEmpty()){
                binding.etOtp1.error = "Please enter OTP"
            }
            else if(binding.etOtp2.text.toString().isEmpty()){
                binding.etOtp2.error = "Please enter OTP"
            }
            else if(binding.etOtp3.text.toString().isEmpty()){
                binding.etOtp1.error = "Please enter OTP"
            }
            else if(binding.etOtp4.text.toString().isEmpty()){
                binding.etOtp1.error = "Please enter OTP"
            }else {
                mainActivity.navController.navigate(R.id.loginFragment)
            }

        }


        return binding.root
    }
    private fun emailValidator(email:String) : Boolean{
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        return email.matches(emailRegex.toRegex())
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OtpFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OtpFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}