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
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "http://10.0.2.2/hobbyApp/register.php"
//
//        val stringRequest = object : StringRequest(
//            Method.POST, url,
//            {response->
//                userRegistLD.value = true
//                Log.d("Register", "Result: ${response}")
//            },
//            {
//                userRegistLD.value = false
//                Log.d("Register", it.toString())
//            }
//        )
//        {
//            override fun getParams(): MutableMap<String, String>? {
//                val params = HashMap<String, String>()
//                params["firstName"] = firstName
//                params["lastName"] = lastName
//                params["email"] = email
//                params["username"] = username
//                params["password"] = password
//                params["photo"] = photo
//                return params
//            }
//        }
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
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
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "http://10.0.2.2/hobbyApp/login.php"
//
//        val stringRequest = object : StringRequest(
//            Method.POST, url,
//            {response->
//                try {
//                    val userLogin = Gson().fromJson(response, Users::class.java)
//
//                    if(userLogin == null || userLogin.id.isNullOrEmpty()){
//                        checkLoginLD.value = false
//                    }else{
//                        userLoginLD.value = userLogin
//                        checkLoginLD.value = true
//                    }
//                }catch (e: Exception){
//                    checkLoginLD.value = false
//                    Log.e("Login Success", "Error parsing response: $response", e)
//                }
//            },
//            {error ->
//                checkLoginLD.value = false
//                Log.e("Login", "Volley error: ${error.message}", error)
//            }
//        )
//        {
//            override fun getParams(): MutableMap<String, String>? {
//                val params = HashMap<String, String>()
//                params["username"] = username
//                params["password"] = password
//                return params
//            }
//        }
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
    }

    fun fetchUpdate(firstName:String, lastName:String, password:String, id:Int){
        launch {
            val db = buildDb(getApplication())
            db.hobbyDao().updateUser(firstName, lastName, password, id)
            userUpdateLD.postValue(true)
        }
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "http://10.0.2.2/hobbyApp/updateUser.php"
//
//        val stringRequest = object : StringRequest(
//            Method.POST, url,{response->
//                userUpdateLD.value = true
//                Log.d("Update", "Result: ${response}")
//            },
//            {
//                userUpdateLD.value = false
//                Log.d("Update", it.toString())
//            }
//        )
//        {
//            override fun getParams(): MutableMap<String, String>? {
//                val params = HashMap<String, String>()
//                params["id"] = id
//                params["firstName"] = firstName
//                params["lastName"] = lastName
//                params["password"] = password
//                return params
//            }
//        }
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
    }

    fun isUserLoggedIn(): Boolean {
        return userLoginLD.value != null && !userLoginLD.value?.uuid.toString().isNullOrEmpty()
    }

//    override fun onCleared() {
//        super.onCleared()
//        queue?.cancelAll(TAG)
//    }
}