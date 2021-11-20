package company.vk.education.androidcourse.rememberthepills.schedule.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.schedule.model.CourseEntry
import company.vk.education.androidcourse.rememberthepills.schedule.view.FragmentScheduleDirections

class CourseListAdapter(private val courseEntries: List<CourseEntry>) :
    RecyclerView.Adapter<CourseListAdapter.CourseEntryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseEntryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_course_list, parent, false)

        val checkBoxEntryIsDone = view.findViewById<CheckBox>(R.id.checkbox_course_entry_is_done)
        view.findViewById<ConstraintLayout>(R.id.layout_course_entry).setOnClickListener {
            checkBoxEntryIsDone.apply { isChecked = !isChecked }
            // TODO: everything else besides just changing the checked state
        }

        return CourseEntryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseEntryViewHolder, position: Int) {
        holder.bind(courseEntries[position])
    }

    override fun getItemCount() = courseEntries.size

    class CourseEntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameOfDrug: TextView =
            itemView.findViewById(R.id.text_course_entry_name_of_drug)
        private val amountOfDrug: TextView =
            itemView.findViewById(R.id.text_course_entry_amount_of_drug)
        private val isDone: CheckBox = itemView.findViewById(R.id.checkbox_course_entry_is_done)
        private val missed: TextView = itemView.findViewById(R.id.text_course_entry_missed)
        private val measurementOfDrug: TextView =
            itemView.findViewById(R.id.text_course_entry_measurement_of_drug)

        fun bind(courseEntry: CourseEntry) {
            itemView.findViewById<Button>(R.id.button_course_entry_edit).setOnClickListener {
                val action = FragmentScheduleDirections.actionFragmentScheduleToFragmentCourse("edit", -1, courseEntry.idOfCourse)
                it.findNavController().navigate(action)
            }

            nameOfDrug.text = courseEntry.nameOfDrug
            amountOfDrug.text = courseEntry.amountOfDrug.toString()
            measurementOfDrug.text = courseEntry.measurementOfDrug

            isDone.isChecked = courseEntry.isDone

            if (courseEntry.isMissed) missed.visibility = View.VISIBLE
        }
    }
}