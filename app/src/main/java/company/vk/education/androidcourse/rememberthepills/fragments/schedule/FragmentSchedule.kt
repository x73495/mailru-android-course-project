package company.vk.education.androidcourse.rememberthepills.fragments.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.adapters.CourseEntryAdapter
import company.vk.education.androidcourse.rememberthepills.models.CourseEntry

class FragmentSchedule : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<FloatingActionButton>(R.id.button_to_course_add).setOnClickListener {
            val action = FragmentScheduleDirections.actionFragmentScheduleToFragmentDrugList("choose")
            it.findNavController().navigate(action)
        }

        // TODO TEMPORARY
        val courseEntries = generateCourseEntries().toMutableList()

        val recycler: RecyclerView = view.findViewById(R.id.recycler_course_entries_by_date)
        val adapter = CourseEntryAdapter(courseEntries)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

//        Toast.makeText(context, recycler.canScrollVertically(-1).toString(), Toast.LENGTH_SHORT).show()
//
//        if (recycler.canScrollVertically(1)) {
//            recycler.setOnScrollChangeListener { view, i, i2, i3, i4 ->
//                if (!recycler.canScrollVertically(1)) {
//                    Toast.makeText(context, "yo", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
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