package company.vk.education.androidcourse.rememberthepills.schedule.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    }

    // TODO TEMPORARY
    private fun generateCourseEntries(): List<CourseEntry> {
        return listOf(
            CourseEntry(
                "Фуфломицин",
                12,
                isDone = false,
                isMissed = true,
                1,
                "шт"
            ),
            CourseEntry(
                "Фуфломицин",
                12,
                isDone = true,
                isMissed = false,
                2,
                "шт"
            ),
            CourseEntry(
                "Фуфломицин",
                12,
                isDone = false,
                isMissed = false,
                3,
                "шт"
            ),
            CourseEntry(
                "препаратНейм",
                1337,
                isDone = true,
                isMissed = true,
                4,
                "мг"
            ),
            CourseEntry(
                "lol",
                -1,
                isDone = true,
                isMissed = true,
                5,
                "ед"
            ),
            CourseEntry(
                "idk",
                0,
                isDone = false,
                isMissed = false,
                5,
                "ед"
            ),
            CourseEntry(
                "aaaaaaaaa",
                4444,
                isDone = true,
                isMissed = false,
                6,
                "ед"
            ),
            CourseEntry(
                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
                999999999,
                isDone = true,
                isMissed = true,
                6,
                "ед"
            ),
            CourseEntry(
                "c",
                1,
                isDone = false,
                isMissed = false,
                7,
                "ед"
            )
        )
    }
}