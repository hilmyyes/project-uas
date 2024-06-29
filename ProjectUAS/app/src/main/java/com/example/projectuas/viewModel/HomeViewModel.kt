package com.example.projectuas.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.projectuas.model.News
import com.example.projectuas.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    var homesLD =MutableLiveData<List<News>>()
    val homesLoadErrorLD =MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private var job = Job()
//    val TAG = "volleyTag"
//    private var queue:RequestQueue ?= null

    override val coroutineContext:CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh(){
        homesLoadErrorLD.value = false
        loadingLD.value = true

        launch {
            val db = buildDb(getApplication())
            homesLD.postValue(db.hobbyDao().selectAllNews())
            loadingLD.postValue(false)
        }

//        queue = Volley.newRequestQueue(getApplication())
//        val url = "http://10.0.2.2/hobbyApp/news.json"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                val sType = object :TypeToken<List<News>>() {}.type
//                val result = Gson().fromJson<List<News>>(it, sType)
//                homesLD.value = result as ArrayList<News>?
//                loadingLD.value = false
//
//                Log.d("showVolley", result.toString())
//            },
//            {
//                Log.d("showVolley", it.toString())
//                loadingLD.value = false
//                homesLoadErrorLD.value = false
//            }
//        )
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
    }

//    override fun onCleared() {
//        super.onCleared()
//        queue?.cancelAll(TAG)
//    }

    fun clearNews(news: News) {
        launch {
            val db = buildDb(getApplication())
            db.hobbyDao().deleteNews(news)
            homesLD.postValue(db.hobbyDao().selectAllNews())
        }
    }
}