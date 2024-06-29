package com.example.projectuas.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.projectuas.R
import com.example.projectuas.databinding.FragmentRegisterBinding
import com.example.projectuas.model.Users
import com.example.projectuas.viewModel.UserViewModel
import com.google.android.material.navigation.NavigationView

class RegisterFragment : Fragment(), ButtonClickListener {
    private lateinit var viewModel: UserViewModel
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.listener = this

        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).findViewById<NavigationView>(R.id.navView).visibility = View.GONE

        binding.user = Users("", "", "", "", "", "")

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)



//        binding.btnOk.setOnClickListener {
//            var firstName = binding.textInputLayoutFirst.editText?.text.toString()
//            var lastName = binding.textInputLayoutLast.editText?.text.toString()
//            var email = binding.textInputLayoutEmail.editText?.text.toString()
//            var username = binding.textInputLayoutUsername.editText?.text.toString()
//            var password = binding.textInputLayoutPass.editText?.text.toString()
//            var rePass = binding.textInputLayoutRePass.editText?.text.toString()
//            var photo = binding.textInputLayoutPhoto.editText?.text.toString()
//
//
//            //Check if password and retype password is same
//            if(password == rePass)
//            {
//                viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
//                viewModel.fetchRegister(firstName, lastName, email, username, password, photo)
//
//                viewModel.userRegistLD.observe(viewLifecycleOwner, Observer {
//                    if(it == true){
//                        val action = RegisterFragmentDirections.actionLoginFragment()
//                        Navigation.findNavController(view).navigate(action)
//                    }
//                })
//            }
//
//        }
    }

    override fun onButtonClick(v: View) {

        var firstName = binding.textInputLayoutFirst.editText?.text.toString()
        var lastName = binding.textInputLayoutLast.editText?.text.toString()
        var email = binding.textInputLayoutEmail.editText?.text.toString()
        var username = binding.textInputLayoutUsername.editText?.text.toString()
        var password = binding.textInputLayoutPass.editText?.text.toString()
        var rePass = binding.textInputLayoutRePass.editText?.text.toString()
        var photo = binding.textInputLayoutPhoto.editText?.text.toString()

//        Log.d("Check", binding.user!!.password)

        if (password == rePass) {
            binding.user = Users(firstName, lastName, email, username, password, photo)
            viewModel.fetchRegister(binding.user!!)
            Toast.makeText(v.context, "News Added", Toast.LENGTH_SHORT).show()

            val action = RegisterFragmentDirections.actionLoginFragment()
            Navigation.findNavController(v).navigate(action)
        }
    }


}