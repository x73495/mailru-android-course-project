package company.vk.education.androidcourse.rememberthepills.schedule.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.schedule.view.adapter.CourseListAdapter
import company.vk.education.androidcourse.rememberthepills.schedule.model.CourseEntry
import company.vk.education.androidcourse.rememberthepills.schedule.viewModel.ScheduleViewModel
import java.util.*

class FragmentSchedule : Fragment() {

    private val scheduleViewModel: ScheduleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<FloatingActionButton>(R.id.button_to_course_add).setOnClickListener {
            val action = FragmentScheduleDirections.actionFragmentScheduleToFragmentDrugList(
                "choose"
            )
            it.findNavController().navigate(action)
        }

        // TODO TEMPORARY
        val courseEntries = generateCourseEntries().toMutableList()

        val recycler: RecyclerView = view.findViewById(R.id.recycler_course_entries_by_date)
        val adapter = CourseListAdapter(courseEntries)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

        // TODO TEMPORARY
        val tempCalendar = Calendar.getInstance()
        tempCalendar.set(2021, 10, 3)
        view.findViewById<CalendarView>(R.id.calendar_schedule).date = tempCalendar.timeInMillis
    }

    // TODO TEMPORARY
    private fun generateCourseEntries(): List<CourseEntry> {
        return listOf(
            CourseEntry(
                "\$названиеПрепарата0",
                12,
                isDone = false,
                isMissed = true,
                1,
                "шт"
            ),
            CourseEntry(
                "\$названиеПрепарата1",
                11,
                isDone = true,
                isMissed = false,
                2,
                "шт"
            ),
            CourseEntry(
                "\$названиеПрепарата2",
                10,
                isDone = false,
                isMissed = false,
                3,
                "шт"
            ),
            CourseEntry(
                "\$названиеПрепарата3",
                1337,
                isDone = true,
                isMissed = false,
                4,
                "мг"
            ),
            CourseEntry(
                "\$названиеПрепарата4",
                9,
                isDone = false,
                isMissed = true,
                5,
                "ед"
            ),
            CourseEntry(
                "\$названиеПрепарата5",
                888,
                isDone = false,
                isMissed = false,
                5,
                "ед"
            ),
            CourseEntry(
                "\$названиеПрепарата5",
                777777777,
                isDone = true,
                isMissed = false,
                6,
                "ед"
            ),
            CourseEntry(
                "\$названиеПрепарата6",
                654,
                isDone = false,
                isMissed = true,
                6,
                "мл"
            ),
            CourseEntry(
                "\$названиеПрепарата7",
                321,
                isDone = false,
                isMissed = false,
                7,
                "ед"
            )
        )
    }
}