package test.s160419098.anmp.w04.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import test.s160419098.anmp.w04.R
import test.s160419098.anmp.w04.view.StudentListFragmentDirections

class StudentListAdapter(
    val students: ArrayList<Student>,
) : RecyclerView.Adapter<StudentListAdapter.ViewHolder>() {

    class ViewHolder(
        view: View,
        onButtonOpenDetailClick: (studentIndex: Int) -> Unit,
    ) : RecyclerView.ViewHolder(view) {
        val textID: TextView
        val textFullName: TextView
        val imagePhoto: ImageView

        init {
            textID = view.findViewById(R.id.textID)
            textFullName = view.findViewById(R.id.textFullName)
            imagePhoto = view.findViewById(R.id.imageProfile)

            view.findViewById<Button>(R.id.buttonOpenDetail).setOnClickListener {
                onButtonOpenDetailClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)

        val onButtonOpenDetailClick: (Int) -> Unit = { studentIndex ->
            Navigation.findNavController(parent).navigate(
                StudentListFragmentDirections.actionStudentListFragmentToStudentDetailFragment(
                    students[studentIndex].id!!
                )
            )
        }

        return ViewHolder(view, onButtonOpenDetailClick)
    }

    override fun getItemCount() = students.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            val student = students[position]
            textID.text = student.id
            textFullName.text = student.fullName

            Picasso.Builder(itemView.context)
                .listener { _, _, exception -> exception.printStackTrace() }
                .build()
                .load(student.photoUrl)
                .into(imagePhoto)
        }
    }

    fun updateData(data: ArrayList<Student>) {
        students.clear()
        students.addAll(data)
        notifyDataSetChanged()
    }
}
