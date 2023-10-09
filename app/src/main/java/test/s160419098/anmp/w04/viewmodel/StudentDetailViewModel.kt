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
import test.s160419098.anmp.w04.model.Student

class StudentDetailViewModel(app: Application) : AndroidViewModel(app) {
    val student = MutableLiveData<Student>()

    private val VOLLEY_TAG = "volley"
    private var queue: RequestQueue? = null

    fun fetch(studentId: String) {
        queue = Volley.newRequestQueue(getApplication())

        val stringRequest = StringRequest(
            Request.Method.GET,
            "http://adv.jitusolution.com/student.php?id=$studentId",
            { response ->
                Log.d("VolleyRequest", response)
                this.student.value = Gson().fromJson<Student>(
                    response,
                    object : TypeToken<Student>() {}.type,
                )
            },
            { error ->
                Log.d("VolleyRequest", error.message.toString())
            },
        )

        stringRequest.tag = VOLLEY_TAG

        queue?.add(stringRequest)
    }
}
