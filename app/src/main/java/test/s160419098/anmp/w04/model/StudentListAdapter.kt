package test.s160419098.anmp.w04.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import test.s160419098.anmp.w04.databinding.ItemStudentBinding
import test.s160419098.anmp.w04.view.StudentListFragmentDirections

class StudentListAdapter(
    private val students: ArrayList<Student>
) : RecyclerView.Adapter<StudentListAdapter.ViewHolder>() {

    class ViewHolder(
        val binding: ItemStudentBinding,
        getStudent: (index: Int) -> Student,
        onOpenDetailClick: (student: Student) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.openDetailListener = View.OnClickListener {
                onOpenDetailClick(getStudent(adapterPosition))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            getStudent = { students[it] },
            onOpenDetailClick = {
                Navigation.findNavController(parent).navigate(
                    StudentListFragmentDirections.openStudentDetail(it.id!!)
                )
            },
        )
    }

    override fun getItemCount() = students.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.student = students[position]
    }

    fun updateData(data: ArrayList<Student>) {
        students.clear()
        students.addAll(data)
        notifyDataSetChanged()
    }
}
