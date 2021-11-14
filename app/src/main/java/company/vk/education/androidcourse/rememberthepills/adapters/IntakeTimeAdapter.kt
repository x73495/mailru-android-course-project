package company.vk.education.androidcourse.rememberthepills.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.models.IntakeTime

class IntakeTimeAdapter(
    private val intakeTimes: MutableList<IntakeTime>,
    private val activity: FragmentActivity
) : RecyclerView.Adapter<IntakeTimeAdapter.IntakeTimeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IntakeTimeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_intake_time, parent, false)
        return IntakeTimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: IntakeTimeViewHolder, position: Int) {
        holder.bind(intakeTimes, position, this, activity)
    }

    override fun getItemCount() = intakeTimes.size

    class IntakeTimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textIntakeTime: MaterialTextView = itemView.findViewById(R.id.text_intake_time)

        fun bind(
            intakeTimes: MutableList<IntakeTime>,
            position: Int,
            adapter: IntakeTimeAdapter,
            activity: FragmentActivity
        ) {
            val intakeTime = intakeTimes[position]

            itemView.findViewById<ConstraintLayout>(R.id.layout_intake_time).setOnClickListener {
                val timePicker = MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(12)
                    .setMinute(10)
                    .setTitleText(R.string.pick_intake_time)
                    .build()

                timePicker.addOnPositiveButtonClickListener {
                    intakeTime.hour = timePicker.hour
                    intakeTime.minute = timePicker.minute
                    adapter.notifyDataSetChanged()
                }

                timePicker.show(
                    activity.supportFragmentManager,
                    timePicker.toString()
                )
            }

            itemView.findViewById<MaterialButton>(R.id.button_intake_time_remove)
                .setOnClickListener {
                    intakeTimes.remove(intakeTime)
                    adapter.notifyDataSetChanged()
                }

            val textMinute = if (intakeTime.minute > 9) intakeTime.minute else "0${intakeTime.minute}"
            val textToSet = "${intakeTime.hour}:$textMinute"
            textIntakeTime.text = textToSet
        }
    }
}