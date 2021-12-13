package company.vk.education.androidcourse.rememberthepills.fragments.courseHistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import company.vk.education.androidcourse.rememberthepills.MainActivity
import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.room.course.EntityCourse
import company.vk.education.androidcourse.rememberthepills.room.drug.DBClientDrugs
import company.vk.education.androidcourse.rememberthepills.room.drug.EntityDrug
import kotlinx.coroutines.launch

class CourseHistoryListAdapter(
    private val courseEntries: List<EntityCourse>,
    private val mainActivity: MainActivity
) :
    RecyclerView.Adapter<CourseHistoryListAdapter.CourseHistoryEntryViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseHistoryEntryViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_course_history_entry, parent, false)

        return CourseHistoryEntryViewHolder(view, mainActivity)
    }

    override fun onBindViewHolder(holder: CourseHistoryEntryViewHolder, position: Int) {
        holder.bind(courseEntries[position])
    }

    override fun getItemCount() = courseEntries.size

    class CourseHistoryEntryViewHolder(itemView: View, private val mainActivity: MainActivity) :
        RecyclerView.ViewHolder(itemView) {
        private val nameOfDrug: TextView =
            itemView.findViewById(R.id.text_course_history_entry_name_of_drug)
        private val amountOfDrug: TextView =
            itemView.findViewById(R.id.text_course_history_entry_amount_of_drug)
        private val frequencyInDays: TextView =
            itemView.findViewById(R.id.course_history_entry_frequency_in_days)
        private val dateStart: TextView =
            itemView.findViewById(R.id.course_history_entry_date_start)
        private val dateEnd: TextView =
            itemView.findViewById(R.id.course_history_entry_date_end)
        private val measureOfDrug: TextView =
            itemView.findViewById(R.id.text_course_history_entry_measure_of_drug)
        private val foodDependency: TextView =
            itemView.findViewById(R.id.text_course_history_food_dependency)

        fun bind(courseHistoryEntry: EntityCourse) {
            val dbClientDrugs = DBClientDrugs(mainActivity)
            mainActivity.lifecycleScope.launch {
                dbClientDrugs.getByID(courseHistoryEntry.drugID)
            }
            val entityOfRelatedDrug = dbClientDrugs.awaitResult() as EntityDrug

            nameOfDrug.text = entityOfRelatedDrug.name
            dateStart.text = courseHistoryEntry.dateStart.toString().replace('-', '.')
            dateEnd.text = courseHistoryEntry.dateEnd.toString().replace('-', '.')
            measureOfDrug.text = courseHistoryEntry.measurement
            amountOfDrug.text = courseHistoryEntry.amount.toString()
            frequencyInDays.text = courseHistoryEntry.frequencyInDays.toString()

            val foodDependencyText: String = courseHistoryEntry.foodDependency
            // TODO fix literal
            if (foodDependencyText != "Не зависит") {
                foodDependency.text = foodDependencyText.lowercase()
                foodDependency.visibility = View.VISIBLE
            }
        }
    }
}