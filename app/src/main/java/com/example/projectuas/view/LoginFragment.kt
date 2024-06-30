package com.example.projectuas.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.projectuas.R
import com.example.projectuas.databinding.FragmentLoginBinding
import com.example.projectuas.viewModel.UserViewModel
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso

class LoginFragment : Fragment(), ButtonClickListener, ButtonActionNavClickListener {
    private lateinit var viewModel: UserViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.listener = this
        binding.navListener = this

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.loginFragment)
            }
        })


        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setHasOptionsMenu(true)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        Picasso.get()
            .load("https://ak.gamepress.gg/sites/default/files/styles/banner_image/public/2020-02/Arknights%20News%20Announcements.png?h=ceb20177&itok=mZ5XQbft").into(binding.imgBg);

    }

    override fun onButtonClick(v: View) {

        var username = binding.txtInputUsername.editText?.text.toString()
        var password = binding.textInputLayoutPassword.editText?.text.toString()

        if (username.isNotEmpty() && password.isNotEmpty()) {
            viewModel.fetchLogin(username, password)

            viewModel.checkLoginLD.observe(viewLifecycleOwner, Observer {success->
                if(success) {
                    val saveLogin = requireActivity().getSharedPreferences("loginAccount", Context.MODE_PRIVATE)
                    var editor = saveLogin.edit()
                    viewModel.userLoginLD.value?.let { userLogin->
                        editor.putString("id", userLogin.uuid.toString())
                        editor.putString("firstName", userLogin.first_name)
                        editor.putString("lastName", userLogin.last_name)
                        editor.putString("email", userLogin.email)
                        editor.putString("username", userLogin.username)
                        editor.putString("password", userLogin.password)
                        editor.putString("photo", userLogin.photo)
                        editor.apply()
                    }
                    val action = LoginFragmentDirections.actionHomeFragment()
                    Navigation.findNavController(v).navigate(action)
                    Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireContext(), "Username/Password not creditable", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun onButtonActionNavClick(v: View) {
        val action = LoginFragmentDirections.actionRegisterFragment()
        Navigation.findNavController(v).navigate(action)
    }

}