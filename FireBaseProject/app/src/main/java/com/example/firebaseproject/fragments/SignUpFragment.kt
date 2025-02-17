package com.example.firebaseproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.firebaseproject.MainActivity
import com.example.firebaseproject.R
import com.example.firebaseproject.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private  fun emailValidator(email:String) : Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
        return email.matches(emailRegex.toRegex())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        firebaseAuth = FirebaseAuth.getInstance()
        mainActivity = activity as MainActivity
        binding.tvLogin.setOnClickListener {
            mainActivity.navController.navigate(R.id.loginFragment)
        }
        binding.btnSignUp.setOnClickListener {
            if(binding.etName.text.toString().isEmpty()){
                binding.etName.error =  "Please enter your Name"
            }
            else if(binding.etEmail.text.toString().isEmpty()){
                binding.etEmail.error = "Please enter your Email"
            }
            else if(binding.etPass.text.toString().isEmpty()){
                binding.etPass.error = "Please enter your Password"
            }

            else if(!emailValidator(binding.etEmail.text.toString())){
                binding.etEmail.error = "Please enter valid Email"
            }
            else if(binding.etPass.text.toString().length  < 6){
                binding.etPass.error = "Password has to be longer than 6 character"
            }
            else {

                firebaseAuth.createUserWithEmailAndPassword(
                    binding.etEmail.text.toString(),
                    binding.etPass.text.toString()
                ).addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(requireContext(),"Account Created Successful", Toast.LENGTH_SHORT).show()
                        mainActivity.navController.navigate(R.id.homeFragment)
                    }
                    else {
                        Toast.makeText(requireContext(),"Failed to Create an Account",Toast.LENGTH_SHORT).show()
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
         * @return A new instance of fragment SignUpFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUpFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}