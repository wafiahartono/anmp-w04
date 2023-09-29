package test.s160419098.anmp.w04.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import test.s160419098.anmp.w04.model.Student

class StudentListViewModel : ViewModel() {
    val students = MutableLiveData<ArrayList<Student>>()

    fun refresh() {
        students.value = arrayListOf(

        )
    }
}
