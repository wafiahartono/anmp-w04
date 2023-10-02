package test.s160419098.anmp.w04.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import test.s160419098.anmp.w04.model.Aircraft

class AircraftListViewModel(
    app: Application,
) : AndroidViewModel(app) {
    val aircrafts = MutableLiveData<List<Aircraft>>()

    private val VOLLEY_TAG = "volley.aircraft"
    private var queue: RequestQueue? = null

    fun refresh() {
        queue = Volley.newRequestQueue(getApplication())

        val stringRequest = StringRequest(
            Request.Method.GET,
            // https://gist.github.com/wafiahartono/4c7bc557e67405607e335ca6b3389a93
            "https://gist.githubusercontent.com/wafiahartono/4c7bc557e67405607e335ca6b3389a93/raw/a06288867e64a57aa36a913aa16e6ec7b3e39d5a/anmp-w06-aircrafts.json",
            { response ->
                Log.d("VolleyRequest.Aircraft", response)
                this.aircrafts.value = Gson().fromJson<List<Aircraft>>(
                    response,
                    object : TypeToken<List<Aircraft>>() {}.type,
                )
            },
            { error ->
                Log.d("VolleyRequest.Aircraft", error.message.toString())
            },
        )

        stringRequest.tag = VOLLEY_TAG

        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(VOLLEY_TAG)
    }
}