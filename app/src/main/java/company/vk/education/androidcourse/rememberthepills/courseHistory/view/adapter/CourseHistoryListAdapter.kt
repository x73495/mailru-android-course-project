package company.vk.education.androidcourse.rememberthepills.courseHistory.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.courseHistory.model.CourseHistoryEntry

class CourseHistoryListAdapter(private val courseEntries: List<CourseHistoryEntry>) :
    RecyclerView.Adapter<CourseHistoryListAdapter.CourseHistoryEntryViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseHistoryEntryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_course_history_entry, parent, false)

        return CourseHistoryEntryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseHistoryEntryViewHolder, position: Int) {
        holder.bind(courseEntries[position])
    }

    override fun getItemCount() = courseEntries.size

    class CourseHistoryEntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameOfDrug: TextView =
            itemView.findViewById(R.id.text_course_history_entry_name_of_drug)

        private val amountOfDrug: TextView =
            itemView.findViewById(R.id.text_course_history_entry_amount_of_drug)

        private val frequencyAmount: TextView =
            itemView.findViewById(R.id.course_history_entry_frequency_amount)

        private val frequencyMeasure: TextView =
            itemView.findViewById(R.id.course_history_entry_frequency_measure)

        private val dateStart: TextView =
            itemView.findViewById(R.id.course_history_entry_date_start)

        private val dateEnd: TextView =
            itemView.findViewById(R.id.course_history_entry_date_end)

        private val measureOfDrug: TextView =
            itemView.findViewById(R.id.text_course_history_entry_measure_of_drug)

        fun bind(courseHistoryEntry: CourseHistoryEntry) {
            nameOfDrug.text = courseHistoryEntry.nameOfDrug
            amountOfDrug.text = courseHistoryEntry.amountOfDrug.toString()
            frequencyAmount.text = courseHistoryEntry.frequencyAmount.toString()
            frequencyMeasure.text = courseHistoryEntry.frequencyMeasure
            dateStart.text = courseHistoryEntry.dateStart.toString().replace('-', '.')
            dateEnd.text = courseHistoryEntry.dateEnd.toString().replace('-', '.')
            measureOfDrug.text = courseHistoryEntry.measureOfDrug
        }
    }
}