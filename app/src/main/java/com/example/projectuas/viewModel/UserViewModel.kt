package com.example.projectuas.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.projectuas.model.Users
import com.example.projectuas.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    var userLoginLD = MutableLiveData<Users>()
    val userRegistLD = MutableLiveData<Boolean>()
    val userUpdateLD = MutableLiveData<Boolean>()
    val checkLoginLD = MutableLiveData<Boolean>()
    private val job = Job()
//    val TAG = "volleyTag"
//    private var queue:RequestQueue ?= null

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun fetchRegister(users: Users){
        launch {
            val db = buildDb(getApplication())
            db.hobbyDao().insertAllUser(users)
            userRegistLD.postValue(true)
        }
    }

    fun fetchLogin(username: String, password: String){
        launch {
            try {
                val db = buildDb(getApplication())
                var userLogin = db.hobbyDao().loginUser(username, password)
                if (userLogin == null || userLogin.uuid.toString().isNullOrEmpty()) {
                    checkLoginLD.postValue(false)
                }
                else {
                    userLoginLD.postValue(userLogin)
                    checkLoginLD.postValue(true)
                }
            }
            catch (e:Exception) {
                checkLoginLD.postValue(false)
                Log.e("checkerror", e.toString())
            }
        }
    }

    fun fetchUpdate(firstName:String, lastName:String, password:String, id:Int){
        launch {
            val db = buildDb(getApplication())
            db.hobbyDao().updateUser(firstName, lastName, password, id)
            userUpdateLD.postValue(true)
        }
    }

    fun isUserLoggedIn(): Boolean {
        return userLoginLD.value != null && !userLoginLD.value?.uuid.toString().isNullOrEmpty()
    }

}