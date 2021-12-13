package company.vk.education.androidcourse.rememberthepills.fragments.schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import company.vk.education.androidcourse.rememberthepills.MainActivity
import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.room.entryForToday.DBClientEntryForToday
import company.vk.education.androidcourse.rememberthepills.room.entryForToday.EntityEntryForToday
import kotlinx.coroutines.launch
import java.time.LocalTime

class CourseListAdapter(
    private val courseEntries: List<CourseEntry>,
    private val mainActivity: MainActivity
) :
    RecyclerView.Adapter<CourseListAdapter.CourseEntryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseEntryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_course_entry, parent, false)

        return CourseEntryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseEntryViewHolder, position: Int) {
        if (courseEntries[position].isCheckBoxShown) {
            fun changeCheckState() {
                val dbClient = DBClientEntryForToday(mainActivity)
                mainActivity.lifecycleScope.launch {
                    dbClient.getByIntakeTime(
                        courseEntries[position].intakeTime,
                        courseEntries[position].idOfCourse
                    )
                }
                val entryForTodayTarget = dbClient.awaitResult() as EntityEntryForToday

                entryForTodayTarget.apply { isDone = !isDone }
                if (entryForTodayTarget.isDone) {
                    entryForTodayTarget.isMissed = false
                }

                if ((!entryForTodayTarget.isDone) and (LocalTime.now()
                        .isAfter(LocalTime.parse(entryForTodayTarget.intakeTime.toString())))
                ) {
                    entryForTodayTarget.isMissed = true
                }

                mainActivity.lifecycleScope.launch {
                    dbClient.update(entryForTodayTarget)
                }
                courseEntries[position].isDone = entryForTodayTarget.isDone
                courseEntries[position].isMissed = entryForTodayTarget.isMissed
                this.notifyDataSetChanged()
            }

            val checkBox =
                holder.itemView.findViewById<CheckBox>(R.id.checkbox_course_entry_is_done)

            holder.itemView.findViewById<ConstraintLayout>(R.id.layout_course_entry)
                .setOnClickListener {
                    changeCheckState()
                }

            // TODO: figure out how preserve checkbox animation
            checkBox.setOnClickListener {
                changeCheckState()
            }
        }

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
        private val intakeTime: TextView =
            itemView.findViewById(R.id.text_course_entry_intake_time)
        private val foodDependency: TextView =
            itemView.findViewById(R.id.text_course_food_dependency)

        fun bind(courseEntry: CourseEntry) {
            itemView.findViewById<Button>(R.id.button_course_entry_edit).setOnClickListener {
                val action = FragmentScheduleDirections.actionFragmentScheduleToFragmentCourse(
                    "edit",
                    -1,
                    courseEntry.idOfCourse
                )
                it.findNavController().navigate(action)
            }

            nameOfDrug.text = courseEntry.nameOfDrug
            amountOfDrug.text = courseEntry.amountOfDrug.toString()
            measurementOfDrug.text = courseEntry.measurementOfDrug
            intakeTime.text = courseEntry.intakeTime.toString()
            missed.visibility = View.GONE

            val foodDependencyText: String = courseEntry.foodDependency
            // TODO fix literal
            if (foodDependencyText != "Не зависит") {
                foodDependency.text = foodDependencyText.lowercase()
            }

            val checkBox = itemView.findViewById<CheckBox>(R.id.checkbox_course_entry_is_done)
            if (courseEntry.isCheckBoxShown) {
                checkBox.visibility = View.VISIBLE
                isDone.isChecked = courseEntry.isDone

                if (courseEntry.isMissed) {
                    missed.visibility = View.VISIBLE
                } else {
                    missed.visibility = View.GONE
                }
            } else {
                checkBox.visibility = View.GONE
            }
        }
    }
}