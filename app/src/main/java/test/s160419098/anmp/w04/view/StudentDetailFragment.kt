package test.s160419098.anmp.w04.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import test.s160419098.anmp.w04.R
import test.s160419098.anmp.w04.databinding.FragmentStudentDetailBinding
import test.s160419098.anmp.w04.viewmodel.StudentDetailViewModel
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment() {
    private var _binding: FragmentStudentDetailBinding? = null
    private val binding get() = _binding!!

    private val studentDetailViewModel: StudentDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        Button "save changes" tidak bekerja sesuai namanya
//        karena tidak ada endpoint update student yang tersedia.
        binding.onSaveClickListener = View.OnClickListener {
            Observable.timer(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    MainActivity.postNotification(
                        "Changes saved successfully",
                        "Student data changes saved",
                        R.drawable.round_save_24,
                    )
                }
        }

        studentDetailViewModel.student.observe(viewLifecycleOwner) {
            binding.student = it
        }

        requireArguments().let {
            studentDetailViewModel.fetch(
                StudentDetailFragmentArgs.fromBundle(it).studentId
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
