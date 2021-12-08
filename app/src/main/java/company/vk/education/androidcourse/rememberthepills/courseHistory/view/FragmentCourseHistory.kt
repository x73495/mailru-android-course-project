package company.vk.education.androidcourse.rememberthepills.courseHistory.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.courseHistory.model.CourseHistoryEntry
import company.vk.education.androidcourse.rememberthepills.courseHistory.view.adapter.CourseHistoryListAdapter
import company.vk.education.androidcourse.rememberthepills.courseHistory.viewModel.CourseHistoryViewModel
import java.time.LocalDate

class FragmentCourseHistory : Fragment() {

    val courseHistoryViewModel: CourseHistoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_course_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // TODO TEMPORARY
        val courseHistoryEntries = generateCourseHistoryEntries().toMutableList()

        val recycler: RecyclerView = view.findViewById(R.id.recycler_course_history)
        val adapter = CourseHistoryListAdapter(courseHistoryEntries)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    // TODO TEMPORARY
    private fun generateCourseHistoryEntries(): List<CourseHistoryEntry> {
        return listOf(
            CourseHistoryEntry(
                "\$названиеПрепарата0",
                12,
                "шт",
                1,
                "неделю",
                LocalDate.of(2021, 12,2),
                LocalDate.of(2021, 12,2)
            ),
            CourseHistoryEntry(
                "\$названиеПрепарата1",
                11,
                "шт",
                2,
                "месяц",
                LocalDate.of(2021, 12,2),
                LocalDate.of(2021, 12,2)
            ),
            CourseHistoryEntry(
                "\$названиеПрепарата2",
                10,
                "шт",
                3,
                "день",
                LocalDate.of(2021, 12,2),
                LocalDate.of(2021, 12,2)
            ),
            CourseHistoryEntry(
                "\$названиеПрепарата3",
                1337,
                "мг",
                4,
                "месяц",
                LocalDate.of(2021, 12,2),
                LocalDate.of(2021, 12,2)
            ),
            CourseHistoryEntry(
                "\$названиеПрепарата4",
                9,
                "ед",
                5,
                "неделю",
                LocalDate.of(2021, 12,2),
                LocalDate.of(2021, 12,2)
            ),
            CourseHistoryEntry(
                "\$названиеПрепарата5",
                888,
                "ед",
                5,
                "неделю",
                LocalDate.of(2021, 12,2),
                LocalDate.of(2021, 12,2)
            ),
            CourseHistoryEntry(
                "\$названиеПрепарата5",
                777777777,
                "ед",
                6,
                "месяц",
                LocalDate.of(2021, 12,2),
                LocalDate.of(2021, 12,2)
            ),
            CourseHistoryEntry(
                "\$названиеПрепарата6",
                654,
                "мл",
                6,
                "день",
                LocalDate.of(2021, 12,2),
                LocalDate.of(2021, 12,2)
            ),
            CourseHistoryEntry(
                "\$названиеПрепарата7",
                321,
                "ед",
                7,
                "день",
                LocalDate.of(2021, 12,2),
                LocalDate.of(2021, 12,2)
            )
        )
    }
}
