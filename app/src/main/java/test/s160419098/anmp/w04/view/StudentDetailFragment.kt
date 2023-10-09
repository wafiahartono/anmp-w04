package test.s160419098.anmp.w04.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.textfield.TextInputEditText
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import test.s160419098.anmp.w04.R
import test.s160419098.anmp.w04.viewmodel.StudentDetailViewModel
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment() {
    private val studentDetailViewModel: StudentDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.buttonSaveChanges).setOnClickListener {
            Observable.timer(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("Observable", it.toString())
                    MainActivity.postNotification(
                        "Changes saved successfully",
                        "Student data changes saved",
                        R.drawable.round_save_24,
                    )
                }
        }

        observeViewModel()

        studentDetailViewModel.fetch()
    }

    private fun observeViewModel() {
        studentDetailViewModel.student.observe(viewLifecycleOwner) { student ->
            view?.findViewById<TextInputEditText>(R.id.editTextID)?.setText(student.id)
            view?.findViewById<TextInputEditText>(R.id.editTextName)?.setText(student.fullName)
            view?.findViewById<TextInputEditText>(R.id.editTextDateOfBirth)?.setText(student.dateOfBirth)
            view?.findViewById<TextInputEditText>(R.id.editTextPhone)?.setText(student.phoneNumber)
        }
    }
}
