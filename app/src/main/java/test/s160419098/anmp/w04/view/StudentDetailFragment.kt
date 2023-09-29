package test.s160419098.anmp.w04.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.textfield.TextInputEditText
import test.s160419098.anmp.w04.R
import test.s160419098.anmp.w04.viewmodel.StudentDetailViewModel

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
