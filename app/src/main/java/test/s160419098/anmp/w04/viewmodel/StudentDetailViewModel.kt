package test.s160419098.anmp.w04.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import test.s160419098.anmp.w04.model.Student

class StudentDetailViewModel(app: Application) : AndroidViewModel(app) {
    val student = MutableLiveData<Student>()

    fun fetch() {
        this.student.value = Student(
            "16055",
            "Nonie",
            "1998/03/28",
            "5718444778",
            "http://dummyimage.com/75x100.jpg/cc0000/ffffff",
        )
    }
}
