package com.example.projectuas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isInvisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.example.projectuas.R
import com.example.projectuas.databinding.ActivityMainBinding
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.projectuas.viewModel.UserViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var userViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        navController = (supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment).navController
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        binding.navView.setupWithNavController(navController)

        binding.bottomNav.setupWithNavController(navController)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment, R.id.registerFragment -> {
                    binding.bottomNav.isInvisible = true
                }
                else->{
                    binding.bottomNav.isInvisible = false
                }
            }
        }

        if(!userViewModel.isUserLoggedIn()){
            Log.d("Check exception", "Berhasil")
            navController.navigate(R.id.loginFragment)
//            val action = HomeListFragmentDirections.actionHomeLoginFragment()
//            Navigation.findNavController(view).navigate(action)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, binding.drawerLayout) || super.onSupportNavigateUp()
    }

}